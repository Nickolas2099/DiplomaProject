package com.example.diplomaProject.domain.api.constructor;

import com.example.diplomaProject.domain.dto.Field;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryResp {

    List<QueryField> fields;

}
