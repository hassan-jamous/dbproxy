package com.bigasus.dbproxy.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class StoredProcedureExecuteResponse {
    public Map<String, Object> response;
}
