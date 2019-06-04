package com.jw.edge.service.impl;

import com.jw.edge.entity.User;
import com.jw.edge.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    UserService userService;
    @Test
    public void addUser() {

        userService.addUser(new User("张三","哈哈哈"));
        userService.addUser(new User("王五","嘻嘻嘻"));
        userService.addUser(new User("赵六","嘿嘿嘿"));

    }

    @Test
    public void deleteUser() {
        userService.deleteUser("李四");
    }

    @Test
    public void findUser() {
        User user = userService.findUser("张三");
        System.out.println(user.getUserRemark());
    }

    @Test
    public void updateUser() {
        User user = new User("张三","诶诶诶");
        userService.updateUser(user);
    }

    @Test
    public void findAllUsers() {
        List<User> users = userService.findAllUsers();
        System.out.println(users.get(1).getUserRemark());
    }
}