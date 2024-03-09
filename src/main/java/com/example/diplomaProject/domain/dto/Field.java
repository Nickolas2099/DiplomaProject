package com.example.diplomaProject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Field {

    private String userTitle;

    private String techTitle;

    private String type;

    private List<String> value;
}
