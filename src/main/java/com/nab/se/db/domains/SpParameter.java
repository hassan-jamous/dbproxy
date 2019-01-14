package com.nab.se.db.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SpParameter {
    private String parameterName;
    private int parameterType;
    private String parameterValue;
}