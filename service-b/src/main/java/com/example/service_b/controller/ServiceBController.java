package com.example.service_b.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceBController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("✅ Service B: /hello được gọi");
        return "Hello from Service B";
    }
}
