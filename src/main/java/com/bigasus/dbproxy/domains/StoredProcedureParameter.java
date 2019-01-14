package com.bigasus.dbproxy.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StoredProcedureParameter {
    private String parameterName;
    private int parameterType;
    private String parameterValue;
}