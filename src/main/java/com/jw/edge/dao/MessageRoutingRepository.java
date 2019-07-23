package com.jw.edge.dao;

import com.jw.edge.entity.MessageRouting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRoutingRepository extends MongoRepository<MessageRouting,String> {
    public MessageRouting findProductByMessageRoutingName(String messageRoutingName);
    public Page<MessageRouting> findAll(Pageable pageable);
    public MessageRouting findProductByMessageRoutingId(String messageRoutingId);
}
