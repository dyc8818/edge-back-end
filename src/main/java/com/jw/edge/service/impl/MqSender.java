package com.jw.edge.service.impl;

import com.jw.edge.service.MqService;
import org.apache.activemq.command.ActiveMQTopic;

import org.springframework.stereotype.Service;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsMessagingTemplate;
import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class MqSender implements MqService {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public void send(String destinationName, Object message) {
        System.out.println("发送"+destinationName+"消息： " + message);
        Destination destination = new ActiveMQQueue(destinationName);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    @Override
    public void publish(String destinationName, Object message) {
        Destination destination = new ActiveMQTopic(destinationName);
        System.out.println("发布topic消息 " + message);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
