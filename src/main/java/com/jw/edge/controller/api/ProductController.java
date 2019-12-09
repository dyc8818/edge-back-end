package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.jw.edge.entity.Product;
import com.jw.edge.service.ProductServie;
import com.jw.edge.util.LayuiTableResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequestMapping("/api/product")
@RestController
public class  ProductController {
    @Autowired
    ProductServie productServie;
    @Autowired
    RestTemplate restTemplate;
    @Value("${server.edgex}")
    private String ip;


    @GetMapping("/list")
    @ResponseBody
    public LayuiTableResultUtil<JSONArray> getProducts(@RequestParam Integer page, @RequestParam Integer limit) {
        String url = "http://"+ip+":48081/api/v1/deviceprofile";
        JSONArray products = new JSONArray(restTemplate.getForObject(url,JSONArray.class));
        System.out.println("查看所有设备模板"+products);
        return new LayuiTableResultUtil<>("",products,0,products.size());
    }



    @PostMapping("/yml")
    @ResponseBody
    public String addProduct(@RequestBody String product) {
        System.out.println("收到\n"+product);
        String url = "http://"+ip+":48081/api/v1/deviceprofile/upload";
        String result = restTemplate.postForObject(url,product,String.class);
        return result;
    }

    @DeleteMapping()
    @ResponseBody
    public void deleteProduct(@RequestBody String id){
        String url = "http://"+ip+":48081/api/v1/deviceprofile/id/"+id;
        System.out.println(url);
        restTemplate.delete(url);
    }
}
