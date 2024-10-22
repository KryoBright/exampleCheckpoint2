package com.hits.backend2.backend.data.clients.rates.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.github.resilience4j.ratelimiter.RateLimiter;

public class RatesClientInterceptor implements RequestInterceptor {

    private final RateLimiter rateLimiter;

    public RatesClientInterceptor(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void apply(RequestTemplate template) {
        if (!rateLimiter.acquirePermission()) {
            throw new RuntimeException("Rate limit exceeded");
        }
    }
}
