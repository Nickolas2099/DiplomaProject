package com.example.diplomaProject.service.dynamicDb;

import com.example.diplomaProject.domain.api.constructor.QueryReq;
import com.example.diplomaProject.domain.api.SwitchDbReq;
import com.example.diplomaProject.domain.dto.ConnDbDto;
import com.example.diplomaProject.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface DynamicDbService {

    ResponseEntity<Response> switchDb(SwitchDbReq req);
    /*
        Marked as deprecated. Have used to change current database.
     */

    ResponseEntity<Response> getAll(String dbTitle);
    /*
        Getting all data from DB by its title for each table
     */

    ResponseEntity<Response> checkConnection(ConnDbDto connDb);
    /*
        Trying to open session by db connection. If connection successful returning empty SuccessResponse otherwise
        ErrorResponse
     */

    ResponseEntity<Response> handleDb(ConnDbDto connDb);
    /*
        Define tables and fields of dataBase and then save it all. Handle only MySQl & Postgres data bases yet/
        Returning empty SuccessResponse or ErrorResponse
     */

    ResponseEntity<Response> selectFromDb(QueryReq req, String jwt);
    /*
        Assemble SQL query from QueryReq parameters and send it.
        Returning SuccessResponse with result of SELECT inside.
     */

}
