package com.example.diplomaProject.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements GrantedAuthority {

    private Long id;

    @NotBlank(message = "название роли должно быть заполнено")
    private String title;

    @Override
    public String getAuthority() {
        return this.title;
    }
}
