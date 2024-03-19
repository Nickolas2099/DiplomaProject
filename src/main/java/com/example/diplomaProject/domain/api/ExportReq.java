package com.example.diplomaProject.domain.api;

import com.example.diplomaProject.domain.api.constructor.QueryResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExportReq {

    private List<QueryResp> rows;
}
