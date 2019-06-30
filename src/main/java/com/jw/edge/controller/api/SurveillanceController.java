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
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/surveillances")
    @ResponseBody
    public List<Surveillance> getSurveillance(){
        return surveillanceService.findAll();
    }

    @GetMapping("/surveillance")
    @ResponseBody
    public List<Surveillance> getType(@RequestParam String type){
        return surveillanceService.findByType(type);
    }

    @GetMapping("/somesurveillance")
    @ResponseBody
    public List<Surveillance> getTypeOfValue(@RequestParam String type, @RequestParam int from, @RequestParam int to){
        return surveillanceService.findByTypeAndValue(type,from,to);
    }

    @GetMapping("/surnum")
    @ResponseBody
    public int getSurNum(){
        return surveillanceService.findAll().size();
    }

    @GetMapping("/surdetails")
    @ResponseBody
    public int[] getDetails(){
        String url = "http://202.112.146.168:48080/api/v1/reading/device/temp and humidity device/2";
        JSONArray getReadings = new JSONArray(restTemplate.getForObject(url,JSONArray.class));
        JSONObject j1 = getReadings.getJSONObject(0);
        JSONObject j2 = getReadings.getJSONObject(1);
        int v1 = j1.getIntValue("value");
        int v2 = j2.getIntValue("value");
        int[] result = new int[2];
        if(j1.getString("name").equals("Humidity")){
            result[0] = v1;
            result[1] = v2;
            }else{
            result[1] = v1;
            result[0] = v2;
        }
        return  result;
    }
}
