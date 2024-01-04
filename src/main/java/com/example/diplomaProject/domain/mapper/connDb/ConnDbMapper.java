package com.example.diplomaProject.domain.mapper.connDb;

import com.example.diplomaProject.domain.dto.ConnDbDto;
import com.example.diplomaProject.domain.entity.ConnDb;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConnDbMapper {

    ConnDb toEntity(ConnDbDto connDb);

    ConnDbDto toDto(ConnDb connDb);

}
