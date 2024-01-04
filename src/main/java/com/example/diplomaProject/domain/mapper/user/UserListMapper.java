package com.example.diplomaProject.domain.mapper.user;

import com.example.diplomaProject.domain.dto.UserDto;
import com.example.diplomaProject.domain.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserListMapper {

    List<UserDto> toDtoList(List<User> users);

    List<User> toEntityList(List<UserDto> users);

}
