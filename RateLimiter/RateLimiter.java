package org.example.RateLimiter;

public abstract class RateLimiter {

    protected final RateLimitConfig config;

    public RateLimiter(RateLimitConfig config) {
        this.config = config;
    }

    /**
     * Determines if a request from a given user should be allowed
     * according to the configured rate limiting algorithm.
     *
     * @param userId The unique identifier of the user or client.
     * @return true if the request is allowed, false otherwise.
     */
    public abstract boolean allowRequest(String userId);

    public RateLimitConfig getConfig() {
        return config;
    }
}
