package com.jw.edge.controller.pages;

import com.jw.edge.entity.Device;
import com.jw.edge.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/pages")
@Controller
public class MessageRoutingPagesController {
    @Autowired
    DeviceService deviceService;

    @GetMapping("/messageRouting")
    public String messageRoutingList(Model model) throws Exception {
        return "/messageRouting/messageRoutingManagement";
    }

    @GetMapping("/messageRoutingCreate")
    public String messageRoutingCreate(Model model) throws Exception {
        List<Device> deviceList = deviceService.findAllDevice();
        model.addAttribute("deviceList",deviceList);
        return "messageRouting/messageRoutingCreate";
    }
}
