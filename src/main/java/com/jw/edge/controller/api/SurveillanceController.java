package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.service.MqService;
import com.jw.edge.service.SurveillanceService;
import com.jw.edge.util.LayuiTableResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api")
@RestController
public class SurveillanceController {
    @Autowired
    SurveillanceService surveillanceService;
    @Autowired
    MqService mqService;

    @GetMapping("/mqtest")
    @ResponseBody
    public String mqTest(){
        JSONObject command = new JSONObject();
        command.put("name","name1");
        command.put("commandId","cid");
        command.put("commandName","cn");
        command.put("commandType","get");
        command.put("deviceId","did");
        command.put("deviceName","dn");
        command.put("jsonObject","jo");
        command.put("jsonArray","ja");
        mqService.publish("run.command",command);
        mqService.publish("test2",command);
        return "提交发送";
    }

    @GetMapping("/surnum")
    @ResponseBody
    public int getSurNum(){
        return surveillanceService.getOnlineDevices().size();
    }

    @GetMapping("/surid")
    @ResponseBody
    public JSONArray getId(){
        return surveillanceService.getOnlineDevices();
    }

    @GetMapping("/totalnum")
    @ResponseBody
    public int getTotalNum(){
        return surveillanceService.getTotalNum();
    }

    @GetMapping("/surdetails")
    @ResponseBody
    public JSONObject getDetails(@RequestParam String id){
        return surveillanceService.getDeviceDetail(id);
    }

    @GetMapping("/expiringnum")
    @ResponseBody
    public int getExpiringNum(){
        return surveillanceService.getExpiringDevice().size();
    }

    @GetMapping("/expiringdetails")
    @ResponseBody
    public LayuiTableResultUtil getExpiring(@RequestParam Integer page, @RequestParam Integer limit){
        LayuiTableResultUtil<JSONArray> table = new LayuiTableResultUtil<>("",surveillanceService.getExpiringDevice(),0,surveillanceService.getExpiringDevice().size());
        return table;
    }

    @GetMapping("/regnum")
    @ResponseBody
    public int getRegNum(){
        return surveillanceService.getRegNum();
    }

    @GetMapping("/regdetails")
    @ResponseBody
    public LayuiTableResultUtil<JSONArray> getReg(@RequestParam Integer page, @RequestParam Integer limit){
        LayuiTableResultUtil<JSONArray> table = new LayuiTableResultUtil<>("",surveillanceService.getRegDevice(),0,surveillanceService.getRegDevice().size());
        return table;
    }

    @GetMapping("/agedis")
    @ResponseBody
    public JSONObject getAgeDistribution(){        return surveillanceService.getAge();}
}
