package com.example.diplomaProject.domain.entity;

import com.example.diplomaProject.domain.constant.DataBaseType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "connected_db")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @Column(name = "db_type")
    private DataBaseType dbType;

    @Column
    private String url;

    @Column
    private String username;

    @Column
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "connDb")
    @JsonManagedReference
    private List<DynamicTable> tables;

}
