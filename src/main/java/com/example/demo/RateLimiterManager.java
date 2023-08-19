package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class RateLimiterManager {
    private Map<String, TokenBucket> userTokenBuckets = new HashMap<>();

    public boolean makeApiCall(String userId) {
        TokenBucket tokenBucket = userTokenBuckets.computeIfAbsent(userId,
                key -> new TokenBucket(15,4000));

        return tokenBucket.tryConsumeToken(userId);
    }
}
