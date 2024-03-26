package com.example.diplomaProject.service.user;

import com.example.diplomaProject.domain.dto.RoleDto;
import com.example.diplomaProject.domain.dto.UserDto;
import com.example.diplomaProject.domain.entity.Role;
import com.example.diplomaProject.domain.entity.User;
import com.example.diplomaProject.domain.mapper.user.UserListMapper;
import com.example.diplomaProject.domain.mapper.user.UserMapper;
import com.example.diplomaProject.domain.response.Response;
import com.example.diplomaProject.domain.response.SuccessResponse;
import com.example.diplomaProject.domain.response.error.ErrorResponse;
import com.example.diplomaProject.repository.RoleRepository;
import com.example.diplomaProject.repository.UserRepository;
import com.example.diplomaProject.util.ValidationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;


    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private ValidationUtils validation;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private UserListMapper userListMapper;
    @Mock
    private UserMapper userMapper;

    @Test
    void getAllTest_Success() {
        // Arrange
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1L, "ROLE_USER"));
        Set<RoleDto> dtoRoles = new HashSet<>();
        dtoRoles.add(new RoleDto(1L, "ROLE_USER"));


        User user1 = new User(1L, "login123", "Alexandr", "Alexandrov", "123456",
                new Timestamp(new Date().getTime()), roles);
        User user2 = new User(2L, "login456", "Ivan", "Ivanov", "111222333",
                new Timestamp(new Date().getTime()), roles);
        List<User> users = List.of(user1, user2);

        UserDto userDto1 = new UserDto(1L, "login123", "Alexandr", "Alexandrov", "123456",
                new Timestamp(new Date().getTime()), dtoRoles);
        UserDto userDto2 = new UserDto(2L, "login456", "Ivan", "Ivanov", "111222333",
                new Timestamp(new Date().getTime()), dtoRoles);
        List<UserDto> userDtos = List.of(userDto1, userDto2);

        Mockito.when(userRepository.findAllByOrderBySecondName()).thenReturn(users);
        Mockito.when(userListMapper.toDtoList(users)).thenReturn(userDtos);

        // Act
        ResponseEntity<Response> response = userService.getAll();

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertTrue(response.getBody() instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response.getBody();
        Assertions.assertEquals(2, ((List)successResponse.getData()).size());
        Assertions.assertEquals(userDtos, successResponse.getData());
    }

//    @Test
//    void  getAllTest_Error() {
//
//        Mockito.when(userRepository.findAllByOrderBySecondName()).thenThrow(new RuntimeException("Ошибка при получении пользователей"));
//
//        ResponseEntity<Response> response = userService.getAll();
//
//        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        Assertions.assertTrue(response.getBody() instanceof ErrorResponse);
//    }


}
