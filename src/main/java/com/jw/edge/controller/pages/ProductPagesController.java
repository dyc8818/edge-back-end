package com.jw.edge.controller.pages;

import com.jw.edge.entity.Product;
import com.jw.edge.service.ProductServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pages")
@Controller
public class ProductPagesController {
    @Autowired
    ProductServie productServie;


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

    @GetMapping("/products")
    public String productList(Model model) throws Exception {
        List<Product> products = productServie.findAllProduct();
        model.addAttribute("products",products);
        return "product/productManagement";
    }

}
