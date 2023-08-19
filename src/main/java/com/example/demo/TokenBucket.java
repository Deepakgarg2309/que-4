package com.example.demo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucket {
    private final int capacity;
    private final long refillInterval;
    private final ConcurrentHashMap<String, UserTokens> userTokensMap = new ConcurrentHashMap<>();

    public TokenBucket(int capacity, long refillInterval) {
        this.capacity = (capacity+1)/2;
        this.refillInterval = refillInterval;
    }

    public boolean tryConsumeToken(String userId) {
        UserTokens userTokens = userTokensMap.computeIfAbsent(userId, key -> new UserTokens(capacity, refillInterval));
        return userTokens.tryConsumeToken();
    }

    private class UserTokens {
        private final AtomicInteger tokens;
        private long lastRefillTime;

        UserTokens(int capacity, long refillInterval) {
            this.tokens = new AtomicInteger(capacity);
            this.lastRefillTime = System.currentTimeMillis();
        }

        boolean tryConsumeToken() {
            refillTokens();
            return tokens.getAndDecrement() > 0;
        }

        private void refillTokens() {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastRefillTime;
            int newTokens = (int) (elapsedTime / refillInterval);

            if (newTokens > 0) {
                tokens.set(Math.min(tokens.get() + newTokens, capacity));
                lastRefillTime = currentTime;
            }
        }
    }
}
