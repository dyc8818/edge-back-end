package com.jw.edge.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import com.jw.edge.controller.api.WebDataController;
import com.jw.edge.controller.api.TerminalDataController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Data

public class Terminal {

    private int flag1 = 100;
    private int flag2 = 100;

    private int threshold=WebDataController.threshold;
    private String name=WebDataController.name;
    private String symbol=WebDataController.symbol;
    private String operation=WebDataController.operation;

    int terminal_value=TerminalDataController.value;
    String terminal_name=TerminalDataController.name;

    public int getFlag1() {
        return flag1;
    }
    public int getFlag2() {
        return flag2;
    }
    public int setFlag1(int num) { flag1=num; return flag1;}
    public int setFlag2(int num) { flag2=num; return flag2;}
}