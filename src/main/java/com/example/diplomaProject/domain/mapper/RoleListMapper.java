package com.example.diplomaProject.domain.mapper;

import com.example.diplomaProject.domain.dto.RoleDto;
import com.example.diplomaProject.domain.entity.Role;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface RoleListMapper {

    Set<RoleDto> toDtoSet(Set<Role> roles);
    Set<Role> toEntitySet(Set<RoleDto> roles);

}
