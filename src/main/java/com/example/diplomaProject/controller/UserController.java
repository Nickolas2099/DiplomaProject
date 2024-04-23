package com.example.diplomaProject.controller;

import com.example.diplomaProject.domain.api.CheckAdminRoleReq;
import com.example.diplomaProject.domain.dto.UserDto;
import com.example.diplomaProject.domain.entity.User;
import com.example.diplomaProject.domain.response.Response;
import com.example.diplomaProject.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "User API")

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Operation(summary = "Gets all users", tags = "user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the users",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                            )
                    }
            )
    })
    @GetMapping
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

    @PostMapping
    public ResponseEntity<Response> addUser(@RequestBody final UserDto user) {

        log.info("START endpoint addUser, user: {}", user);
        ResponseEntity<Response> resp = userService.add(user);
        log.info("END endpoint addUser, resp: {}", resp);
        return resp;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable("id") Long id) {

        log.info("START endpoint deleteUser, userId: {}", id);
        ResponseEntity<Response> resp = userService.remove(id);
        log.info("END endpoint deleteUser, resp: {}", resp);
        return resp;
    }

    @PatchMapping
    public ResponseEntity<Response> updateUser(@RequestBody final UserDto user) {

        log.info("START endpoint updateUser, user: {}", user);
        ResponseEntity<Response> resp = userService.update(user);
        log.info("END endpoint updateUser, resp: {}", resp);
        return resp;
    }

    @PostMapping("/assignAdmin/{login}")
    public ResponseEntity<Response> assignAdminRole(@PathVariable("login") final String login) {
        log.info("START endpoint assignAdminRole, login: {}", login);
        ResponseEntity<Response> resp = userService.assignAdmin(login);
        log.info("END endpoint assignAdminRole, response: {}", resp);
        return resp;
    }

    @PostMapping("/checkAdminRole")
    public ResponseEntity<Response> checkAdmin(@RequestBody final CheckAdminRoleReq req) {
        log.info("START endpoint checkAdminRole");
        ResponseEntity<Response> resp = userService.checkAdminRole(req.getJwt());
        log.info("END endpoint checkAdminRole, response: {}", resp);
        return resp;
    }

}
