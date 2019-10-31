package com.jw.edge.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jw.edge.service.DataAnalysisService;
import com.jw.edge.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataAnalysisImpl implements DataAnalysisService {
    @Autowired
    MqService mqService;
    @Override
    public void deviceEventAnalysis(JSONObject deviceEvent){
        System.out.println("数据分析接收"+deviceEvent);
        JSONObject deviceReadings = deviceEvent.getJSONObject("readings");
        mqService.publish("device.readings",deviceReadings);
        System.out.println("数据分析发送"+deviceReadings);
    }
}
