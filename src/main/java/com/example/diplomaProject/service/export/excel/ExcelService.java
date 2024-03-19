package com.example.diplomaProject.service.export.excel;

import com.example.diplomaProject.domain.api.ExportReq;
import com.example.diplomaProject.domain.api.constructor.QueryResp;
import com.example.diplomaProject.domain.response.Response;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExcelService {

    ResponseEntity<InputStreamResource> getFile(ExportReq req);

}
