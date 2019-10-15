package com.jw.edge.service;

import com.alibaba.fastjson.JSONObject;
import com.jw.edge.entity.Command;

public interface CommandService {
    boolean addCommand(Command command);
    String deleteCommand(String thisId);
    JSONObject sendGet(Command command);
    void sendPut(Command command, JSONObject jsonObject);
    void sendDelete(Command command);
}
