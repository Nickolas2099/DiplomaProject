package com.example.diplomaProject.domain.mapper.dynamicDb;

import com.example.diplomaProject.domain.entity.DynamicField;
import org.hibernate.transform.ResultTransformer;

import java.util.List;

public class FieldMapper implements ResultTransformer {

    @Override
    public Object transformTuple(Object[] objects, String[] strings) {
        DynamicField field = new DynamicField();
        field.setTechTitle((String)objects[0]);
        field.setType((String)objects[1]);
        return field;
    }

    @Override
    public List transformList(List resultSet) {
        return ResultTransformer.super.transformList(resultSet);
    }
}
