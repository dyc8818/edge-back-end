package com.jw.edge.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public interface SurveillanceService {
    JSONArray getOnlineDevices();
    int getTotalNum();
    JSONObject getDeviceDetail(String id);
    JSONArray getExpiringDevice();
    int getRegNum();
    JSONArray getRegDevice();
}
