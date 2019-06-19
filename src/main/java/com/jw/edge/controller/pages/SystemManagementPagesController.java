package com.jw.edge.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pages")
@Controller
public class SystemManagementPagesController {
    @GetMapping("/monitor")
    public String systemManagementList(Model model) throws Exception {
        return "systemManagement/monitor";
    }

    @GetMapping("/logManagement")
    public String logManagement(Model model) throws Exception {
        return "systemManagement/logManagement";
    }

}
