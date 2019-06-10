package com.jw.edge.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Surveillance {
    @Id
    private String surveillanceID;
    private String surveillanceName;
    private String surveillanceFeature;
    private int surveillanceFeatureValue;

    public Surveillance() {
    }

    public Surveillance(String name, String feature, int value) {
        this.surveillanceName = name;
        this.surveillanceFeature = feature;
        this.surveillanceFeatureValue = value;
    }

    public String getSurveillanceName() {
        return surveillanceName;
    }

    public void setSurveillanceName(String name) {
        this.surveillanceName = name;
    }

    public String getSurveillanceFeature() {
        return surveillanceFeature;
    }

    public void setSurveillanceFeature(String feature) {
        this.surveillanceFeature = feature;
    }

    public String getSurveillanceID() {
        return surveillanceID;
    }

    public void setSurveillanceID(String id) {
        this.surveillanceID = id;
    }

    public int getSurveillanceFeatureValue(){
        return surveillanceFeatureValue;
    }

    public void setSurveillanceFeatureValue(int value){
        this.surveillanceFeatureValue = value;
    }
}