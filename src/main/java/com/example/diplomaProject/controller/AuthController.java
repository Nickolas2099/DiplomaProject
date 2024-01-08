package com.example.diplomaProject.controller;

import com.example.diplomaProject.domain.api.AuthenticationReq;
import com.example.diplomaProject.domain.api.RegistrationReq;
import com.example.diplomaProject.domain.response.Response;
import com.example.diplomaProject.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody RegistrationReq req) {

        log.info("START endpoint register, req: {}", req);
        ResponseEntity<Response> resp = authService.register(req);
        log.info("END endpoint register, resp: {}", resp);
        return resp;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Response> authenticate(@RequestBody AuthenticationReq req) {

        log.info("START endpoint authenticate, req: {}", req);
        ResponseEntity<Response> resp = authService.authenticate(req);
        log.info("END endpoint authenticate, resp: {}", resp);
        return resp;
    }

}
