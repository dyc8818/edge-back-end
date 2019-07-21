package com.jw.edge.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pages")
@Controller
public class MessageRoutingController {
    @GetMapping("/messageRouting")
    public String productList(Model model) throws Exception {
        return "/messageRouting/messageRoutingManagement";
    }
}
