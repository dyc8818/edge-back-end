package com.jw.edge.service;

import com.alibaba.fastjson.JSONObject;

public interface DataAnalysisService {
    void deviceEventAnalysis(JSONObject deviceEvent);
}
