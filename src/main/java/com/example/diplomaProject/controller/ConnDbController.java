package com.example.diplomaProject.controller;

import com.example.diplomaProject.domain.dto.ConnDbDto;
import com.example.diplomaProject.domain.response.Response;
import com.example.diplomaProject.service.connectedDB.ConnectedDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/connectedDbs")
public class ConnDbController {

    private final ConnectedDbService connDbService;

    @GetMapping
    public ResponseEntity<Response> getAllDbs() {

        log.info("START endpoint getAllDbs");
        ResponseEntity<Response> resp = connDbService.getAll();
        log.info("END endpoint getAllDbs");
        return resp;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getDbById(@PathVariable("id") Long id) {

        log.info("START endpoint getDbById, db id: `{}`", id);
        ResponseEntity<Response> resp = connDbService.getById(id);
        log.info("END endpoint getDbById, response: `{}`", resp);
        return resp;
    }

    @PostMapping
    public ResponseEntity<Response> addDb(@RequestBody final ConnDbDto connDb) {

        log.info("START endpoint addDb, db: `{}`", connDb);
        ResponseEntity<Response> resp = connDbService.add(connDb);
        log.info("END endpoint addDb");
        return resp;
    }

    @PatchMapping
    public ResponseEntity<Response> updateDb(@RequestBody final ConnDbDto connDb) {

        log.info("START endpoint updateDb, db: `{}`", connDb);
        ResponseEntity<Response> resp = connDbService.update(connDb);
        log.info("END endpoint updateDb");
        return resp;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteDb(@PathVariable("id") Long id) {

        log.info("START endpoint deleteDb, db id: `{}`", id);
        ResponseEntity<Response> resp = connDbService.remove(id);
        log.info("END endpoint deleteDb");
        return resp;
    }

}
