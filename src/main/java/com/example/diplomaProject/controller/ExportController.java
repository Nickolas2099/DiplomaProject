package com.example.diplomaProject.controller;

import com.example.diplomaProject.domain.api.ExportReq;
import com.example.diplomaProject.domain.response.Response;
import com.example.diplomaProject.service.export.excel.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/export")
public class ExportController {

    private final ExcelService excelService;

    @PostMapping("/excelFile")
    public ResponseEntity<InputStreamResource> getExcelFile(@RequestBody ExportReq req) {

        log.info("START endpoint getExcelFile");
        ResponseEntity<InputStreamResource> resp = excelService.getFile(req);
        log.info("END endpoint getExcelFile, resp: {}", resp);
        return resp;
    }


}
