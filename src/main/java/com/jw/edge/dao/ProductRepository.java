package com.jw.edge.dao;

import com.jw.edge.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    public Product findProductByProductName(String productName);
    public Page<Product> findAll(Pageable pageable);
    public Product findProductByProductId(String productId);
}
