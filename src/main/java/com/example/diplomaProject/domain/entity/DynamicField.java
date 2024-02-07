package com.example.diplomaProject.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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

    private String kind;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    @JsonBackReference
    @ToString.Exclude
    private DynamicTable table;

}
