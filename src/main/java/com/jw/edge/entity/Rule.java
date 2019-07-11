package com.jw.edge.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Rule {
    @Id
    private String ruleId;
    private String ruleName;

    private String ruleDescribe;
    private String ruleFormat;
    private String ruleState;
    private String ruleSQL;
    private Date ruleCreateTime;

    private String ruleParaThreshold;
    private String rulePara;
    private String ruleToDevice;
    private int ruleJudge;
    private int ruleStatus;


    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleParaThreshold() {
        return ruleParaThreshold;
    }

    public void setRuleParaThreshold(String ruleParaThreshold) {
        this.ruleParaThreshold = ruleParaThreshold;
    }

    public String getRulePara() {
        return rulePara;
    }

    public void setRulePara(String rulePara) {
        this.rulePara = rulePara;
    }

    public String ruleToDevice() {
        return ruleToDevice;
    }

    public void setRuleToDevice(String ruleToDevice) {
        this.ruleToDevice = ruleToDevice;
    }

    public int getRuleJudge() { return ruleJudge; }

    public void setRuleJudge(int ruleJudge) {this.ruleJudge = ruleJudge; }

    public int getRuleStatus() { return ruleStatus; }

    public void setRuleStatus(int ruleStatus) {this.ruleStatus = ruleStatus; }

    public String getRuleDescribe() {
        return ruleDescribe;
    }

    public void setRuleDescribe(String ruleDescribe) {
        this.ruleDescribe = ruleDescribe;
    }

    public String getRuleFormat() {
        return ruleFormat;
    }

    public void setRuleFormat(String ruleFormat) {
        this.ruleFormat = ruleFormat;
    }

    public String getRuleState() {
        return ruleState;
    }

    public void setRuleState(String ruleState) {
        this.ruleState = ruleState;
    }

    public String getRuleSQL() {
        return ruleSQL;
    }

    public void setRuleSQL(String ruleSQL) {
        this.ruleSQL = ruleSQL;
    }

    public Date getRuleCreateTime() {
        return ruleCreateTime;
    }

    public void setRuleCreateTime(Date ruleCreateTime) {
        this.ruleCreateTime = ruleCreateTime;
    }

}
