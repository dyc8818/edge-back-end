package com.jw.edge.controller.pages;

import com.alibaba.fastjson.JSONObject;
import com.jw.edge.entity.Product;
import com.jw.edge.util.LayuiTableResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/pages")
@Controller
public class test {
    @GetMapping("/test")
    public String test(Model model) throws Exception {
        return "test/test";
    }
    @ResponseBody
    @GetMapping("/test/data")
    public LayuiTableResultUtil<List> testdata() throws Exception {
        List<users> list = new ArrayList<users>();

        users user = new users();
        user.setCity("a");
        user.setEmail("b");
        user.setExperience("c");
        user.setJoinTime("2019");
        users user2 = new users();
        user.setCity("a");
        user.setEmail("b");
        user.setExperience("c");
        user.setJoinTime("2019");
        list.add(user);
        list.add(user2);
        String json = JSONObject.toJSONString(user);
        LayuiTableResultUtil<List> productsTable=new LayuiTableResultUtil<List>("",list,0,3);
        return productsTable;
    }
}

class users{
    Integer id;
    String username;
    String email;
    String sex;
    String city;
    String sign;
    String experience;
    String ip;
    String logins;
    String joinTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLogins() {
        return logins;
    }

    public void setLogins(String logins) {
        this.logins = logins;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }
}