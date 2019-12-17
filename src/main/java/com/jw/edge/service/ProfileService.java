package com.jw.edge.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileService {
    public Page<Product> findAllProduct(Pageable pageable);
    public List<Product> findAllProduct();
    public boolean addProduct(Product product);
    public String deleteProduct(String productId);
    public Product findProductByProductId(String productId);
    JSONObject stamp2Time(JSONObject jsonObject);
}