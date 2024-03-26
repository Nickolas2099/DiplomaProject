package com.example.diplomaProject.domain.mapper.dynamicDb;

import com.example.diplomaProject.domain.api.constructor.QueryField;
import com.example.diplomaProject.domain.api.constructor.QueryResp;
import org.hibernate.transform.ResultTransformer;

import java.util.ArrayList;
import java.util.List;

public class ResultMapper implements ResultTransformer {
    @Override
    public Object transformTuple(Object[] objects, String[] strings) {
        QueryResp resp = new QueryResp();
        resp.setFields(new ArrayList<>());
        for(int i = 0; i < objects.length; i++) {
            resp.getFields().add(QueryField.builder().userTitle(strings[i]).value(objects[i].toString()).build());
        }
        return resp;
    }

    @Override
    public List transformList(List resultSet) {
        return ResultTransformer.super.transformList(resultSet);
    }
}
