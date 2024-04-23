package com.example.diplomaProject.repository;

import com.example.diplomaProject.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByOrderBySecondName();

    Optional<User> findBySecondNameAndFirstName(String secondName, String firstName);

    Optional<User> findByLogin(String login);


}
