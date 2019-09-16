package com.jw.edge.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.stereotype.Service;

import javax.jms.ConnectionFactory;

@Service
public class MqReceiver {
    @Bean
    JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory){
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

    @JmsListener(destination = "test.queue")
    public void receiveTest(Object msg) {
        System.out.println("收到消息： " + msg);
    }

    @JmsListener(destination = "test.topic", containerFactory = "myJmsContainerFactory")
    public void subscribeTest(JSONObject msg) {
        System.out.println("收到订阅的消息:" + msg.get("veteranFix"));}
}
