package com.example.diplomaProject.service.dynamicDb;

import com.example.diplomaProject.domain.api.constructor.QueryReq;
import com.example.diplomaProject.domain.api.SwitchDbReq;
import com.example.diplomaProject.domain.dto.ConnDbDto;
import com.example.diplomaProject.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface DynamicDbService {

    ResponseEntity<Response> switchDb(SwitchDbReq req);
    /*

     */

    ResponseEntity<Response> getAll();
    /*

     */

    ResponseEntity<Response> checkConnection(ConnDbDto connDb);
    /*

     */

    ResponseEntity<Response> handleDb(ConnDbDto connDb);
    /*

     */

    ResponseEntity<Response> selectFromDb(QueryReq req);
    /*

     */

}
