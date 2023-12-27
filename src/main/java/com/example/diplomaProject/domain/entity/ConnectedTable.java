package com.example.diplomaProject.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "connected_table")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConnectedTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<ConnectedField> fields;

    @ManyToOne(fetch = FetchType.LAZY)
    private ConnectedDataBase dataBase;
}
