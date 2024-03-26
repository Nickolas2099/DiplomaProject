package com.example.diplomaProject.domain.mapper.dynamicDb;

import com.example.diplomaProject.domain.dto.Table;
import com.example.diplomaProject.domain.entity.DynamicTable;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = DynamicTableMapper.class)
public interface DynamicTableListMapper {

    List<DynamicTable> toEntityList(List<Table> tables);

    List<Table> toDtoList(List<DynamicTable> tables);

}
