package com.example.diplomaProject.domain.mapper.dynamicDb;

import com.example.diplomaProject.domain.dto.Field;
import com.example.diplomaProject.domain.entity.DynamicField;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = DynamicFieldMapper.class)
public interface DynamicFieldListMapper {

    List<Field> toDtoList(List<DynamicField> fields);

    List<DynamicField> toEntityList(List<Field> fields);

}
