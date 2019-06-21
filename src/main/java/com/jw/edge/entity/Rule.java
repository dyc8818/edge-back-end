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
