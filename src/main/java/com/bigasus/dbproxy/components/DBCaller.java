package com.bigasus.dbproxy.components;

import com.bigasus.dbproxy.domains.StoredProcedureExecuteRequest;
import com.bigasus.dbproxy.domains.StoredProcedureParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DBCaller {

    @Autowired
    private SimpleJdbcCall simpleJdbcCall;

    public Map<String, Object> executeStoredProcedure(StoredProcedureExecuteRequest storedProcedureExecuteRequest) {
        List<SqlParameter> sqlParameters = buildSqlParameters(storedProcedureExecuteRequest);
        MapSqlParameterSource param = mapSqlParameters(storedProcedureExecuteRequest);

        this.simpleJdbcCall
                .withSchemaName(storedProcedureExecuteRequest.getSchemaName())
                .withCatalogName(storedProcedureExecuteRequest.getPackageName())
                .withProcedureName(storedProcedureExecuteRequest.getProcedureName())
                .declareParameters(sqlParameters.toArray(new SqlParameter[sqlParameters.size()]));

        Map<String, Object> spResponse = simpleJdbcCall.execute(param);
        return spResponse;
    }

    private MapSqlParameterSource mapSqlParameters(StoredProcedureExecuteRequest storedProcedureExecuteRequest) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        for (StoredProcedureParameter storedProcedureParameter : storedProcedureExecuteRequest.getInParameters()) {
            param.addValue(storedProcedureParameter.getParameterName(), storedProcedureParameter.getParameterValue());
        }
        return param;
    }


    public List<Map<String,Object>> executeSqlQuery(String sqlStatement) {
        List<Map<String,Object>> result = this.simpleJdbcCall.getJdbcTemplate().query(sqlStatement, new GeneralRowMapper());
        return result;
    }

    public Map<String, Integer> executeSqlUpdate(String sqlStatement) {
        Map<String, Integer> result = new HashMap<>();
        int numberOfAffectedRows = this.simpleJdbcCall.getJdbcTemplate().update(sqlStatement);
        result.put("numberOfAffectedRows", numberOfAffectedRows);
        return result;

    }

    private List<SqlParameter> buildSqlParameters(StoredProcedureExecuteRequest storedProcedureExecuteRequest) {
        List<SqlParameter> sqlParameters = new ArrayList<>();
        for (StoredProcedureParameter storedProcedureParameter : storedProcedureExecuteRequest.getInParameters()) {
            sqlParameters.add(new SqlParameter(storedProcedureParameter.getParameterName(), storedProcedureParameter.getParameterType()));
        }
        for (StoredProcedureParameter storedProcedureParameter : storedProcedureExecuteRequest.getOutParameters()) {
            sqlParameters.add(new SqlOutParameter(storedProcedureParameter.getParameterName(), storedProcedureParameter.getParameterType()));
        }
        return sqlParameters;
    }

}
