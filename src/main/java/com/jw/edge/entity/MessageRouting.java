package com.jw.edge.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class MessageRouting {
    @Id
    private String messageRoutingId;
    private String messageRoutingName;
    private String messageRoutingAdress;
    private String messageRoutingPort;
    private String messageRoutingType;
    private Date messageRoutingCreateTime;
    private String deviceId;
    private String deviceName;

    public String getMessageRoutingId() {
        return messageRoutingId;
    }

    public void setMessageRoutingId(String messageRoutingId) {
        this.messageRoutingId = messageRoutingId;
    }

    public String getMessageRoutingName() {
        return messageRoutingName;
    }

    public void setMessageRoutingName(String messageRoutingName) {
        this.messageRoutingName = messageRoutingName;
    }

    public String getMessageRoutingAdress() {
        return messageRoutingAdress;
    }

    public void setMessageRoutingAdress(String messageRoutingAdress) {
        this.messageRoutingAdress = messageRoutingAdress;
    }

    public String getMessageRoutingPort() {
        return messageRoutingPort;
    }

    public void setMessageRoutingPort(String messageRoutingPort) {
        this.messageRoutingPort = messageRoutingPort;
    }

    public String getMessageRoutingType() {
        return messageRoutingType;
    }

    public void setMessageRoutingType(String messageRoutingType) {
        this.messageRoutingType = messageRoutingType;
    }

    public Date getMessageRoutingCreateTime() {
        return messageRoutingCreateTime;
    }

    public void setMessageRoutingCreateTime(Date messageRoutingCreateTime) {
        this.messageRoutingCreateTime = messageRoutingCreateTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
