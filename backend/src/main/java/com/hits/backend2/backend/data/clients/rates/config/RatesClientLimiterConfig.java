package com.hits.backend2.backend.data.clients.rates.config;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RatesClientLimiterConfig {
    @Bean
    public RateLimiterRegistry rateLimiterRegistry() {
        return RateLimiterRegistry.ofDefaults();
    }

    @Bean
    public RateLimiterConfig rateLimiterConfig() {
        return RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(5)
                .build();
    }

    @Bean
    public RateLimiter rateLimiter(RateLimiterRegistry rateLimiterRegistry, RateLimiterConfig rateLimiterConfig) {
        return rateLimiterRegistry.rateLimiter("ratesClientRateLimiter", rateLimiterConfig);
    }
}
