package com.jw.edge.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/pages")
@Controller
public class ScenarioPagesController {
    @GetMapping("/scenarioManagement")
    public String getList(Model model) throws Exception {
        return "/scenarioCommand/scenarioCommandManagement";
    }

    @GetMapping("/commandCreate")
    public String creatCommand(Model model) throws Exception {
        return "scenarioCommand/commandCreate";
    }

    @GetMapping("/scenarioCreate")
    public String createScenario(Model model) throws Exception {
        return "scenarioCommand/scenarioCreate";
    }
}
