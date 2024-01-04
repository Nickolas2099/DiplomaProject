package com.example.diplomaProject.domain.dto;

import com.example.diplomaProject.domain.constant.DataBaseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConnDbDto {


    private Long id;

    @NotBlank(message = "название базы данных должно быть заполнено")
    private String title;

    @NotNull(message = "тип базы должен быть заполнен")
    private DataBaseType dbType;

    @NotBlank(message = "url адрес базы должен быть заполнен")
    private String url;

    @NotBlank(message = "пользователь базы должен быть заполнен")
    private String username;

    @NotBlank(message = "пароль базы должен быть заполнен")
    private String password;

}
