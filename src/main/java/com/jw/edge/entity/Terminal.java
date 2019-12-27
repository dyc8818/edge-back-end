package com.jw.edge.entity;

import lombok.Data;
import com.jw.edge.controller.api.WebDataController;
import com.jw.edge.controller.api.TerminalDataController;

@Data

public class Terminal {

    private int flag1 = 0;
    private int flag2 = 0;
    private int flag3 = 0;

    private int drools_threshold=WebDataController.threshold[0];
    private String drools_parameterName=WebDataController.parameterName[0];
    private String drools_symbol=WebDataController.symbol[0];
    private String drools_operation=WebDataController.operation[0];

    private int drools_threshold2=WebDataController.threshold[1];
    private String drools_parameterName2=WebDataController.parameterName[1];
    private String drools_symbol2=WebDataController.symbol[1];
    private String drools_operation2=WebDataController.operation[1];

    private int drools_threshold3=WebDataController.threshold[2];
    private String drools_parameterName3=WebDataController.parameterName[2];
    private String drools_symbol3=WebDataController.symbol[2];
    private String drools_operation3=WebDataController.operation[2];

    int terminal_value=TerminalDataController.value;
    String terminal_name=TerminalDataController.name;

    public int getFlag1() {
        return flag1;
    }
    public int setFlag1(int num) { flag1=num; return flag1;}

    public int getFlag2() {
        return flag2;
    }
    public int setFlag2(int num) { flag2=num; return flag2;}

    public int getFlag3() {
        return flag3;
    }
    public int setFlag3(int num) { flag3=num; return flag3;}
}