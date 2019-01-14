package com.nab.se.db.components;

import com.nab.se.db.domains.RequestDomain;
import com.nab.se.db.domains.SpParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class StoredProcedure {

    @Autowired
    private SimpleJdbcCall simpleJdbcCall;

    public Map<String, Object> getAccountList(RequestDomain requestDomain) {

        List<SqlParameter> sqlParameters = new ArrayList<>();
        for (SpParameter spParameter: requestDomain.getInParameters()) {
            sqlParameters.add(new SqlParameter(spParameter.getParameterName(), spParameter.getParameterType()));
        }
        for (SpParameter spParameter: requestDomain.getOutParameters()) {
            sqlParameters.add(new SqlOutParameter(spParameter.getParameterName(), spParameter.getParameterType()));
        }

        this.simpleJdbcCall
                .withSchemaName(requestDomain.getSchemaName())
                .withCatalogName(requestDomain.getPackageName())
                .withProcedureName(requestDomain.getProcedureName())
                .declareParameters(sqlParameters.toArray(new SqlParameter[sqlParameters.size()]));

        MapSqlParameterSource param = new MapSqlParameterSource();
        for (SpParameter spParameter: requestDomain.getInParameters()) {
            param.addValue(spParameter.getParameterName(), spParameter.getParameterValue());
        }

        Map<String, Object> spResponse = simpleJdbcCall.execute(param);
        return spResponse;

    }



}
