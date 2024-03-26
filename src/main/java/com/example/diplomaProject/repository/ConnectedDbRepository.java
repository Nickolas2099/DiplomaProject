package com.example.diplomaProject.repository;

import com.example.diplomaProject.domain.entity.ConnDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConnectedDbRepository extends JpaRepository<ConnDb, Long> {


    Optional<ConnDb> findByTitle(String dbTitle);
}
