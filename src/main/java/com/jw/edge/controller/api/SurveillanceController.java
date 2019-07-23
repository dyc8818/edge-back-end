package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.entity.Surveillance;
import com.jw.edge.service.SurveillanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequestMapping("/api")
@RestController
public class SurveillanceController {
    @Autowired
    SurveillanceService surveillanceService;


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
}
