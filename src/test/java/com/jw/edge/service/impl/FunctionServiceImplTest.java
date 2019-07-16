package com.jw.edge.service.impl;

import com.jw.edge.service.FunctionService;
import com.jw.edge.service.ProductServie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FunctionServiceImplTest {

    @Autowired
    FunctionService functionService;

    @Test
    public void findFunctionByProductName() {
        System.out.println(functionService.findFunctionByProductName("DSFDS").size());
    }
}