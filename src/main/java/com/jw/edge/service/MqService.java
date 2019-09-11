package com.jw.edge.service;

public interface MqService {
    void sendMsg(String destinationName, Object message);
    void receiveTest(String msg);
    void publish(String destinationName, Object message);
    void subscribe(String msg);
}
