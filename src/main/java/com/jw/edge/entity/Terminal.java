package com.jw.edge.entity;

import lombok.Data;

@Data

public class Terminal {

    private int flag1 = 100;
    private int flag2 = 100;
    private Temperature temperature;
//    private Wet wet;
    private int parameter=30;
    private String name="we";
    private String symbol=">";
    private String operation="警告";
}