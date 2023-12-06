package com.example.diplomaProject.domain.response.error;

import com.example.diplomaProject.domain.constant.Code;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private Code code;
    private String message;

}
