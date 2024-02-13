package com.example.diplomaProject.repository;

import com.example.diplomaProject.domain.entity.DynamicTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<DynamicTable, Long> {
}
