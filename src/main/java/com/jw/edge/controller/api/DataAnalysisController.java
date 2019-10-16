package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.util.dataAnalysis.Raw;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.web.bind.annotation.*;
import javax.jms.*;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RequestMapping("/api")
@RestController
public class DataAnalysisController {
    public static JSONArray status = new JSONArray();
    public static ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,50,3, TimeUnit.SECONDS,new SynchronousQueue<>());

    @PostMapping("/analysis")
    @ResponseBody
    public String newAnalysis(@RequestBody JSONObject info){
        boolean existed = false;
        for(int i=0; i<status.size(); i++){
            if(info.getString("name").equals(status.getJSONObject(i).getString("name"))){existed = true;}
        }
        if(!existed){
            Raw raw = new Raw(info.getString("name"),info.getString("destination"),info.getString("target"));
            status.add(info);
            threadPoolExecutor.execute(raw);
            return "启动成功~~";
        }else {
            return "名称重复！";
        }
    }

    @GetMapping("/analysis")
    @ResponseBody
    public JSONArray allInfo(){
        return status;
    }

    @DeleteMapping("/analysis")
    @ResponseBody
    public boolean delete(@RequestBody JSONObject info){
        return status.remove(info);
    }

}
