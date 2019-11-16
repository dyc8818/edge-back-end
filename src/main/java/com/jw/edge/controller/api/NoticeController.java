package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;
import java.util.Map;

@RequestMapping("/api/notice")
@RestController
public class NoticeController {
    private ConnectionFactory connectionFactory = MessageRouterController.connectionFactory;

    @GetMapping("/alert")
    public String getAlert(){
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("notice");
            MessageConsumer consumer = session.createConsumer(destination);
            while (true) {
                try {
                    ActiveMQMapMessage activeMQMapMessage = (ActiveMQMapMessage) consumer.receive();
                    Map content = activeMQMapMessage.getContentMap();
                    JSONObject msg = new JSONObject(content);
                    String alert = msg.getString("content");
                    connection.close();
                    return alert;
                }catch (Exception e){e.printStackTrace();return null;}
            }
        }catch (Exception e){e.printStackTrace();return null;}
    }
}
