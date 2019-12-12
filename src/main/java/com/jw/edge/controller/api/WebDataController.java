package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.web.bind.annotation.*;
import javax.jms.*;
import com.jw.edge.entity.Terminal;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

@RequestMapping("/api")
@RestController

public class WebDataController {

    public static String parameterName;
    public static int threshold;
    public static String symbol;
    public static String operation;
    public static String service;

//    public static String parameterName2;
//    public static int threshold2;
//    public static String symbol2;
//    public static String operation2;
//    public static String service2;

    @PostMapping("/webdata")
    @ResponseBody
    public String Webdata(@RequestBody JSONObject info){

        int threshold = info.getIntValue("ruleParaThreshold");
        String name = info.getString("rulePara");
        String symbol = info.getString("ruleJudge");
        String operation = info.getString("ruleExecute");
        String service = info.getString("service");

        if (this.parameterName==null)
        {
            this.parameterName=name;
            this.threshold=threshold;
            this.symbol=symbol;
            this.operation=operation;
            this.service=service;
        }
        /*
        else if(this.parameterName2==null)
        {
            this.parameterName2=name;
            this.threshold2=threshold;
            this.symbol2=symbol;
            this.operation2=operation;
            this.service2=service;
        }

         */

        return service;
    }
}
