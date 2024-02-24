package com.example.diplomaProject.domain.api.constructor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryReq {

    private String dbTitle;
    private String table;
    private List<String> fields;
    private List<SqlFilter> filters;
    private List<Sort> sorts;
    private List<String> groups;
    private Integer limit;



}
