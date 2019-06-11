package com.jw.edge.dao;

import com.jw.edge.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    public Product findProductByProductName(String productName);
}
