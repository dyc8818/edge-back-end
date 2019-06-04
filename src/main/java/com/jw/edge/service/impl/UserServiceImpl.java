package com.jw.edge.service.impl;

import com.jw.edge.dao.UserRepository;
import com.jw.edge.entity.User;
import com.jw.edge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String name) {
        userRepository.deleteByUserName(name);
    }

    @Override
    public User findUser(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public void updateUser(User user) {
        User user1 = findUser(user.getUserName());
        user1.setUserRemark(user.getUserRemark());
        userRepository.save(user1);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
