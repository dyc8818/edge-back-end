package com.jw.edge.service.impl;

import com.jw.edge.entity.MessageRouting;
import com.jw.edge.service.DeviceService;
import com.jw.edge.service.MessageRoutingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageRoutingServiceImplTest {

    @Autowired
    MessageRoutingService messageRoutingService;
    @Autowired
    DeviceService deviceService;

    @Test
    public void findAllMessageRouting() {
        Pageable pageable = PageRequest.of(0, 10);
        System.out.println(messageRoutingService.findAllMessageRouting(pageable).getContent().get(0).getMessageRoutingAdress());
    }

    @Test
    public void addMessageRouting() {
        MessageRouting messageRouting = new MessageRouting();
        messageRouting.setMessageRoutingAdress("服务器");
        messageRouting.setMessageRoutingType("mq");
        messageRouting.setDeviceId(deviceService.findAllDevice().get(0).getDeviceId());
        messageRoutingService.addMessageRouting(messageRouting);
    }
}