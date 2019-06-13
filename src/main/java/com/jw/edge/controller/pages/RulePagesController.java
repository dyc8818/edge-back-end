package com.jw.edge.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pages")
@Controller
public class RulePagesController {
    @GetMapping("/rules")
    public String ruleList(Model model) throws Exception {
        return "rule/ruleManagement";
    }

    @GetMapping("/ruleCreate")
    public String ruleCreate(Model model) throws Exception {
        return "rule/ruleCreate";
    }

}
