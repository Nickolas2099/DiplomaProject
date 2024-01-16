package com.example.diplomaProject.domain.mapper.dynamicDb;

import com.example.diplomaProject.domain.dto.Field;
import org.hibernate.transform.ResultTransformer;

import java.util.List;

public class FieldMapper implements ResultTransformer {

    @Override
    public Object transformTuple(Object[] objects, String[] strings) {
        Field field = new Field();
        field.setTechTitle((String)objects[0]);
        return field;
    }

    @Override
    public List transformList(List resultSet) {
        return ResultTransformer.super.transformList(resultSet);
    }
}
