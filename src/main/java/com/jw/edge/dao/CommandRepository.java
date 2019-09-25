package com.jw.edge.dao;

import com.jw.edge.entity.Command;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends MongoRepository<Command,String> {
    Command findByDeviceName(String name);
    Command findByThisId(String id);
    Command findByEdgexId(String id);
    Command findByDeviceId(String id);
    Command findByDeviceNameAndCommandType(String name, int type);
    Command findByDeviceNameAndCommandName(String device, String command);
    Command findByDeviceNameAndCommandTypeAndCommandName(String device, int type, String command);
}
