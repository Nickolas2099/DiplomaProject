package com.example.diplomaProject.service.user;

import com.example.diplomaProject.domain.entity.User;
import com.example.diplomaProject.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Response> getAll();

    ResponseEntity<Response> getById(Long id);

    ResponseEntity<Response> add(User user);

    ResponseEntity<Response> update(User user, Long id);

    ResponseEntity<Response> remove(Long id);

}
