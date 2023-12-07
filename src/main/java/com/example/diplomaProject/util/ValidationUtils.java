package com.example.diplomaProject.util;

import com.example.diplomaProject.domain.constant.Code;
import com.example.diplomaProject.domain.response.exception.CommonException;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValidationUtils {

    private final Validator validator;

    public <T> void validationRequest(T request) {

        Set<ConstraintViolation<T>> result = validator.validate(request);
        if(!result.isEmpty()) {
            String resultValidations = result.stream()
                    .map(ConstraintViolation::getMessage)
                    .reduce((s1, s2) -> s1 + ". " + s2).orElse("");
            log.error("The json passed in the request is not valid, validation error: {}", resultValidations);
            throw CommonException.builder()
                    .code(Code.INVALID_VALUE).message(resultValidations).httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }


}
