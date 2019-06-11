package com.jw.edge.service;

import com.jw.edge.entity.Product;

import java.util.List;

public interface ProductServie {
    public List<Product> findAllProduct();
    public boolean addProduct(Product product);
}
