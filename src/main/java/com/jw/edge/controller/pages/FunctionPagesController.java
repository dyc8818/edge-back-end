package com.jw.edge.controller.pages;

import com.jw.edge.entity.Function;
import com.jw.edge.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pages")
@Controller
public class FunctionPagesController {
    @Autowired
    FunctionService functionService;

    @GetMapping("/functions")
    public String functionList(Model model) throws Exception {
        return "product/functionManagement";
    }

    @GetMapping("/functionCreate")
    public String functionCreate(Model model) throws Exception {
        return "product/functionCreate";
    }

    @GetMapping("/functionDetails")
    public String functionDetails(Model model, String functionId) throws Exception {
        System.out.println(functionId);
        Function function = functionService.findFunctionByFunctionId(functionId);
        System.out.println(function.getFunctionName());
        model.addAttribute("function", function);
        return "product/functionDetails";
    }
}
