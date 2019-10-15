package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONObject;


import com.jw.edge.util.dataAnalysis.Raw;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.web.bind.annotation.*;
import javax.jms.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RequestMapping("/api")
@RestController
public class DataAnalysisController {
    static ConcurrentHashMap status = new ConcurrentHashMap();
    public static ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,180, TimeUnit.SECONDS,new SynchronousQueue<>());

    @PostMapping("/analysis")
    @ResponseBody
    public void newAnalysis(@RequestBody JSONObject newAnalysis){
        Raw raw = new Raw(newAnalysis.getString("name"),newAnalysis.getString("destination"),newAnalysis.getString("target"));
        threadPoolExecutor.execute(raw);
    }

}
