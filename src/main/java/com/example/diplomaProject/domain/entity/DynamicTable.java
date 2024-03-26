package com.example.diplomaProject.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "table")
    @JsonManagedReference
//    @JoinColumn(name = "table_id")
    private List<DynamicField> fields;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "db_id")
    @JsonBackReference
    @ToString.Exclude
    private ConnDb connDb;

}
