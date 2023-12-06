package com.example.diplomaProject.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse <T> implements Response{

    private T data;
}
