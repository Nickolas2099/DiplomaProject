package com.example.diplomaProject.service.export.excel;

import com.example.diplomaProject.domain.api.ExportReq;
import com.example.diplomaProject.domain.api.constructor.QueryField;
import com.example.diplomaProject.domain.api.constructor.QueryResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService{

    @Override
    public ResponseEntity<InputStreamResource> getFile(ExportReq req) {

        try {
            Workbook workbook = this.getWorkBook(req.getRows());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] bytes = outputStream.toByteArray();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=select.xlsx");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(bytes.length)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(new InputStreamResource(inputStream));
        } catch (IOException ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    private Workbook getWorkBook(List<QueryResp> req) throws IOException {

        List<String> columns = new ArrayList<>();
        for(QueryField field : req.get(0).getFields()) {
            columns.add(field.getUserTitle());
        }
        Workbook workbook = new XSSFWorkbook();
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Data_report");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < columns.size(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns.get(col));
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Age
            CellStyle ageCellStyle = workbook.createCellStyle();
            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 1;
            for(QueryResp queryResp : req) {
                Row row = sheet.createRow(rowIdx++);
                int i = 0;
                for(QueryField field : queryResp.getFields()) {
                    row.createCell(i).setCellValue(field.getValue());
                    i++;
                }
            }

            workbook.write(out);
            return workbook;
        }
    }

}
