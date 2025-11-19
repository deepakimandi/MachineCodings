package org.example.RateLimiter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class SlidingWindowLogRateLimiter extends RateLimiter {

    private final Map<String, Queue<Long>> requestLog = new ConcurrentHashMap<>();

    public SlidingWindowLogRateLimiter(RateLimitConfig config) {
        super(config);
    }

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        long now = System.currentTimeMillis() / 1000;

        requestLog.compute(userId, (id, log) -> {
            if (log == null) log = new ArrayDeque<>();

            // Remove timestamps older than window
            while (!log.isEmpty() && (now - log.peek()) >= config.getWindowInSeconds()) {
                log.poll();
            }

            if (log.size() < config.getMaxRequests()) {
                log.add(now); // record this request
                allowed.set(true);
            }

            return log;
        });

        return allowed.get();
    }
}
