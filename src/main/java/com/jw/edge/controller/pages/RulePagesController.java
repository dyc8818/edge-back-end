package com.jw.edge.controller.pages;

import com.jw.edge.entity.Rule;
import com.jw.edge.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pages")
@Controller
public class RulePagesController {
    @Autowired
    RuleService ruleService;

    @GetMapping("/rules")
    public String ruleList(Model model) throws Exception {
        return "rule/ruleManagement";
    }

    @GetMapping("/ruleCreate")
    public String ruleCreate(Model model) throws Exception {
        return "rule/ruleCreate";
    }

    @GetMapping("/ruleDetails")
    public String ruleDetails(Model model, String ruleId) throws Exception {
        Rule rule = ruleService.findRuleByRuleId(ruleId);
        model.addAttribute("rule", rule);
        return "rule/ruleDetails";
    }

}
