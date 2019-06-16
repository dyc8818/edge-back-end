package com.jw.edge.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pages")
@Controller
public class LoginAndOutPagesController {

    @GetMapping("/login")
    public String login(Model model) throws Exception {
        return "loginAndOut/login";
    }

    @GetMapping("/register")
    public String register(Model model) throws Exception {
//        System.out.println("!!!!!!!!!!");
        return "loginAndOut/register";
    }
}
