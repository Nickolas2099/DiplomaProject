package com.example.diplomaProject.domain.mapper.connDb;

import com.example.diplomaProject.domain.dto.ConnDbDto;
import com.example.diplomaProject.domain.entity.ConnDb;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ConnDbMapper.class)
public interface ConnDbListMapper {

    List<ConnDbDto> toDtoList(List<ConnDb> connDbs);

    List<ConnDb> toEntityList(List<ConnDbDto> connDbDtos);
}
