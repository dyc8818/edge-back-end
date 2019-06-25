package com.jw.edge.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document
public class Surveillance {
    @Id
    private String surveillanceId;
    @Field("name")
    private String surveillanceName;
    @Field("type")
    private String surveillanceFeatureType;
    @Field("value")
    private int surveillanceFeatureValue;

    public Surveillance() {
    }

    public Surveillance(String name, String type, int value) {
        this.surveillanceName = name;
        this.surveillanceFeatureType = type;
        this.surveillanceFeatureValue = value;
    }

    public String getSurveillanceName() {
        return surveillanceName;
    }

    public void setSurveillanceName(String name) {
        this.surveillanceName = name;
    }

    public String getSurveillanceFeatureType() {
        return surveillanceFeatureType;
    }

    public void setSurveillanceFeatureType(String type) {
        this.surveillanceFeatureType = type;
    }

    public String getSurveillanceId() {
        return surveillanceId;
    }

    public void setSurveillanceId(String id) {
        this.surveillanceId = id;
    }

    public int getSurveillanceFeatureValue(){
        return surveillanceFeatureValue;
    }

    public void setSurveillanceFeatureValue(int value){
        this.surveillanceFeatureValue = value;
    }
}