package org.example.RateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedWindowRateLimiter extends RateLimiter {

    private final Map<String, Integer> requestCount = new ConcurrentHashMap<>();
    private final Map<String, Long> windowStart = new ConcurrentHashMap<>();

    public FixedWindowRateLimiter(RateLimitConfig config) {
        super(config);
    }

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        long currentWindow = System.currentTimeMillis() / 1000 / config.getWindowInSeconds();

        requestCount.compute(userId, (id, count) -> {
            long lastWindow = windowStart.getOrDefault(id, -1L);

            // If we’ve moved into a new time window, reset counter and window start
            if (lastWindow != currentWindow) {
                windowStart.put(id, currentWindow);
                allowed.set(true);
                return 1; // first request in new window
            }

            if (count == null) count = 0;
            if (count < config.getMaxRequests()) {
                allowed.set(true);
                return count + 1; // increment count
            }

            // Otherwise, reject the request (rate limit exceeded)
            return count;
        });

        return allowed.get();
    }
}
