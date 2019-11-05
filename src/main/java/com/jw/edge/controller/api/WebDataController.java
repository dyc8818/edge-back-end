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
    public static int parameter;
    public static String symbol;
    public static String operation;

    @PostMapping("/webdata")
    @ResponseBody
    public int Webdata(@RequestBody JSONObject info){

        int parameter = info.getIntValue("ruleParaThreshold");
        String name = info.getString("rulePara");
        String symbol = info.getString("ruleJudge");
        String operation = info.getString("ruleExecute");

        this.name=name;
        this.parameter=parameter;
        this.symbol=symbol;
        this.operation=operation;


        Test();

        return parameter;
    }


    @Test
    public void Test() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("all-rules");

        Terminal terminal = new Terminal();

        kieSession.insert(terminal);

        kieSession.fireAllRules();//通知规则引擎执行规则

        System.out.println("温度输出是"+terminal.getFlag1());
        System.out.println("湿度输出是"+terminal.getFlag2());

        kieSession.dispose();
    }

}
