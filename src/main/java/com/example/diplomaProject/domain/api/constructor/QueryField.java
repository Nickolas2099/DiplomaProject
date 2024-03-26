package com.example.diplomaProject.domain.api.constructor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryField {

    private String userTitle;

    private String value;

}
