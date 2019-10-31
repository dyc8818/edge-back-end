package com.jw.edge.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/pages")
@Controller
public class MessageRoutingPagesController {

    @GetMapping("/messageRouting")
    public String messageRoutingList(Model model) throws Exception {
        return "/messageRouting/messageRoutingManagement";
    }

    @GetMapping("/messageRoutingCreate")
    public String messageRoutingCreate(Model model) throws Exception {
        return "messageRouting/messageRoutingCreate";
    }
}
