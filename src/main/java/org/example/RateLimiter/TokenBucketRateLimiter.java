package org.example.RateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class TokenBucketRateLimiter extends RateLimiter {

    private final Map<String, Integer> tokens = new ConcurrentHashMap<>();
    private final Map<String, Long> lastRefillTime = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(RateLimitConfig config) {
        super(config);
    }

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        long now = System.currentTimeMillis();

        tokens.compute(userId, (id, availableTokens) -> {
            int currentTokens = refillTokens(userId, now);
            if (currentTokens > 0) {
                allowed.set(true);
                return currentTokens - 1; // consume 1 token
            } else {
                return currentTokens; // no token available
            }
        });

        return allowed.get();
    }

    private int refillTokens(String userId, long now) {
        long lastRefill = lastRefillTime.getOrDefault(userId, now);
        long elapsedSeconds = (now - lastRefill) / 1000;

        if (elapsedSeconds > 0) {
            double refillRate = (double) config.getMaxRequests() / config.getWindowInSeconds();
            int refillTokens = (int) (elapsedSeconds * refillRate);
            int currentTokens = tokens.getOrDefault(userId, config.getMaxRequests());
            currentTokens = Math.min(config.getMaxRequests(), currentTokens + refillTokens);

            lastRefillTime.put(userId, now);
            return currentTokens;
        }

        return tokens.getOrDefault(userId, config.getMaxRequests());
    }
}
