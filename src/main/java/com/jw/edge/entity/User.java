package com.jw.edge.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class User {
    @Id
    private String userId;
    private String userName;
    private String userRemark;

    public User() {
    }

    public User(String name, String remark) {
        this.userName = name;
        this.userRemark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String remark) {
        this.userRemark = remark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}