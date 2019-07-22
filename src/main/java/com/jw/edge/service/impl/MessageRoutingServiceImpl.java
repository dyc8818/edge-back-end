package com.jw.edge.service.impl;

import com.jw.edge.dao.MessageRoutingRepository;
import com.jw.edge.entity.MessageRouting;
import com.jw.edge.service.DeviceService;
import com.jw.edge.service.MessageRoutingService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageRoutingServiceImpl implements MessageRoutingService
{
    @Autowired
    MessageRoutingRepository messageRoutingRepository;

    @Autowired
    DeviceService deviceService;

    @Override
    public Page<MessageRouting> findAllMessageRouting(Pageable pageable) {
        return messageRoutingRepository.findAll(pageable);
    }

    @Override
    public boolean addMessageRouting(MessageRouting messageRouting) {
        MessageRouting messageRouting1 = messageRoutingRepository.findProductByMessageRoutingId(messageRouting.getMessageRoutingId());
        if(messageRouting1==null){
            MessageRouting messageRoutingSaved =messageRoutingRepository.save(messageRouting);
            ObjectId objectId =new  ObjectId(messageRoutingSaved.getMessageRoutingId());
            messageRoutingSaved.setMessageRoutingCreateTime(objectId.getDate());
            messageRoutingSaved.setDeviceName(deviceService.findDeviceByDeviceId(messageRoutingSaved.getDeviceId()).getDeviceName());
            messageRoutingRepository.save(messageRoutingSaved);
            return true;
        }
        return false;
    }
}
