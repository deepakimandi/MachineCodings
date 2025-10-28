package org.example.RateLimiter;

public class RateLimitConfig {
    private final int maxRequests;
    private final int windowInSeconds;

    public RateLimitConfig(int maxRequests, int windowInSeconds) {
        this.maxRequests = maxRequests;
        this.windowInSeconds = windowInSeconds;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public int getWindowInSeconds() {
        return windowInSeconds;
    }

    @Override
    public String toString() {
        return "RateLimitConfig{" +
                "maxRequests=" + maxRequests +
                ", windowInSeconds=" + windowInSeconds +
                '}';
    }
}
