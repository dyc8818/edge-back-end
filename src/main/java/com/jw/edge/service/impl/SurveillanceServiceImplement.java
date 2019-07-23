package com.jw.edge.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.dao.DeviceRepository;
import com.jw.edge.dao.SurveillanceRepository;
import com.jw.edge.entity.Device;
import com.jw.edge.service.SurveillanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SurveillanceServiceImplement implements SurveillanceService{
    @Autowired
    SurveillanceRepository surveillanceRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    private RestTemplate restTemplate;

    private String ip = "202.205.101.151";

    @Override
    public JSONArray getOnlineDevices(){
        String allUrl = "http://"+ip+":48082/api/v1/device";
        JSONArray all = new JSONArray(restTemplate.getForObject(allUrl,JSONArray.class));
        JSONArray idArr = new JSONArray();
        for(int i = 0; i<all.size();i++) {
            JSONObject deviceObj = all.getJSONObject(i);
            String deviceId = deviceObj.getString("id");
            String deviceName = deviceObj.getString("name");
            JSONArray commandsArr = deviceObj.getJSONArray("commands");
            String commandsId= commandsArr.getJSONObject(0).getString("id");
            try {
                String url = "http://"+ip+":48082/api/v1/device/"+deviceId+"/command/"+commandsId;
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

    @Override
    public int getTotalNum(){
        String allUrl = "http://"+ip+":48082/api/v1/device";
        JSONArray all = new JSONArray(restTemplate.getForObject(allUrl,JSONArray.class));
        return all.size();
    }

    @Override
    public JSONObject getDeviceDetail(String id){
        if(id.equals("0")){return new JSONObject();}else {
            String deviceId = id;
            String deviceUrl = "http://"+ip+":48082/api/v1/device/" + deviceId;
            JSONObject deviceObj = new JSONObject(restTemplate.getForObject(deviceUrl, JSONObject.class));
            JSONArray commandsArr = deviceObj.getJSONArray("commands");
            JSONObject result = new JSONObject();
            for (int i = 0; i < commandsArr.size(); i++) {
                String commandsId = commandsArr.getJSONObject(i).getString("id");
                String url = "http://"+ip+":48082/api/v1/device/" + deviceId + "/command/" + commandsId;
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

    @Override
    public JSONArray getExpiringDevice(){
        JSONArray arr = new JSONArray();
        List<Device> list = deviceRepository.findAll();
        for(int i=0;i<list.size();i++){
            try {
                Device device = list.get(i);
                String dateStr = device.getDeviceFailDate();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = simpleDateFormat.parse(dateStr);
                Calendar failDate = Calendar.getInstance();
                Calendar after = Calendar.getInstance();
                failDate.setTime(date);
                after.add(Calendar.MONTH,3);
                if(after.compareTo(failDate)>0){
                    JSONObject deviceObj = new JSONObject();
                    deviceObj.put("name",device.getDeviceName());
                    deviceObj.put("failDate",dateStr);
                    deviceObj.put("description",device.getDeviceDesc());
                    arr.add(deviceObj);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return arr;
    }
}
