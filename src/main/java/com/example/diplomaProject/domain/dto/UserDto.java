package com.example.diplomaProject.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "имя должно быть заполнено")
    private String firstName;

    @NotBlank(message = "фамилия должна быть заполнена")
    private String secondName;

    @NotEmpty(message = "пароль должен быть заполнен")
    private char[] password;

    private Timestamp inputTime;

    private Set<RoleDto> roles;

}
