package com.jw.edge.service.impl;

import com.jw.edge.service.MqService;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.stereotype.Service;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;

@Service
public class MqServiceImpl implements MqService {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Bean
    JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory){
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

    @Override
    public void sendMsg(String destinationName, Object message) {
        System.out.println("发送"+destinationName+"消息： " + message);
        Destination destination = new ActiveMQQueue(destinationName);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    @Override
    @JmsListener(destination = "test.queue")
    public void receiveTest(String msg) {
        System.out.println("收到消息： " + msg);
    }

    @Override
    public void publish(String destinationName, Object message) {
        Destination destination = new ActiveMQTopic(destinationName);
        System.out.println("发布topic消息 " + message);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    @Override
    @JmsListener(destination = "test.topic", containerFactory = "myJmsContainerFactory")
    public void subscribe(String msg) {
        System.out.println("收到订阅的消息" + msg);
    }
}
