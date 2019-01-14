package com.bigasus.dbproxy.components;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GeneralRowMapper implements RowMapper<Map<String,Object>> {
    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        Map<String, Object> row = new HashMap<>();
        for (int i = 1 ; i<= rs.getMetaData().getColumnCount(); i++) {
            row.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
        }
        return row;
    }
}
