package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.service.ProfileService;
import com.jw.edge.util.LayuiTableResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RequestMapping("/api/profile")
@RestController
public class ProfileController {
    @Autowired
    ProfileService profileService;
    @Autowired
    RestTemplate restTemplate;
    @Value("${server.edgex}")
    private String ip;

    @GetMapping("/list")
    @ResponseBody
    public LayuiTableResultUtil<JSONArray> getProducts(@RequestParam Integer page, @RequestParam Integer limit) {
        String url = "http://"+ip+":48081/api/v1/deviceprofile";
        JSONArray products = new JSONArray(restTemplate.getForObject(url,JSONArray.class));
        JSONArray result = new JSONArray();
        for(int i=0;i<products.size();i++){
            JSONObject jo = products.getJSONObject(i);
            jo = profileService.stamp2Time(jo);
            result.add(jo);
        }
        System.out.println("查看所有设备模板"+result);
        return new LayuiTableResultUtil<>("",result,0,products.size());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public JSONObject getThisProduct(@PathVariable String id){
        String url = "http://"+ip+":48081/api/v1/deviceprofile/"+id;
        return profileService.stamp2Time(restTemplate.getForObject(url,JSONObject.class));
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
