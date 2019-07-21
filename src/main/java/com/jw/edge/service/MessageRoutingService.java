package com.jw.edge.service;

import com.jw.edge.entity.MessageRouting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageRoutingService {
    public Page<MessageRouting> findAllMessageRouting(Pageable pageable);

    public boolean addMessageRouting(MessageRouting messageRouting);
}
