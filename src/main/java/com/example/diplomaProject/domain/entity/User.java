package com.example.diplomaProject.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"first_name"}),
        @UniqueConstraint(columnNames = {"second_name"})
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "_password")
    private char[] password;

    @Column(name = "input_time")
    private String inputTime;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "user_role",
//            joinColumns = @JoinColumn(name = "_user", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "_role", referencedColumnName = "id"))
    private Set<Role> roles;

}
