package com.jw.edge.controller.pages;

import com.jw.edge.entity.User;
import com.jw.edge.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/pages")
@Controller
public class UserPagesController {
    @Autowired
    UserService userService;

    @RequestMapping("/users")
    public String userList2(Model model) throws Exception {
        model.addAttribute("hello","Hello, Spring Boot!");
        model.addAttribute("userList", userService.findAllUsers());
//        for(User user:userService.findAllUsers()){
//            ObjectId objectId = new ObjectId(user.getUserId());
//            System.out.println(objectId.getDate()+","+objectId.getCounter());
//        }
        return "user/userList";
    }
}
