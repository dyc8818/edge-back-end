package com.jw.edge.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;


@Service
public class Subscriber {

    @JmsListener(destination = "test.topic", containerFactory = "myJmsContainerFactory")
    public void subscribe(String text) {
        System.out.println("===========<<<<<<<<收到订阅的消息" + text);
    }
}