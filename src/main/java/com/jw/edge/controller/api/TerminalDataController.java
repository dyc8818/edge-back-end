package com.jw.edge.controller.api;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.web.bind.annotation.*;
import javax.jms.*;

@RequestMapping("/api")
@RestController

public class TerminalDataController {
    public static ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

    public static String name;
    public static int value;

    @PostMapping("/terminaldata")
    @ResponseBody
    public String Terminaldata(@RequestBody JSONObject info){

        JSONArray jsonarray = info.getJSONArray("readings");
        JSONObject jsonpacket= jsonarray.getJSONObject(0);
        int value = jsonpacket.getIntValue("value");
        String name = jsonpacket.getString("name");
        this.value=value;
        this.name=name;
        return "OK";
    }
}
