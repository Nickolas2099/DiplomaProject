package com.example.diplomaProject.controller;

import com.example.diplomaProject.domain.response.Response;
import com.example.diplomaProject.service.action.ActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/action")
public class ActionController {

    private final ActionService actionService;


    @GetMapping("/recent")
    public ResponseEntity<Response> getRecent() {

        log.info("START endpoint getLastTenActions");
        ResponseEntity<Response> resp = actionService.getRecent();
        log.info("END endpoint getLastTenActions");
        return resp;
    }

}
