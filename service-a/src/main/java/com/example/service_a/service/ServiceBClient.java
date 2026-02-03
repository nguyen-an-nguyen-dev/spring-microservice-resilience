package com.example.service_a.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class ServiceBClient {

    private final RestTemplate restTemplate;
    private final String serviceBUrl;

    public ServiceBClient(
            RestTemplate restTemplate,
            @Value("${service-b.url}") String serviceBUrl) {
        this.restTemplate = restTemplate;
        this.serviceBUrl = serviceBUrl;
    }
    
    @Retry(name = "serviceBRetry")
    @CircuitBreaker(name = "serviceBCircuit", fallbackMethod = "fallback")
    @RateLimiter(name = "serviceBRateLimiter")
    @Bulkhead(name = "serviceBBulkhead")

    public String callServiceB() {
        String url = serviceBUrl + "/hello";
        return restTemplate.getForObject(url, String.class);
        
    }
    
    
    // fallback BẮT BUỘC
    public String fallback(Exception e) {
        return "Service B đang lỗi, fallback được gọi!";
    }
}
