package com.jw.edge.controller.api;

import com.jw.edge.entity.Device;
import com.jw.edge.service.DeviceService;
import com.jw.edge.util.LayuiTableResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class DeviceController {
    @Autowired
    DeviceService deviceService;


    @GetMapping("/devices")
    @ResponseBody
    public LayuiTableResultUtil<List<Device>> getDevices(@RequestParam Integer page, @RequestParam Integer limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<Device> devices =  deviceService.findAllDevice(pageable);
        LayuiTableResultUtil<List<Device>> devicesTable=new LayuiTableResultUtil<List<Device>>("",devices.getContent(),0,(int)devices.getTotalElements());
        return devicesTable;
    }
    //控制设备激活
    @PutMapping("/changeStatus")
    @ResponseBody
    public String changeStatus(@RequestBody String deviceID){
        Device dev = deviceService.findDeviceByDeviceId(deviceID);
        String currentStatus;
        if(dev.getDeviceStatus() == "on"){
            currentStatus = "off";
        }else {
            currentStatus = "on";
        }
        dev.setDeviceStatus(currentStatus);
        return "current status:"+currentStatus;
    }

    @GetMapping("/devicesByType")
    @ResponseBody
    public LayuiTableResultUtil<List<Device>> getDevicesByType(@RequestParam Integer page, @RequestParam Integer limit, @RequestParam String deviceType) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<Device> devicesByType =  deviceService.findDeviceByDeviceType(deviceType, pageable);
        LayuiTableResultUtil<List<Device>> devicesByTypeTable=new LayuiTableResultUtil<List<Device>>("",devicesByType.getContent(),0,(int)devicesByType.getTotalElements());
        return devicesByTypeTable;
    }



    @PostMapping("/addDevice")
    @ResponseBody
    public Boolean addDevice(@RequestBody Device device) {
        if (device != null) {
            if (deviceService.addDevice(device)) {
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
