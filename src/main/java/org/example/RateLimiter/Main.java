package org.example.RateLimiter;

public class Main {

    // Example: Test concurrent requests
    static void checkConcurrency(RateLimiterService rateLimiterService) throws InterruptedException {
        User user = new User("concurrentUser", UserTier.FREE);

        Runnable task = () -> {
            boolean allowed = rateLimiterService.allowRequest(user);
            System.out.println(Thread.currentThread().getName() +
                    " → Request allowed: " + allowed);
        };

        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(task, "Thread-" + (i + 1));
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiterService rateLimiterService = new RateLimiterService();

        User freeUser = new User("user1", UserTier.FREE);       // 10 req per 60s
        User premiumUser = new User("user2", UserTier.PREMIUM); // 100 req per 60s

        System.out.println("=== Free User Requests ===");
        for (int i = 1; i <= 15; i++) {
            boolean allowed = rateLimiterService.allowRequest(freeUser);
            System.out.println("Request " + i + " for Free User: " + allowed);
            Thread.sleep(100); // simulate delay between requests
        }

        System.out.println("\n=== Premium User Requests ===");
        for (int i = 1; i <= 120; i++) {
            boolean allowed = rateLimiterService.allowRequest(premiumUser);
            System.out.println("Request " + i + " for Premium User: " + allowed);
            Thread.sleep(50);
        }

        System.out.println("\n=== Concurrency Test ===");
        checkConcurrency(rateLimiterService);
    }
}
