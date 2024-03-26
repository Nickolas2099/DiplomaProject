package com.example.diplomaProject.repository;

import com.example.diplomaProject.domain.entity.DynamicField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<DynamicField, Long> {
}
