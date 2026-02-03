package com.example.service_a.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;

@Configuration
public class RetryEventLogger {

    private final RetryRegistry retryRegistry;

    public RetryEventLogger(RetryRegistry retryRegistry) {
        this.retryRegistry = retryRegistry;
    }

    @PostConstruct
    public void registerRetryEvent() {
        Retry retry = retryRegistry.retry("serviceB");

        retry.getEventPublisher()
                .onRetry(event ->
                        System.out.println(
                                "🔁 Retry '" + event.getName()
                                + "', attempt " + event.getNumberOfRetryAttempts()
                        )
                );
    }
}