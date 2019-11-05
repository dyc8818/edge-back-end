package com.jw.edge.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import com.jw.edge.controller.api.WebDataController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Data

public class Terminal {

    private int flag1 = 100;
    private int flag2 = 100;

    private int parameter=WebDataController.parameter;

    private String name=WebDataController.name;
    private String symbol=WebDataController.symbol;
    private String operation=WebDataController.operation;

    public int getFlag1() {
        return flag1;
    }

    public int getFlag2() {
        return flag2;
    }
}