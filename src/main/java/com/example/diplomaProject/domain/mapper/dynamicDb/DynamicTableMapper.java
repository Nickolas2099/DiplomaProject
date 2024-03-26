package com.example.diplomaProject.domain.mapper.dynamicDb;

import com.example.diplomaProject.domain.dto.Field;
import com.example.diplomaProject.domain.entity.DynamicField;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DynamicTableMapper {

    DynamicField toEntity(Field field);

    Field toDto(DynamicField field);
}
