package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.entity.Device;
import com.jw.edge.entity.Product;
import com.jw.edge.service.DeviceService;
import com.jw.edge.service.ProfileService;
import com.jw.edge.util.LayuiTableResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

@RequestMapping("/api/device")
@RestController
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    ProfileService profileService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${server.edgex}")
    private String ip;

    @GetMapping("/deviceTypes")
    @ResponseBody
    public List<Product> getdeviceTypes() {
        List<Product> products =  profileService.findAllProduct();
        // LayuiTableResultUtil<List<Product>> productsTable=new LayuiTableResultUtil<List<Product>>();
        return products;
    }
    @GetMapping("/devices")
    @ResponseBody
    public LayuiTableResultUtil<List<Device>> getDevices(@RequestParam Integer page, @RequestParam Integer limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<Device> devices =  deviceService.findAllDevice(pageable);
        LayuiTableResultUtil<List<Device>> devicesTable=new LayuiTableResultUtil<List<Device>>("",devices.getContent(),0,(int)devices.getTotalElements());
        return devicesTable;
    }

    @GetMapping("/list")
    @ResponseBody
    public LayuiTableResultUtil<JSONArray> getEdgeXDevices(){
        String url = "http://"+ip+":48081/api/v1/device";
        JSONArray devices = new JSONArray(restTemplate.getForObject(url,JSONArray.class));
        JSONArray arr = new JSONArray();
        for(int i=0; i<devices.size();i++){
            JSONObject jo = devices.getJSONObject(i);
            try {
                Device device = deviceService.findByName(jo.getString("name"));
                Date date = device.getDeviceCreateTime();
                jo.put("createdTime", date);
            }catch (Exception ignored){}
            String profileName = jo.getJSONObject("profile").getString("name");
            jo.remove("profile");
            jo.put("profile",profileName);
            String serviceName = jo.getJSONObject("service").getString("name");
            jo.remove("service");
            jo.put("service",serviceName);
            arr.add(jo);
        }
        return new LayuiTableResultUtil<>("",arr,0,devices.size());
    }

    //控制设备激活
    @PutMapping("/changeStatus")
    @ResponseBody
    public int changeStatus(@RequestBody JSONObject deviceID){
        JSONObject obj = new JSONObject(deviceID);//将json字符串转换为json对象

        System.out.println("deviceid:"+deviceID);
        System.out.println("deviceid:"+deviceID.getString("deviceId"));
        Device dev = deviceService.findDeviceByDeviceId(deviceID.getString("deviceId"));
        int currentStatus;
        if(dev.getDeviceStatus() == 1){
            currentStatus = 0;
        }else {
            currentStatus = 1;
        }
        System.out.println("DeviceController currentstatus:"+currentStatus);
        deviceService.changeDeviceStatus(dev, currentStatus);
        return currentStatus;
    }

    @GetMapping("/devicesByType")
    @ResponseBody
    public LayuiTableResultUtil<List<Device>> getDevicesByType(@RequestParam Integer page, @RequestParam Integer limit, @RequestParam String deviceType) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<Device> devicesByType =  deviceService.findDeviceByDeviceType(deviceType, pageable);
        LayuiTableResultUtil<List<Device>> devicesByTypeTable=new LayuiTableResultUtil<List<Device>>("",devicesByType.getContent(),0,(int)devicesByType.getTotalElements());
        return devicesByTypeTable;
    }

//    @PostMapping("/addDevice")
//    @ResponseBody
//    public Boolean addDevice(@RequestBody Device device) {
//        if (device != null) {
//            if (deviceService.addDevice(device)) {
//                return true;
//            }
//        }
//        return false;
//    }

    @PostMapping("/json")
    @ResponseBody
    public String addDevice(@RequestBody JSONObject jsonObject) {
        try{
            String url = "http://"+ip+":48081/api/v1/device";
            String result = restTemplate.postForObject(url,jsonObject,String.class);
            System.out.println("添加设备成功 id="+result);
            Device device = new Device();
            device.setDeviceName(jsonObject.getString("name"));
            deviceService.addDevice(device);
            return result;
        }catch (HttpClientErrorException e){
            return "失败";
        }

    }

    @DeleteMapping("/device")
    @ResponseBody
    public LayuiTableResultUtil<String> deleteDevice(@RequestBody Device device){
        // System.out.println(product.getProductId());
        String deleteStatus = deviceService.deleteDevice(device.getDeviceId());
        return  new LayuiTableResultUtil<String>("",deleteStatus,0,1);
    }
}
