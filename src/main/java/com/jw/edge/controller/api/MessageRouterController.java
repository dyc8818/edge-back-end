package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.service.MqService;
import com.jw.edge.util.LayuiTableResultUtil;
import com.jw.edge.util.dataAnalysis.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.*;
import javax.jms.*;
import java.util.Date;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RequestMapping("/api/message")
@RestController
public class MessageRouterController {
    @Autowired
    MqService mqService;
    public static JSONArray status = new JSONArray();
    public static ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,50,3, TimeUnit.SECONDS,new SynchronousQueue<>());

    @PostMapping("/analysis/raw")
    @ResponseBody
    public String newRaw(@RequestBody JSONObject info){
        info.put("createTime", new Date().toString());
        boolean existed = false;
        for(int i=0; i<status.size(); i++){
            if(info.getString("name").equals(status.getJSONObject(i).getString("name"))){existed = true;}
        }
        if(!existed){
            try{
            Raw raw = new Raw(info.getString("name"),info.getString("incomingQueue"),info.getString("outgoingQueue"));
            status.add(info);
            threadPoolExecutor.execute(raw);
            return "启动成功~~";}catch (Exception e){return "参数错误!";}
        }else {
            return "名称重复！";
        }
    }

    @JmsListener(destination = "device/readings", containerFactory = "topicContainerFactory")
    public void subscribeTest(JSONObject msg) {
        System.out.println("收到edegx读数" + msg);
        mqService.publish("edgex.readings",msg);
    }

    @GetMapping("/analysis")
    @ResponseBody
    public JSONArray allInfo(){
        return status;
    }

    @GetMapping("/analysis/table")
    @ResponseBody
    public LayuiTableResultUtil<JSONArray> allInfoTable(@RequestParam Integer page, @RequestParam Integer limit){
        LayuiTableResultUtil<JSONArray> table = new LayuiTableResultUtil<>("",status,0,status.size());
        return table;
    }

    @DeleteMapping("/analysis")
    @ResponseBody
    public boolean delete(@RequestBody JSONObject info){
        return status.remove(info);
    }

    public static boolean check(String name){
        boolean flag = false;
        for (int i = 0; i < status.size(); i++){
            if(name.equals(status.getJSONObject(i).getString("name"))){
                flag = true;
            }
        }
        return flag;
    }

}
