package com.example.diplomaProject.repository;

import com.example.diplomaProject.domain.entity.ConnDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectedDbRepository extends JpaRepository<ConnDb, Long> {



}
