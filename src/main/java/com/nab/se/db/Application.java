package com.nab.se.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {

    public static void main (String [] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
