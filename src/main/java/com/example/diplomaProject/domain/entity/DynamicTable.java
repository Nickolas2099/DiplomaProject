package com.example.diplomaProject.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "dynamic_table")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DynamicTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userTitle;

    private String techTitle;

    @OneToMany
    @JoinColumn(name = "table_id")
    private List<DynamicField> fields;



}
