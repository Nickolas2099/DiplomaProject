package com.example.diplomaProject.service.connectedDB;

import com.example.diplomaProject.domain.dto.ConnDbDto;
import com.example.diplomaProject.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface ConnectedDbService {

    ResponseEntity<Response> add(ConnDbDto connectedDb);
    /*

     */

    ResponseEntity<Response> getAll();
    /*

     */

    ResponseEntity<Response> getById(Long id);
    /*

     */

    ResponseEntity<Response> update(ConnDbDto connectedDb);
    /*

     */

    ResponseEntity<Response> remove(Long id);
    /*

     */

    ResponseEntity<Response> getByTitle(String dbTitle);
    /*

     */

    ResponseEntity<Response> getTablesByDbTitle(String dbTitle);
    /*

     */
}
