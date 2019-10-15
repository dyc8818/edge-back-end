package com.jw.edge.util.dataAnalysis;

import com.alibaba.fastjson.JSONObject;
import com.jw.edge.controller.api.DataAnalysisController;
import com.jw.edge.service.MqService;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;
import java.util.Map;

public class Raw implements Runnable {
    @Autowired
    MqService mqService;

    private String name;
    private String destination;
    private String target;
    private ConnectionFactory connectionFactory = DataAnalysisController.connectionFactory;

    public Raw(String name, String destination, String target) {
        this.name = name;
        this.destination = destination;
        this.target = target;
    }

    public JSONObject getInfo(){
        JSONObject info = new JSONObject();
        info.put("name",this.name);
        info.put("destination",this.destination);
        info.put("target",this.target);
        return info;
    }

    @Override
    public void run() {
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(this.destination);
            MessageConsumer consumer = session.createConsumer(destination);
            while (true) {
                try {
                    ActiveMQMapMessage activeMQMapMessage = (ActiveMQMapMessage) consumer.receive();
                    Map content = activeMQMapMessage.getContentMap();
                    JSONObject msg = new JSONObject(content);
                    System.out.println("收到"+destination+msg);

                }catch (Exception e){e.printStackTrace();break;}
            }
            connection.close();
        }catch (Exception e){e.printStackTrace();}
    }
}
