package com.jw.edge.controller;


import com.jw.edge.entity.User;
import com.jw.edge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    @ResponseBody
    public List<User> getUser() {
        return userService.findAllUsers();
    }

    @GetMapping("/user")
    @ResponseBody
    public User getUserByName(@RequestParam("name") String name) {
        return userService.findUser(name);
    }

    @PostMapping("/user")
    @ResponseBody
    public String addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            return "success";
        }catch (Exception e){
            return "failed";
        }
    }

    @PutMapping("/user")
    @ResponseBody
    public String updateUser(@RequestParam String name,@RequestParam String userRemark) {
        try {
            userService.updateUser(new User(name,userRemark));
            return "success";
        }catch (Exception e){
            return "failed";
        }
    }

    @DeleteMapping("/user")
    @ResponseBody
    public String deleteUser(@RequestParam String name) {
        try {
            userService.deleteUser(name);
            return "success";
        }catch (Exception e){
            return "failed";
        }
    }


}