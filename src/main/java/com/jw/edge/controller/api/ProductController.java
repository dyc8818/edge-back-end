package com.jw.edge.controller.api;

import com.jw.edge.entity.Product;
import com.jw.edge.service.ProductServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ProductController {
    @Autowired
    ProductServie productServie;

    @GetMapping("/products")
    @ResponseBody
    public List<Product> getProducts() {
        return productServie.findAllProduct();
    }

    @PostMapping("/product")
    @ResponseBody
    public Boolean addProduct(@RequestBody Product product) {
        if (product != null) {
            if (productServie.addProduct(product)) {
                return true;
            }
        }
        return false;
    }
}
