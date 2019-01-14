package com.nab.se.db.domains;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class StoredProcedureExecuteRequest {
    private String schemaName;
    private String packageName;
    private String procedureName;
    private List<StoredProcedureParameter> InParameters;
    private List<StoredProcedureParameter> OutParameters;
}


