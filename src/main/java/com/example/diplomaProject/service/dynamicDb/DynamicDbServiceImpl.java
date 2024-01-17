package com.example.diplomaProject.service.dynamicDb;

import com.example.diplomaProject.domain.api.SwitchDbReq;
import com.example.diplomaProject.domain.constant.Code;
import com.example.diplomaProject.domain.dto.ConnDbDto;
import com.example.diplomaProject.domain.dto.Field;
import com.example.diplomaProject.domain.dto.Table;
import com.example.diplomaProject.domain.entity.ConnDb;
import com.example.diplomaProject.domain.mapper.connDb.ConnDbMapper;
import com.example.diplomaProject.domain.mapper.dynamicDb.FieldMapper;
import com.example.diplomaProject.domain.mapper.dynamicDb.TableMapper;
import com.example.diplomaProject.domain.response.Response;
import com.example.diplomaProject.domain.response.SuccessResponse;
import com.example.diplomaProject.domain.response.error.Error;
import com.example.diplomaProject.domain.response.error.ErrorResponse;
import com.example.diplomaProject.service.connectedDB.ConnectedDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DynamicDbServiceImpl implements DynamicDbService {

    private final ConnectedDbService connectedDbService;
    private final ConnDbMapper connDbMapper;
    private Set<Table> tables;

    public static SessionFactory dynamicSessionFactory;
    private String dbName;

    @Override
    public ResponseEntity<Response> switchDb(SwitchDbReq req) {
        try {
            SuccessResponse response = (SuccessResponse)connectedDbService.getByTitle(req.getTitle()).getBody();
            ConnDb connDb = connDbMapper.toEntity((ConnDbDto)response.getData());
            dbName = getDatabaseName(connDb.getUrl());
            dynamicSessionFactory = getSessionFactory(connDb);
            return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
        }catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder()
                    .code(Code.INVALID_VALUE).message("база с названием: `" + req.getTitle() + "` не найдена")
                    .build()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    private String getDatabaseName(String url) throws URISyntaxException {
        URI uri = new URI(url.substring(5));
        return uri.getPath().substring(1);
    }

    @Override
    public ResponseEntity<Response> getAll() {
        try {
            Session session = dynamicSessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            List<Table> tables =
                    session.createNativeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES " +
                            "WHERE TABLE_SCHEMA = '" + dbName + "' AND TABLE_TYPE = 'BASE TABLE';")
                            .setResultTransformer(new TableMapper()).list();
            log.info("enabled tables: {}", tables);

            for(Table table : tables) {
                table.setFields(
                        session.createNativeQuery("SELECT COLUMN_NAME " +
                                        "FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + table.getTechTitle() + "';")
                                .setResultTransformer(new FieldMapper()).list());
            }
            log.info("enabled tables: {}", tables);

            for(Table table : tables) {
                for(Field field : table.getFields()) {
                    List<Object> values = session.createNativeQuery("SELECT " + field.getTechTitle()
                            + " FROM " + table.getTechTitle()).list();
                    List<String> stringValues = values.stream()
                            .map(Object::toString)
                            .collect(Collectors.toList());
                    field.setValue(stringValues);
                }
            }
            log.info("enabled tables: {}", tables);

            tx.commit();

            return new ResponseEntity<>(SuccessResponse.builder().data(tables).build(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder()
                    .code(Code.INTERNAL_SERVER_ERROR).message("Error getting data from the database")
                    .build()).build(), HttpStatus.BAD_REQUEST);
        } finally {
            dynamicSessionFactory.close();
        }
    }

    private SessionFactory getSessionFactory(ConnDb dataBase) {
        Configuration config = new Configuration();

        String driver = "";
//        String dialect = "";
        switch (dataBase.getDbType()) {
            case MYSQL -> {
                driver = "com.mysql.cj.jdbc.Driver";
//                dialect = "MySQLDialect";
            }
            case POSTGRESQL -> {
                driver = "org.postgresql.Driver";
//                dialect = "PostgreSQLDialect";
            }
        }

        config.setProperty("hibernate.connection.driver_class", driver);
//        config.setProperty("hibernate.connection.url", "jdbc:" + kind + "://" + dataBase. + ":" +  + "/" + dataBase.getTitle());
        config.setProperty("hibernate.connection.url", dataBase.getUrl());
        config.setProperty("hibernate.connection.username", dataBase.getUsername());
        config.setProperty("hibernate.connection.password", dataBase.getPassword());
//        config.setProperty("hibernate.dialect", "org.hibernate.dialect." + dialect);

        return config.buildSessionFactory();
    }

}
