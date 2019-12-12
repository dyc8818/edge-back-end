package com.jw.edge.controller.pages;

import com.jw.edge.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/pages")
@Controller
public class ProfilePagesController {
    @Autowired
    ProfileService profileService;

    @GetMapping("/profile")
    public String productList(Model model) throws Exception {
        return "profile/profileManagement";
    }

    @GetMapping("/profileCreate")
    public String productCreate(Model model) throws Exception {
        return "profile/profileCreate";
    }

    @GetMapping("/profileDetails")
    public String productDetails() throws Exception {
        return "profile/profileDetails";
    }

}
