package com.jw.edge.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pages")
@Controller
public class DevicePagesController {
    @GetMapping("/devices")
    public String deviceList(Model model) throws Exception {
        return "device/deviceManagement";
    }

    @GetMapping("/deviceCreate")
    public String deviceCreate(Model model) throws Exception {
        return "device/deviceCreate";
    }

}
