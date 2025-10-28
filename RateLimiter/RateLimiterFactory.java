package org.example.RateLimiter;

public class RateLimiterFactory {

    public static RateLimiter createRateLimiter(RateLimitType type, RateLimitConfig config) {
        switch (type) {
            case TOKEN_BUCKET:
                return new TokenBucketRateLimiter(config);
            case FIXED_WINDOW:
                return new FixedWindowRateLimiter(config);
            case SLIDING_WINDOW_LOG:
                return new SlidingWindowLogRateLimiter(config);
            default:
                throw new IllegalArgumentException("Unsupported rate limiter type: " + type);
        }
    }
}
