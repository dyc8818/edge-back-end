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
        int num = 0;
        String allUrl = "http://202.205.101.151:48082/api/v1/device";
        JSONArray all = new JSONArray(restTemplate.getForObject(allUrl,JSONArray.class));
        for(int i = 0; i<all.size();i++) {
            JSONObject deviceObj = all.getJSONObject(i);
            String deviceId = deviceObj.getString("id");
            JSONArray commandsArr = deviceObj.getJSONArray("commands");
            String commandsId= commandsArr.getJSONObject(0).getString("id");
            try {
                String url = "http://202.205.101.151:48082/api/v1/device/"+deviceId+"/command/"+commandsId;
                JSONObject get = new JSONObject(restTemplate.getForObject(url, JSONObject.class));
                num++;
            } catch (Exception e) {
            }
        }
        return num;
    }

    @GetMapping("/surid")
    @ResponseBody
    public JSONArray getId(){
        String allUrl = "http://202.205.101.151:48082/api/v1/device";
        JSONArray all = new JSONArray(restTemplate.getForObject(allUrl,JSONArray.class));
        JSONArray idArr = new JSONArray();
        for(int i = 0; i<all.size();i++) {
            JSONObject deviceObj = all.getJSONObject(i);
            String deviceId = deviceObj.getString("id");
            String deviceName = deviceObj.getString("name");
            JSONArray commandsArr = deviceObj.getJSONArray("commands");
            String commandsId= commandsArr.getJSONObject(0).getString("id");
            try {
                String url = "http://202.205.101.151:48082/api/v1/device/"+deviceId+"/command/"+commandsId;
                JSONObject get = new JSONObject(restTemplate.getForObject(url, JSONObject.class));
                JSONObject id = new JSONObject();
                id.put("id",deviceId);
                id.put("name",deviceName);
                idArr.add(id);
            } catch (Exception e) {
            }
        }
        return idArr;
    }

    @GetMapping("/totalnum")
    @ResponseBody
    public int getTotalNum(){
        String allUrl = "http://202.205.101.151:48082/api/v1/device";
        JSONArray all = new JSONArray(restTemplate.getForObject(allUrl,JSONArray.class));
        return all.size();
    }

    @GetMapping("/surdetails")
    @ResponseBody
    public JSONObject getDetails(@RequestParam String id){
        if(id.equals("0")){return new JSONObject();}else {
            String deviceId = id;
            String deviceUrl = "http://202.205.101.151:48082/api/v1/device/" + deviceId;
            JSONObject deviceObj = new JSONObject(restTemplate.getForObject(deviceUrl, JSONObject.class));
            JSONArray commandsArr = deviceObj.getJSONArray("commands");
            JSONObject result = new JSONObject();
            for (int i = 0; i < commandsArr.size(); i++) {
                String commandsId = commandsArr.getJSONObject(i).getString("id");
                String url = "http://202.205.101.151:48082/api/v1/device/" + deviceId + "/command/" + commandsId;
                try {
                    JSONObject commandObj = new JSONObject(restTemplate.getForObject(url, JSONObject.class));
                    JSONObject reading = commandObj.getJSONArray("readings").getJSONObject(0);
                    result.put(reading.getString("name"), reading.getIntValue("value"));
                } catch (Exception e) {
                }
            }
            return result;
        }
    }
}
