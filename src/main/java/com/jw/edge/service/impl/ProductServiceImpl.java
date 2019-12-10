package com.jw.edge.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jw.edge.dao.ProductRepository;
import com.jw.edge.entity.Product;
import com.jw.edge.service.ProductServie;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductServie {

    //private final DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    ProductRepository productRepository;


    @Override
    public Page<Product> findAllProduct(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products;
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public boolean addProduct(Product product) {
        Product findProduct = productRepository.findProductByProductName(product.getProductName());
        if (findProduct == null) {
            Product product1 = productRepository.save(product);
            ObjectId objectId = new ObjectId(product1.getProductId());
            product1.setProductCreateTime(objectId.getDate());
            productRepository.save(product1);
            return true;

        } else {
            return false;
        }
    }

    @Override
    public String deleteProduct(String productId) {
        Product product = productRepository.findProductByProductId(productId);
        if (product == null) {
            return "不存在该产品";
        } else {
            productRepository.deleteById(productId);
            return "删除成功";
        }
    }

    @Override
    public Product findProductByProductId(String productId) {
        return productRepository.findProductByProductId(productId);
    }

    @Override
    public JSONObject stamp2Time(JSONObject jsonObject){
        String createdStamp = jsonObject.getString("created");
        String modifiedStamp = jsonObject.getString("modified");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long created = Long.parseLong(createdStamp);
        long modified = Long.parseLong(modifiedStamp);
        Date createdTime = new Date(created);
        Date modifiedTime = new Date(modified);
        jsonObject.remove("created");
        jsonObject.remove("modified");
        jsonObject.put("created",simpleDateFormat.format(createdTime));
        jsonObject.put("modified",simpleDateFormat.format(modifiedTime));
        return jsonObject;
    }
}
