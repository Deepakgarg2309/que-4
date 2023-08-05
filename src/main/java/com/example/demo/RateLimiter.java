package com.example.demo;

public class RateLimiter {
    private final TokenBucket tokenBucket;

    public RateLimiter(int capacity, long refillInterval) {
        this.tokenBucket = new TokenBucket(capacity, refillInterval);
    }

    public boolean makeApiCall() {
        return tokenBucket.tryConsumeToken();
    }
}

