package com.example.bai2.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {
    @GetMapping("hello")
    public String getHello() {
        return "Hello World";
    }
}
