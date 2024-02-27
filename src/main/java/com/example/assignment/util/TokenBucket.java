package com.example.assignment.util;

public class TokenBucket {
    private final int capacity;
    private int tokens;
    private long lastRefillTimestamp;
    private final int timeWindowInSeconds;

    public TokenBucket(int tokens, int timeWindowInSeconds) {
        this.capacity = tokens;
        this.tokens = tokens;
        this.lastRefillTimestamp = System.currentTimeMillis();
        this.timeWindowInSeconds = timeWindowInSeconds;
    }

    public synchronized boolean tryConsume() {
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
        long timeElapsed = currentTime - lastRefillTimestamp;
        int tokensToAdd = (int) (timeElapsed / (timeWindowInSeconds * 1000) * (double) capacity);

        tokens = Math.min(tokens + tokensToAdd, capacity);
        lastRefillTimestamp = currentTime;
    }
}
