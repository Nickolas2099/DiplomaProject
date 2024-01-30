package com.example.diplomaProject.controller;

import com.example.diplomaProject.domain.api.SwitchDbReq;
import com.example.diplomaProject.domain.dto.ConnDbDto;
import com.example.diplomaProject.domain.response.Response;
import com.example.diplomaProject.service.connectedDB.ConnectedDbService;
import com.example.diplomaProject.service.dynamicDb.DynamicDbService;
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
    private final DynamicDbService dynamicDbService;

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
        log.info("END endpoint deleteDb, resp: `{}`", resp);
        return resp;
    }

    @PostMapping("/switch")
    public ResponseEntity<Response> switchDb(@RequestBody final SwitchDbReq req) {

        log.info("START endpoint switchDb, db req: `{}`", req);
        ResponseEntity<Response> resp = dynamicDbService.switchDb(req);
        log.info("END endpoint switchDb, resp: `{}`", resp);
        return resp;
    }

    @GetMapping("/db")
    public ResponseEntity<Response> getAllFromDb() {

        log.info("START endpoint getAllFromDb");
        ResponseEntity<Response> resp = dynamicDbService.getAll();
        log.info("END endpoint getAllFromDb, resp: `{}`", resp);
        return resp;
    }

    @GetMapping("/testConnection")
    public ResponseEntity<Response> checkDbConnection(@RequestBody final ConnDbDto connDb) {

        log.info("START endpoint checkDbConnection");
        ResponseEntity<Response> resp = dynamicDbService.checkConnection(connDb);
        log.info("END endpoint checkDbConnection, resp `{}`", resp);
        return resp;
    }

    @GetMapping("/table")
    public ResponseEntity<Response> getTables(@RequestBody final String dbTitle) {

        log.info("START endpoint getTables, req: {}", dbTitle);
        ResponseEntity<Response> resp = connDbService.getTablesByDbTitle(dbTitle);
        log.info("END endpoint getTables, resp `{}`", resp);
        return resp;
    }

}
