package com.example.diplomaProject.domain.mapper.dynamicDb;

import com.example.diplomaProject.domain.dto.Table;
import com.example.diplomaProject.domain.entity.DynamicTable;
import org.hibernate.transform.ResultTransformer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TableMapper implements ResultTransformer {


    @Override
    public Object transformTuple(Object[] objects, String[] strings) {
        DynamicTable table = new DynamicTable();
        table.setTechTitle((String)objects[0]);
        return table;
    }

    @Override
    public List transformList(List resultList) {
        return ResultTransformer.super.transformList(resultList);
    }
}
