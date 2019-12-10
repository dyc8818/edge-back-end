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

    @GetMapping("/products")
    public String productList(Model model) throws Exception {
        return "product/productManagement";
    }

    @GetMapping("/productCreate")
    public String productCreate(Model model) throws Exception {
        return "product/productCreate";
    }

    @GetMapping("/productDetails")
    public String productDetails() throws Exception {
        return "product/productDetails";
    }

}
