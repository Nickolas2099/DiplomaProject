package com.example.diplomaProject.domain.api;

import com.example.diplomaProject.domain.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationReq {

    private String firstName;

    private String secondName;

    private String password;

    private Set<RoleDto> roles;

}
