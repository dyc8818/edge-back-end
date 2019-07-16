package com.jw.edge.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document
public class Function {
    @Id
    private String functionId;
    private String productId;
    private String productName;
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
    private String resourceIndex;
    private String resourceOperation;
    private String resourceObject;
    private String resourceParameter;
    private String resourceProperty;
    private String responseCode1;
    private String responseCode2;
    private String responseDec1;
    private String responseDec2;
    private String responseDec3;
    private String responseDec4;
//    private String ;
//    private String ;
    public String getProductId() {
    return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setFunctionproName(String productName) {
        this.productName = productName;
    }
    public String getFunctionIdentifier() {
        return functionIdentifier;
    }

    public void setFunctionIdentifier(String functionIdentifier) {
        this.functionIdentifier = functionIdentifier;
    }

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

    public String getResourceIndex() {
        return resourceIndex;
    }

    public void setResourceIndex() {
        this.resourceIndex = "1";
    }

    public String getResourceOperation() {
        return resourceOperation;
    }

    public void setResourceOperation() {
        this.resourceOperation = "get";
    }

    public String getResourceObject() {
        return resourceObject;
    }

    public void setResourceObject(String functionName) {
        this.resourceObject = functionName;
    }

    public String getResourceParameter() {
        return resourceParameter;
    }

    public void setResourceParameter(String functionName) {
        this.resourceParameter = functionName;
    }

    public String getResourceProperty() {
        return resourceProperty;
    }

    public void setResourceProperty() {
        this.resourceProperty = "value";
    }

    public String getResponseCode1() {
        return responseCode1;
    }

    public void setResponseCode1() {
        this.responseCode1 = "200";
    }

    public String getResponseCode2() {
        return responseCode2;
    }

    public void setResponseCode2() {
        this.responseCode2 = "503";
    }

    public String getResponseDec1() {
        return responseDec1;
    }

    public void setResponseDec1() {
        this.responseDec1 = "Get the temperature in degrees F";
    }

    public String getResponseDec2() {
        return responseDec2;
    }

    public void setResponseDec2() {
        this.responseDec2 = "Get the temperature in degrees C";
    }

    public String getResponseDec3() {
        return responseDec3;
    }

    public void setResponseDec3() {
        this.responseDec3 = "Get the humidity in %RH";
    }

    public String getResponseDec4() {
        return responseDec4;
    }

    public void setResponseDec4() {
        this.responseDec4 = "service unavailable";
    }

}

