package com.example.diplomaProject.domain.mapper.user;

import com.example.diplomaProject.domain.dto.UserDto;
import com.example.diplomaProject.domain.entity.User;
import com.example.diplomaProject.domain.mapper.role.RoleListMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoleListMapper.class)
public interface UserMapper {

    UserDto toDto(User userEntity);
    User toEntity(UserDto userDto);

}
