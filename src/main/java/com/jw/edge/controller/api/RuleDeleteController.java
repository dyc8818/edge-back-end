package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController

public class RuleDeleteController {

    public static String [] ruleName= new String[10];

    @PostMapping("/ruleDelete")
    @ResponseBody
    public void ruleDelete(@RequestBody JSONObject info)
    {
        String ruleName = info.getString("ruleName");

        for (int i = 0; i<10; i++) {
            if ((WebDataController.ruleName[i]).equals(ruleName)) {
                WebDataController.parameterName[i] = null;
                WebDataController.threshold[i] = 0;
                WebDataController.symbol[i] = null;
                WebDataController.operation[i] = null;
                WebDataController.service[i] = null;
                break;
            }
        }
    }

}
