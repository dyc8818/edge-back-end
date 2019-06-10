package com.jw.edge.controller.pages;

import com.jw.edge.entity.User;
import com.jw.edge.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
@Controller
public class TestController {
    @Autowired
    UserService userService;

    @RequestMapping("/test1")
    public String userList1(Model model) throws Exception {
        model.addAttribute("title","title");
        model.addAttribute("title","title");
        return "user/test1";
    }

    @RequestMapping("/test2")
    public String userList2() throws Exception {

        return "user/test2";
    }
}
