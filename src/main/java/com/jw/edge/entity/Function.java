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
    private String functionDesc;
    private String holdingRegister;
    private String valueType;
    private String readWrite;
    private String valueSize;
    private String valueScale;
    private String valueMinimum;
    private String valueMaximum;
    private String defaultValue;
    private String unitsType;
    private String defaultUnits;

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getReadWrite() {
        return readWrite;
    }

    public void setReadWrite(String readWrite) {
        this.readWrite = readWrite;
    }

    public String getValueSize() {
        return valueSize;
    }

    public void setValueSize(String valueSize) {
        this.valueSize = valueSize;
    }

    public String getValueScale() {
        return valueScale;
    }

    public void setValueScale(String valueScale) {
        this.valueScale = valueScale;
    }

    public String getValueMinimum() {
        return valueMinimum;
    }

    public void setValueMinimum(String valueMinimum) {
        this.valueMinimum = valueMinimum;
    }

    public String getValueMaximum() {
        return valueMaximum;
    }

    public void setValueMaximum(String valueMaximum) {
        this.valueMaximum = valueMaximum;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getUnitsType() {
        return unitsType;
    }

    public void setUnitsType(String unitsType) {
        this.unitsType = unitsType;
    }

    public String getDefaultUnits() {
        return defaultUnits;
    }

    public void setDefaultUnits(String defaultUnits) {
        this.defaultUnits = defaultUnits;
    }




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

    public String getHoldingRegister() {
        return holdingRegister;
    }

    public void setHoldingRegister(String holdingRegister) {
        this.holdingRegister = holdingRegister;
    }

    public String getFunctionDesc() {
        return functionDesc;
    }

    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc;
    }



}

