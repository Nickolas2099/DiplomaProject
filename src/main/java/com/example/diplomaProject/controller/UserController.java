package com.example.diplomaProject.controller;

import com.example.diplomaProject.domain.entity.User;
import com.example.diplomaProject.domain.response.Response;
import com.example.diplomaProject.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<Response> getAllUsers() {

        log.info("START endpoint getAllUsers");
        ResponseEntity<Response> resp = userService.getAll();
        log.info("END endpoint getAllUsers");
        return resp;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getUserById(@PathVariable("id") Long id) {

        log.info("START endpoint getUserById, userId: {}", id);
        ResponseEntity<Response> resp = userService.getById(id);
        log.info("END endpoint getUserById, resp: {}", resp);
        return resp;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addUser(@RequestBody final User user) {

        log.info("START endpoint addUser, user: {}", user);
        ResponseEntity<Response> resp = userService.add(user);
        log.info("END endpoint addUser, resp: {}", resp);
        return resp;
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Response> deleteUser(@PathVariable("id") Long id) {

        log.info("START endpoint deleteUser, userId: {}", id);
        ResponseEntity<Response> resp = userService.remove(id);
        log.info("END endpoint deleteUser, resp: {}", resp);
        return resp;
    }

    @PatchMapping("/update")
    public ResponseEntity<Response> updateUser(@RequestBody final User user) {

        log.info("START endpoint updateUser, user: {}", user);
        ResponseEntity<Response> resp = userService.update(user);
        log.info("END endpoint updateUser, resp: {}", resp);
        return resp;
    }
}
