package com.jw.edge.dao;

import com.jw.edge.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Test
    public void contextLoads() {

        userRepository.save(new User("张三","是个男人"));
    }
    @Before
    public void before(){
        System.out.print("开始进行MongoDB测试");
    }
    @After
    public void after(){
        System.out.print("结束测试！");
    }


}