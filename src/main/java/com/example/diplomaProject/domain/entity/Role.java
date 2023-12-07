package com.example.diplomaProject.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "_role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title", unique = true)
    @NotBlank
    private String title;


}
