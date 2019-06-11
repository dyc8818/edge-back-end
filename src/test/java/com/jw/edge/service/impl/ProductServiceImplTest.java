package com.jw.edge.service.impl;

import com.jw.edge.entity.Product;
import com.jw.edge.service.ProductServie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    ProductServie productServie;

    @Test
    public void testProductLists(){
        List<Product> products = productServie.findAllProduct();
        System.out.println(products.get(0).getProductId()+products.get(0).getProductName());
    }

    @Test
    public void addProduct() {
        Product product = new Product();
        product.setProductName("传感器2");
        product.setProductType("设备");
        product.setProductConnectWay("wifi");
        System.out.println(productServie.addProduct(product));
    }
}