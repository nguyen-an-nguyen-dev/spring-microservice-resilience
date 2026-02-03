package com.example.service_a.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
@Service
public class ServiceBClient {

    @Value("${service-b.url}")
    private String serviceBUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Retry(name = "serviceB", fallbackMethod = "fallback")
    @CircuitBreaker(name = "serviceB")
    public String callServiceB() {
        System.out.println("👉 Gọi Service B...");
        return restTemplate.getForObject(serviceBUrl + "/hello", String.class);
    }

    public String fallback(Exception e) {
        System.out.println("⚠️ Fallback được gọi do lỗi: " + e.getMessage());
        return "Fallback from Service A";
    }
}