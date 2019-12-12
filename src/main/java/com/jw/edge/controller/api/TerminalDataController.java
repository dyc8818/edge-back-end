package com.jw.edge.controller.api;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.entity.Terminal;
import com.jw.edge.service.MqService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.*;
import javax.jms.*;


@RequestMapping("/api")
@RestController

public class TerminalDataController {
    @Autowired
    MqService mqService;

    public static String name;
    public static int value;

    @PostMapping("/terminaldata")
    @ResponseBody
    public String msgTest(@RequestBody JSONObject json1)
    {
        mqService.publish("rules_terminal",json1);
        return "发送成功";
    }

    @JmsListener(destination = "rules_terminal", containerFactory = "topicContainerFactory")
    public void Terminaldata( JSONObject info ){
        System.out.println("规则引擎收到消息"+info);
        JSONArray jsonarray = info.getJSONArray("readings");
        JSONObject jsonpacket= jsonarray.getJSONObject(0);
        int value = jsonpacket.getIntValue("value");
        String name = jsonpacket.getString("name");

        this.value=value;
        this.name=name;
        try{
        Test();}catch (Exception e){}
        System.out.println("传感器参数---"+value);
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

        int OutThreshold=WebDataController.threshold;
        String OutName=" ";
        String OutSymbol=" ";
        {
            if(WebDataController.parameterName.equals("0")){OutName = "温度";}
            if(WebDataController.parameterName.equals("1")){OutName = "湿度";}
        }
        {
            if(WebDataController.symbol.equals("0")){OutSymbol = ">";}
            if(WebDataController.symbol.equals("1")){OutSymbol = "<";}
            if(WebDataController.symbol.equals("2")){OutSymbol = "=";}
        }

        if (flag1!=0 || flag2!=0)
        {
            System.out.println("输出条件为："+OutName+OutSymbol+OutThreshold);
            JSONObject alert = new JSONObject();
            alert.put("content",OutName+OutSymbol+OutThreshold+"!");
            mqService.publish("notice",alert);
            if (WebDataController.operation.equals("1"))
            {
                JSONObject json = new JSONObject();
                json.put("name",WebDataController.service);
                mqService.publish("run.command",json);
            }
        }
        kieSession.dispose();
    }

}
