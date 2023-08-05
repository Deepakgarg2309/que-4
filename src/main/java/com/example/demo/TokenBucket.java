package com.example.demo;

public class TokenBucket {
    private final int capacity;
    private final long refillInterval;
    private int tokens;
    private long lastRefillTime;

    public TokenBucket(int capacity, long refillInterval) {
        this.capacity = capacity;
        this.refillInterval = refillInterval;
        this.tokens = capacity;
        this.lastRefillTime = System.currentTimeMillis();
    }

    public synchronized boolean tryConsumeToken() {
        refillTokens();
        if (tokens > 0) {
            tokens--;
            return true;
        } else {
            return false;
        }
    }

    private void refillTokens() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastRefillTime;
        int newTokens = (int) (elapsedTime / refillInterval);
        if (newTokens > 0) {
            tokens = Math.min(tokens + newTokens, capacity);
            lastRefillTime = currentTime;
        }
    }
}
