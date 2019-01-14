package com.nab.se.db.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nab.se.db.components.DBCaller;
import com.nab.se.db.domains.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;


@RestController
public class DbController {
    @Autowired
    private ObjectMapper jsonObjectsMapper;

    @Autowired
    DBCaller dbCaller;

    @GetMapping(value = "/testData/storedProcedure")
    public ResponseEntity<StoredProcedureExecuteResponse> spRequest(@RequestParam(value = "q") String q) throws Exception{
        StoredProcedureExecuteRequest rd = jsonObjectsMapper.readValue(URLDecoder.decode(q, "UTF-8"), StoredProcedureExecuteRequest.class);
        dbCaller.executeStoredProcedure(rd);
        StoredProcedureExecuteResponse response = new StoredProcedureExecuteResponse();
        response.setResponse(dbCaller.executeStoredProcedure(rd));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/testData/sqlQuery")
    public ResponseEntity<List<Map<String,Object>>> sqlQuery(@RequestParam(value = "q") String q) throws Exception{
        return new ResponseEntity<>(dbCaller.executeSqlQuery(q), HttpStatus.OK);
    }

    @GetMapping(value = "/testData/sqlUpdate")
    public ResponseEntity<Map<String, Integer>> sqlUpdate(@RequestParam(value = "q") String q) throws Exception{
        return new ResponseEntity<>(dbCaller.executeSqlUpdate(q), HttpStatus.OK);
    }



}

//{"schemaName": "OPS$UNLBUILD","packageName": "pkgAccountSE","procedureName": "prcGetAccountList","inParameters": [{"parameterName": "inCustomerId","parameterType": "12","parameterValue": "999999903"}, {"parameterName": "inProductMidList","parameterType": "12"}],"outParameters": [{"parameterName": "outErrorFID","parameterType": "12"}, {"parameterName": "outErrorMessage","parameterType": "12"}, {"parameterName": "outAccountList","parameterType": "-10"}]}
//{"schemaName": "OPS$UNLBUILD","packageName": "pkgAccountSE","procedureName": "prcGetAccountList","inParameters": [{"parameterName": "inCustomerId","parameterType": "12","parameterValue": "999999999"}, {"parameterName": "inProductMidList","parameterType": "12"}],"outParameters": [{"parameterName": "outErrorFID","parameterType": "12"}, {"parameterName": "outErrorMessage","parameterType": "12"}, {"parameterName": "outAccountList","parameterType": "-10"}]}
