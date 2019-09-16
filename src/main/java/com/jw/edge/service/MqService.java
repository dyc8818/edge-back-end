package com.jw.edge.service;

public interface MqService {
    void send(String destinationName, Object message);
    void publish(String destinationName, Object message);
}
