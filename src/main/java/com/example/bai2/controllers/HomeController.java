package com.example.bai2.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String index(Model model) {
        Map<String, String> home = new HashMap<>();
        home.put("title", "Chiu thua cach noi yeu em");
        home.put("message", "Xin caho");
        model.addAttribute("home", home);
        return "index";
    }
}
