package com.example.diplomaProject.service.user;

import com.example.diplomaProject.domain.constant.Code;
import com.example.diplomaProject.domain.dto.UserDto;
import com.example.diplomaProject.domain.entity.Role;
import com.example.diplomaProject.domain.entity.User;
import com.example.diplomaProject.domain.mapper.user.UserListMapper;
import com.example.diplomaProject.domain.mapper.user.UserMapper;
import com.example.diplomaProject.domain.response.Response;
import com.example.diplomaProject.domain.response.SuccessResponse;
import com.example.diplomaProject.domain.response.error.Error;
import com.example.diplomaProject.domain.response.error.ErrorResponse;
import com.example.diplomaProject.repository.RoleRepository;
import com.example.diplomaProject.repository.UserRepository;
import com.example.diplomaProject.service.security.JwtService;
import com.example.diplomaProject.util.EncryptPassword;
import com.example.diplomaProject.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ValidationUtils validation;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserListMapper userListMapper;
    private final UserMapper userMapper;
    private final JwtService jwtService;


    public ResponseEntity<Response> getAll() {

        List<User> users = userRepository.findAllByOrderBySecondName();
        List<UserDto> dtoUsers = userListMapper.toDtoList(users);

        return new ResponseEntity<>(SuccessResponse.builder().data(dtoUsers).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getById(Long id) {

        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            UserDto userDto = userMapper.toDto(userOptional.get());
            return new ResponseEntity<>(SuccessResponse.builder().data(userDto).build(), HttpStatus.OK);
        } else {
            log.error("user with id: {} is not found", id);
            return new ResponseEntity<>(ErrorResponse.builder()
                    .error(Error.builder().code(Code.NOT_FOUND).message("Пользователь не найден").build())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Response> add(UserDto userDto) {

        validation.validationRequest(userDto);
        User user = userMapper.toEntity(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> rolesCopy = new HashSet<>(user.getRoles());
        for(Role role : rolesCopy) {
            Optional<Role> optionalRole = roleRepository.findRoleByTitle(role.getTitle());
            if(optionalRole.isPresent()) {
                user.getRoles().remove(role);
                user.getRoles().add(optionalRole.get());
            }
        }
        userRepository.save(user);

        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> update(UserDto userDto) {

        validation.validationRequest(userDto);
        User user = userMapper.toEntity(userDto);

        Optional<User> userOptional = userRepository.findById(user.getId());
        if(userOptional.isPresent()) {
            User baseUser = userOptional.get();
            baseUser.setFirstName(user.getFirstName());
            baseUser.setSecondName(user.getSecondName());
//            baseUser.setPassword(user.getPassword());
            baseUser.setRoles(user.getRoles());
            Set<Role> rolesCopy = new HashSet<>(user.getRoles());
            for(Role role : rolesCopy) {
                Optional<Role> optionalRole = roleRepository.findRoleByTitle(role.getTitle());
                if(optionalRole.isPresent()) {
                    user.getRoles().remove(role);
                    user.getRoles().add(optionalRole.get());
                }
            }
            userRepository.save(baseUser);

            return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
        }

        return new ResponseEntity<>(ErrorResponse.builder()
                .error(Error.builder().code(Code.INVALID_VALUE).message("Пользователь не найден").build())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Response> remove(Long id) {

        if(!userRepository.existsById(id)) {
            log.error("user with id: {} is not found", id);
            return new ResponseEntity<>(ErrorResponse.builder()
                    .error(Error.builder().code(Code.NOT_FOUND).message("Пользователь не найден").build())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        userRepository.deleteById(id);

        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> assignAdmin(String login) {

        User user = getUserByLogin(login);
        Role adminRole = roleRepository.findRoleByTitle("ADMIN")
                .orElseGet(() -> Role.builder().title("ADMIN").build());
        if(user.getRoles().contains(adminRole)) {
            return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder()
                            .message("Admin role already assigned to user with login: `" + login + "`")
                            .code(Code.BAD_REQUEST)
                            .build()).build(), HttpStatus.BAD_REQUEST);
        } else {
            user.getRoles().add(adminRole);
            userRepository.save(user);
        }
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> checkAdminRole(String jwt) {

        String login = jwtService.extractUsername(jwt);
        User user = getUserByLogin(login);
        if(hasUserAdminRole(user)) {
            return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(ErrorResponse.builder()
                .error(Error.builder().message("user isn't admin").code(Code.FORBIDDEN).build())
                .build(), HttpStatus.FORBIDDEN);
    }

    private User getUserByLogin(String login) {

        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User with that login haven't found"));
    }

    private boolean hasUserAdminRole(User user) {
        Role adminRole = roleRepository.findRoleByTitle("ADMIN")
                .orElseGet(() -> Role.builder().title("ADMIN").build());
        return user.getRoles().contains(adminRole);
    }

}
