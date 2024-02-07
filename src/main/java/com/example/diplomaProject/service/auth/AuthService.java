package com.example.diplomaProject.service.auth;

import com.example.diplomaProject.domain.api.registration.AuthenticationReq;
import com.example.diplomaProject.domain.api.registration.RegistrationReq;
import com.example.diplomaProject.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<Response> register(RegistrationReq req);
    /*

     */

    ResponseEntity<Response> authenticate(AuthenticationReq req);
    /*

     */

}
