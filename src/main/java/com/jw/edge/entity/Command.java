package com.jw.edge.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Command {
    @Id
    private String thisId;
    private String deviceId;
    private String deviceName;
    private String edgexId;
    private String commandId;
    private int commandType;
    private String commandName;
    public static final int GET = 0;
    public static final int PUT =  1;
    public static final int POST = 2;
    public static final int DELETE = 3;

    public String getThisId() {
        return thisId;
    }

    public void setThisId(String thisId) {
        this.thisId = thisId;
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

    public String getEdgexId() {
        return edgexId;
    }

    public void setEdgexId(String edgexId) {
        this.edgexId = edgexId;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public int getCommandType() {
        return commandType;
    }

    public void setCommandType(int commandType) {
        this.commandType = commandType;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }
}
