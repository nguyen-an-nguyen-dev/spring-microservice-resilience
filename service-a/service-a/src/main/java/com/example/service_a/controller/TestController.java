package com.example.service_a.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service_a.service.ServiceBClient;

@RestController
public class TestController {

    private final ServiceBClient serviceBClient;

    public TestController(ServiceBClient serviceBClient) {
        this.serviceBClient = serviceBClient;
    }

    @GetMapping("/call-b")
    public String callB() {
        return serviceBClient.callServiceB();
    }
}
