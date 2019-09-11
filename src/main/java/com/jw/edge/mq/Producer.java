package com.jw.edge.mq;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class Producer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMsg(String destinationName, Object message) {
        System.out.println("发送"+destinationName+"消息： " + message);
        Destination destination = new ActiveMQQueue(destinationName);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
