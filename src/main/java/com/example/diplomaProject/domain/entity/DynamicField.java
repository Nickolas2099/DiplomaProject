package com.example.diplomaProject.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dynamic_field")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DynamicField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userTitle;

    private String techTitle;


}
