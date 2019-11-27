package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.entity.Device;
import com.jw.edge.entity.Product;
import com.jw.edge.service.DeviceService;
import com.jw.edge.service.ProductServie;
import com.jw.edge.util.LayuiTableResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    ProductServie productServie;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${server.edgex}")
    private String ip;

    @GetMapping("/deviceTypes")
    @ResponseBody
    public List<Product> getdeviceTypes() {
        List<Product> products =  productServie.findAllProduct();
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

    @GetMapping("/devices/edgex")
    @ResponseBody
    public LayuiTableResultUtil<JSONArray> getEdgeXDevices(){
        String url = "http://"+ip+":48081/api/v1/device";
        JSONArray devices = new JSONArray(restTemplate.getForObject(url,JSONArray.class));
        JSONArray arr = new JSONArray();
        for(int i=0; i<devices.size();i++){
            JSONObject jo = devices.getJSONObject(i);
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

    @PostMapping("/addDevice")
    @ResponseBody
    public boolean addDevice(@RequestBody Device device) {
        if (device != null) {
            if (deviceService.addDevice(device)) {
                JSONObject jo = new JSONObject();
                jo.put("name",device.getDeviceName());
                jo.put("method","POST");
                jo.put("protocol",device.getDeviceProtocol());
                jo.put("address",device.getDeviceAddress());
                jo.put("port",device.getDeviceAddressPort());
                System.out.println(jo);


                String url1 = "http://"+ip+":48081/api/v1/deviceprofile/uploadfile";
                String url3 = "http://"+ip+":48081/api/v1/addressable";
                String url2 = "http://"+ip+":48081/api/v1/device";
                try {
                    //File file = new File ("deviceProfile/temp.profile.yml");
                    RestTemplate restTemplate = new RestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    MediaType type = MediaType.parseMediaType("multipart/form-data");
                    headers.setContentType(type);
                    FileSystemResource fileSystemResource = new FileSystemResource("src/main/resources/deviceProfile/temp.profile.yml");
                    MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
                    form.add("file", fileSystemResource);
                    form.add("filename","temp.profile.yml");
                    HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);

                    String submitProfile = restTemplate.postForObject(url1,files,String.class);
                    try {
                        JSONArray submitAddressable = new JSONArray(restTemplate.postForObject(url2,jo,JSONArray.class));
                        try {
                            JSONObject jo2 = new JSONObject();
                            jo2.put("description","temperature and humidity sensor");
                            jo2.put("name",device.getDeviceName());
                            jo2.put("adminState","unlocked");
                            jo2.put("operatingState","enabled");
                            JSONObject jo3 = new JSONObject();
                            jo3.put("name",jo.get("name"));
                            jo2.put("addressable",jo3);
                            List<String> li = new ArrayList<String>();
                            jo2.put("labels",li);
                            jo2.put("location",null);
                            JSONObject jo4 = new JSONObject();
                            jo4.put("name","edgex-device-modbus");
                            jo2.put("service",jo4);
                            JSONObject jo5 = new JSONObject();
                            jo5.put("name","temp.profile");
                            jo2.put("profile",jo5);
                            JSONArray submitProfile2 = new JSONArray(restTemplate.postForObject(url3,jo2,JSONArray.class));
                        }catch (Exception e3){
                           System.out.println("e3"+e3);
                        }
                    }catch (Exception e2){
                        System.out.println("e2"+e2);
                    }
                }catch (Exception e1){
                    System.out.println("e1"+e1);
                }
                return true;
            }
        }
        return false;
    }

    @DeleteMapping("/device")
    @ResponseBody
    public LayuiTableResultUtil<String> deleteDevice(@RequestBody Device device){
        // System.out.println(product.getProductId());
        String deleteStatus = deviceService.deleteDevice(device.getDeviceId());
        return  new LayuiTableResultUtil<String>("",deleteStatus,0,1);
    }
}
