package com.example.diplomaProject.domain.entity;

import com.example.diplomaProject.domain.constant.DataBaseType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "connected_data_base")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnectedDataBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @Column
    private DataBaseType dataBaseType;

    @Column
    private String url;

    @Column
    private String username;

    @Column
    private String password;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<ConnectedTable> tables;

}
