package com.nab.se.db.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nab.se.db.components.StoredProcedure;
import com.nab.se.db.domains.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;


@RestController
public class AccountsController {
    @Autowired
    private ObjectMapper jsonObjectsMapper;

    @Autowired
    StoredProcedure sp;

    @GetMapping(value = "/testData/request")
    public ResponseEntity<ResponseDomain> spRequest(@RequestParam(value = "q") String q) throws Exception{
        RequestDomain rd = jsonObjectsMapper.readValue(URLDecoder.decode(q, "UTF-8"), RequestDomain.class);
        sp.getAccountList(rd);
        ResponseDomain response = new ResponseDomain();
        response.setResponse(sp.getAccountList(rd));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

//{"schemaName": "OPS$UNLBUILD","packageName": "pkgAccountSE","procedureName": "prcGetAccountList","inParameters": [{"parameterName": "inCustomerId","parameterType": "12","parameterValue": "999999903"}, {"parameterName": "inProductMidList","parameterType": "12"}],"outParameters": [{"parameterName": "outErrorFID","parameterType": "12"}, {"parameterName": "outErrorMessage","parameterType": "12"}, {"parameterName": "outAccountList","parameterType": "-10"}]}
