package com.jw.edge.service.impl;

import com.jw.edge.dao.CommandRepository;
import com.jw.edge.entity.Command;
import com.jw.edge.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandServiceImpl implements CommandService {
    @Autowired
    CommandRepository commandRepository;

    @Override
    public boolean addCommand(Command command){
        Command findCommand = commandRepository.findByDeviceNameAndCommandTypeAndCommandName(command.getDeviceName(), command.getCommandType(), command.getCommandName());
        if(findCommand == null){
            commandRepository.save(command);
            return true;
        }else{
            return false;
        }
    }
}
