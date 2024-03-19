package com.example.diplomaProject.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;

import java.sql.Timestamp;

@Entity
@Table(name = "action")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String queryText;

    @CreationTimestamp
    private Timestamp inputTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private DynamicTable table;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;


}
