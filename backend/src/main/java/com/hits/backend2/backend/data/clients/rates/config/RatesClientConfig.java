package com.hits.backend2.backend.data.clients.rates.config;

import com.hits.backend2.backend.data.clients.rates.interceptor.RatesClientInterceptor;
import feign.Feign;
import io.github.resilience4j.ratelimiter.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RatesClientConfig {
    @Autowired
    private RateLimiter rateLimiter;

    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder()
                .requestInterceptor(new RatesClientInterceptor(rateLimiter));
    }
}
