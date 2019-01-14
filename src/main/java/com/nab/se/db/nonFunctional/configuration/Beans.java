package com.nab.se.db.nonFunctional.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class Beans {

    @Bean
    public SimpleJdbcCall getSimpleJdbcCall(JdbcTemplate jdbcTemplate) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall.getJdbcTemplate().setQueryTimeout(10);
        return simpleJdbcCall;
    }
}
