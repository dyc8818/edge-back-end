package com.jw.edge.controller.pages;

import com.jw.edge.entity.Function;
import com.jw.edge.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class FunctionPagesController {
    @Autowired
    FunctionService functionService;

    @GetMapping("/functions")
    public String functionList(Model model) throws Exception {
        return "function/functionManagement";
    }

    @GetMapping("/functionCreate")
    public String functionCreate(Model model) throws Exception {
        return "function/functionCreate";
    }

    @GetMapping("/functionDetails")
    public String functionDetails(Model model, String functionName) throws Exception {
        Function function = functionService.findFunctionByFunctionName(functionName);
        model.addAttribute("function", function);
        return "function/functionDetails";
    }
}
