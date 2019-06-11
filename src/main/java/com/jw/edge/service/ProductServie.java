package com.jw.edge.service;

import com.jw.edge.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductServie {
    public Page<Product> findAllProduct(Pageable pageable);
    public List<Product> findAllProduct();
    public boolean addProduct(Product product);
}
