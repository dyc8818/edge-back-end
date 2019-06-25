package com.jw.edge.controller.api;

import com.jw.edge.entity.Rule;
import com.jw.edge.service.RuleService;
import com.jw.edge.util.LayuiTableResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class RuleController {
    @Autowired
    RuleService ruleService;

    @GetMapping("/rules")
    @ResponseBody
    public LayuiTableResultUtil<List<Rule>> Rules(@RequestParam Integer page, @RequestParam Integer limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<Rule> rules =  ruleService.findAllRule(pageable);
        LayuiTableResultUtil<List<Rule>> rulesTable=new LayuiTableResultUtil<List<Rule>>("",rules.getContent(),0,(int)rules.getTotalElements());
        return rulesTable;
    }

    @PostMapping("/rule")
    @ResponseBody
    public Boolean addRule(@RequestBody Rule rule) {
        if (rule != null) {
            if (ruleService.addRule(rule)) {
                return true;
            }
        }
        return false;
    }

    @DeleteMapping("/rule")
    @ResponseBody
    public LayuiTableResultUtil<String> deleteRule(@RequestBody Rule rule){
        // System.out.println(rule.getRuleId());
        String deleteStatus = ruleService.deleteRule(rule.getRuleId());
        return  new LayuiTableResultUtil<String>("",deleteStatus,0,1);
    }
}
