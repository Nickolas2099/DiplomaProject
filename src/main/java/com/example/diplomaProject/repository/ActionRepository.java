package com.example.diplomaProject.repository;

import com.example.diplomaProject.domain.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Long> {

    @Query("SELECT a FROM Action a ORDER BY a.inputTime DESC")
    List<Action> getRecentActions();
}
