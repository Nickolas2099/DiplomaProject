package com.example.diplomaProject.domain.api.allDbData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetDbDataResp {

    private List<TableResp> tables;
}
