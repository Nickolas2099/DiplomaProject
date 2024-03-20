package com.example.diplomaProject.service.connectedDB;

import com.example.diplomaProject.domain.dto.ConnDbDto;
import com.example.diplomaProject.domain.entity.ConnDb;
import com.example.diplomaProject.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface ConnectedDbService {

    ResponseEntity<Response> add(ConnDbDto connectedDb);
    /*
        Checking value for validity and save DB.
        Returning empty SuccessResponse.
     */

    ResponseEntity<Response> add(ConnDb connDb);
    /*
        Checking value for validity and save DB.
        Returning empty SuccessResponse.
     */

    ResponseEntity<Response> getAll();
    /*
        Returning all databases
     */

    ResponseEntity<Response> getById(Long id);
    /*
        Checking id for validity and Return database
     */

    ResponseEntity<Response> update(ConnDbDto connectedDb);
    /*
       Update saved database
     */

    ResponseEntity<Response> remove(Long id);
    /*
        Remove saved database by id
     */

    ResponseEntity<Response> getByTitle(String dbTitle);
    /*
        Returning database by title;
     */

    ResponseEntity<Response> getTablesByDbTitle(String dbTitle);
    /*
        Returning tables of saved database by title
     */
}
