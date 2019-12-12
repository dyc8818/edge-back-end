package com.jw.edge.controller.pages;

import com.jw.edge.entity.Device;
import com.jw.edge.service.DeviceService;
import com.jw.edge.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pages")
@Controller
public class DevicePagesController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    ProfileService productService;

    @GetMapping("/devices")
    public String deviceList(Model model) throws Exception {
        return "device/deviceManagement";
    }

    @GetMapping("/addDevice")
    public String deviceCreate(Model model) throws Exception {
        return "device/addDevice";
    }

    @GetMapping("/deviceDetails")
    public String deviceDetails(Model model, String deviceId) throws Exception {
        Device device = deviceService.findDeviceByDeviceId(deviceId);
        model.addAttribute("device", device);
        return "device/deviceDetails";
    }
}
