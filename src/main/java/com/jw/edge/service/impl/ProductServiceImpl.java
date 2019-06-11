package com.jw.edge.service.impl;

import com.jw.edge.dao.ProductRepository;
import com.jw.edge.entity.Product;
import com.jw.edge.service.ProductServie;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
