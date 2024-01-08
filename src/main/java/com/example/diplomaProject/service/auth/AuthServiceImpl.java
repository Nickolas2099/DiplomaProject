package com.example.diplomaProject.service.auth;

import com.example.diplomaProject.domain.api.AuthenticationReq;
import com.example.diplomaProject.domain.api.AuthenticationResp;
import com.example.diplomaProject.domain.api.RegistrationReq;
import com.example.diplomaProject.domain.constant.Code;
import com.example.diplomaProject.domain.dto.UserDto;
import com.example.diplomaProject.domain.mapper.user.UserMapper;
import com.example.diplomaProject.domain.response.Response;
import com.example.diplomaProject.domain.response.SuccessResponse;
import com.example.diplomaProject.domain.response.error.Error;
import com.example.diplomaProject.domain.response.error.ErrorResponse;
import com.example.diplomaProject.repository.UserRepository;
import com.example.diplomaProject.service.security.JwtService;
import com.example.diplomaProject.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<Response> register(RegistrationReq req) {

        var user = UserDto.builder()
                .firstName(req.getFirstName())
                .secondName(req.getSecondName())
                .password(req.getPassword())
                .roles(req.getRoles())
                .build();
        ResponseEntity<Response> optionalResp = userService.add(user);
        if(optionalResp.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder()
                    .code(Code.INTERNAL_SERVER_ERROR).message("Something went wrong")
                    .build()).build(), HttpStatus.BAD_REQUEST);
        }
        String jwToken = jwtService.generateToken(user);

        return new ResponseEntity<>(SuccessResponse.builder()
                .data(AuthenticationResp.builder().token(jwToken).build())
                .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> authenticate(AuthenticationReq req) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getSecondName() + " " + req.getFirstName(),
                    req.getPassword()
                )
        );
        var user = userRepository.findBySecondNameAndFirstName(req.getSecondName(), req.getFirstName())
                .orElseThrow();
        String jwToken = jwtService.generateToken(userMapper.toDto(user));

        return new ResponseEntity<>(SuccessResponse.builder().data(AuthenticationResp.builder()
                        .token(jwToken)
                .build()).build(), HttpStatus.OK);
    }
}
