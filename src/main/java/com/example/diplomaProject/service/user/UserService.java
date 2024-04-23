package com.example.diplomaProject.service.user;

import com.example.diplomaProject.domain.dto.UserDto;
import com.example.diplomaProject.domain.entity.User;
import com.example.diplomaProject.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Response> getAll();
    /*
        getting all users with Sort by surname
        returning userDto list inside ResponseEntity
    */

    ResponseEntity<Response> getById(Long id);
    /*
        getting user by his id
        returning userDto or errorResponse inside ResponseEntity
    */

    ResponseEntity<Response> add(UserDto user);
    /*
        adding user with checking for the existence of a role
        returning empty successResponse or ErrorResponse inside ResponseEntity
    */

    ResponseEntity<Response> update(UserDto user);
    /*
        updating user
        returning empty successResponse or ErrorResponse inside ResponseEntity
    */


    ResponseEntity<Response> remove(Long id);
    /*
        delete user by his id if user with this id exists
        returning empty successResponse or ErrorResponse inside ResponseEntity
    */

    ResponseEntity<Response> assignAdmin(String login);
    /*
        add admin role for user by login
     */

    ResponseEntity<Response> checkAdminRole(String jwt);
    /*
        extract login from jwt, then get user and check roles.
        returning empty successResponse or status 403 in ErrorResponse
     */

}
