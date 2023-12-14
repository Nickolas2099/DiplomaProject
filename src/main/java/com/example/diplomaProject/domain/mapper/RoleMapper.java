package com.example.diplomaProject.domain.mapper;

import com.example.diplomaProject.domain.dto.RoleDto;
import com.example.diplomaProject.domain.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto toDto(Role roleEntity);

    Role toEntity(RoleDto roleDto);

}
