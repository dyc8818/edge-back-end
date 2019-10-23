package com.jw.edge.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.entity.Command;

public interface CommandService {
    boolean addCommand(Command command);
    boolean deleteCommand(String name);
    JSONArray showAll();
    JSONObject find(String name);
}
