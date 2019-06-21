package com.jw.edge.service;

import com.jw.edge.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void deleteUser(String userName);
    User findUser(String name);
    void updateUser(User user);
    List<User> findAllUsers();
}
