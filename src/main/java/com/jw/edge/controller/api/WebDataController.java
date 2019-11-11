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

    public static ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

    public static String name;
    public static int threshold;
    public static String symbol;
    public static String operation;

    @PostMapping("/webdata")
    @ResponseBody
    public int Webdata(@RequestBody JSONObject info){

        int threshold = info.getIntValue("ruleParaThreshold");
        String name = info.getString("rulePara");
        String symbol = info.getString("ruleJudge");
        String operation = info.getString("ruleExecute");

        this.name=name;
        this.threshold=threshold;
        this.symbol=symbol;
        this.operation=operation;
        Test();
        return threshold;
    }

    @Test
    public void Test() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("all-rules");

        Terminal terminal = new Terminal();

        kieSession.insert(terminal);
        kieSession.fireAllRules();//通知规则引擎执行规则

        int flag1=terminal.getFlag1();
        int flag2=terminal.getFlag2();

        String tem1="温度高了,";
        String tem2="温度低了,";
        String wet1="湿度高了,";
        String wet2="湿度高了,";

        String op=" ";
        if(operation.equals("0")){op="要进行警告";}
        if(operation.equals("1")){op="要关闭设备";}
        if(operation.equals("2")){op="要进行赋值";}

        if (flag1==1){System.out.println(tem1+op);}
        if (flag1==2){System.out.println(tem2+op);}
        if (flag2==1){System.out.println(wet1+op);}
        if (flag2==2){System.out.println(wet2+op);}
        //System.out.println("温度输出是"+terminal.getFlag1());
        //System.out.println("湿度输出是"+terminal.getFlag2());

        kieSession.dispose();
    }

}
