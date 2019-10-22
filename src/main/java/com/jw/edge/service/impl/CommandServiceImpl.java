package com.jw.edge.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.dao.CommandRepository;
import com.jw.edge.entity.Command;
import com.jw.edge.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommandServiceImpl implements CommandService {
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    RestTemplate restTemplate;
    @Value("${server.edgex}")
    private String ip;

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

    @Override
    public String deleteCommand(String thisId){
        Command find = commandRepository.findByThisId(thisId);
        if(find == null){
            return "不存在该命令";
        }else {
            commandRepository.deleteById(thisId);
            return "删除成功";        }
    }

    @Override
    public JSONObject sendGet(Command command){
        String url = "http://"+ip+":48082/api/v1/device/" + command.getDeviceId() + "/command/" + command.getCommandId();
        try {
            JSONObject getObj = new JSONObject(restTemplate.getForObject(url, JSONObject.class));
            return getObj;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void sendPut(Command command, JSONObject jsonObject){
        String url = "http://"+ip+":48082/api/v1/device/" + command.getDeviceId() + "/command/" + command.getCommandId();
        restTemplate.put(url,jsonObject,String.class);
    }

    @Override
    public void sendDelete(Command command){
        String url = "http://"+ip+":48082/api/v1/device/" + command.getDeviceId() + "/command/" + command.getCommandId();
        restTemplate.delete(url);
    }
}
