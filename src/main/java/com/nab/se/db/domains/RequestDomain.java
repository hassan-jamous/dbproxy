package com.nab.se.db.domains;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RequestDomain {
    private String schemaName;
    private String packageName;
    private String procedureName;
    private List<SpParameter> InParameters;
    private List<SpParameter> OutParameters;
}


