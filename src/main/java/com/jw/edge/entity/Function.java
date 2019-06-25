package com.jw.edge.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document
public class Function {
    @Id
    private String functionId;
    private String functionType;
    private String functionName;
    private String functionIdentifier;


    /*public Function() {
    }

    public Function(String name, String identifier) {
        this.functionName = name;
        this.functionIdentifier = identifier;
    }*/

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionIdentifier() {
        return functionIdentifier;
    }

    public void setFunctionIdentifier(String functionIdentifier) {
        this.functionIdentifier = functionIdentifier;
    }





}

