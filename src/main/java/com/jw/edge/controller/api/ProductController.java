package com.jw.edge.controller.api;

import com.jw.edge.entity.Product;
import com.jw.edge.service.ProductServie;
import com.jw.edge.util.LayuiTableResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class  ProductController {
    @Autowired
    ProductServie productServie;


    @GetMapping("/products")
    @ResponseBody
    public LayuiTableResultUtil<List<Product>> getProducts(@RequestParam Integer page, @RequestParam Integer limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<Product> products =  productServie.findAllProduct(pageable);
        LayuiTableResultUtil<List<Product>> productsTable=new LayuiTableResultUtil<List<Product>>("",products.getContent(),0,(int)products.getTotalElements());
        return productsTable;
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

    @DeleteMapping("/product")
    @ResponseBody
    public LayuiTableResultUtil<String> deleteProduct(@RequestBody Product product){
       // System.out.println(product.getProductId());
        String deleteStatus = productServie.deleteProduct(product.getProductId());
        return  new LayuiTableResultUtil<String>("",deleteStatus,0,1);
    }
}
