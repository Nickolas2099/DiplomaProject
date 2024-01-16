package com.example.diplomaProject.service.dynamicDb;

import com.example.diplomaProject.domain.constant.Code;
import com.example.diplomaProject.domain.dto.ConnDbDto;
import com.example.diplomaProject.domain.entity.ConnDb;
import com.example.diplomaProject.domain.mapper.connDb.ConnDbMapper;
import com.example.diplomaProject.domain.response.Response;
import com.example.diplomaProject.domain.response.SuccessResponse;
import com.example.diplomaProject.domain.response.error.Error;
import com.example.diplomaProject.domain.response.error.ErrorResponse;
import com.example.diplomaProject.service.connectedDB.ConnectedDbService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DynamicDbServiceImpl implements DynamicDbService {

    private final ConnectedDbService connectedDbService;
    private final ConnDbMapper connDbMapper;

    public static SessionFactory dynamicSessionFactory;

    public ResponseEntity<Response> switchDynamicDb(String dbTitle) {
        try {
            SuccessResponse response = (SuccessResponse)connectedDbService.getByTitle(dbTitle).getBody();
            dynamicSessionFactory = getSessionFactory(connDbMapper.toEntity((ConnDbDto)response.getData()));
            return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder()
                    .code(Code.INVALID_VALUE).message("база с названием: `" + dbTitle + "` не найдена").build()).build(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    private SessionFactory getSessionFactory(ConnDb dataBase) {
        Configuration config = new Configuration();

        String driver = "";
        String dialect = "";
        switch (dataBase.getDbType()) {
            case MYSQL -> {
                driver = "com.mysql.jdbc.Driver";
                dialect = "MySQLDialect";
            }
            case POSTGRESQL -> {
                driver = "org.postgresql.Driver";
                dialect = "PostgreSQLDialect";
            }
        }

        config.setProperty("hibernate.connection.driver_class_2", driver);
//        config.setProperty("hibernate.connection.url", "jdbc:" + kind + "://" + dataBase. + ":" +  + "/" + dataBase.getTitle());
        config.setProperty("hibernate.connection.url_2", dataBase.getUrl());
        config.setProperty("hibernate.connection.username_2", dataBase.getUsername());
        config.setProperty("hibernate.connection.password_2", dataBase.getPassword());
        config.setProperty("hibernate.dialect", "org.hibernate.dialect." + dialect);

//        config.addAnnotatedClass()

        return config.buildSessionFactory();
    }

}
