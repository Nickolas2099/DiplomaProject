package com.example.diplomaProject.service.user;

import com.example.diplomaProject.domain.constant.Code;
import com.example.diplomaProject.domain.entity.User;
import com.example.diplomaProject.domain.response.Response;
import com.example.diplomaProject.domain.response.SuccessResponse;
import com.example.diplomaProject.domain.response.error.Error;
import com.example.diplomaProject.domain.response.error.ErrorResponse;
import com.example.diplomaProject.repository.UserRepository;
import com.example.diplomaProject.util.EncryptPassword;
import com.example.diplomaProject.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ValidationUtils validation;
    private final EncryptPassword encryptPassword;


    public ResponseEntity<Response> getAll() {

        List<User> users = userRepository.findAllByOrderBySecondName();

        return new ResponseEntity<>(SuccessResponse.builder().data(users).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getById(Long id) {

        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            return new ResponseEntity<>(SuccessResponse.builder().data(userOptional.get()).build(), HttpStatus.OK);
        } else {
            log.error("user with id: {} is not found", id);
            return new ResponseEntity<>(ErrorResponse.builder()
                    .error(Error.builder().code(Code.NOT_FOUND).message("Пользователь не найден").build())
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Response> add(User user) {

        validation.validationRequest(user);
        user.setPassword(encryptPassword.encryptPassword(user.getPassword()));
        userRepository.save(user);

        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> update(User user) {

        validation.validationRequest(user);

        Optional<User> userOptional = userRepository.findById(user.getId());
        if(userOptional.isPresent()) {
            User baseUser = userOptional.get();
            baseUser.setFirstName(user.getFirstName());
            baseUser.setSecondName(user.getSecondName());
            baseUser.setPassword(user.getPassword());
            baseUser.setRoles(user.getRoles());
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

        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }


}
