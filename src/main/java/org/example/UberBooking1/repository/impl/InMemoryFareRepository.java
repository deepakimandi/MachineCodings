package org.example.UberBooking1.repository.impl;

import org.example.CustomerIssueResolution1.model.Fare;
import org.example.UberBooking1.repository.FareRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

public class InMemoryFareRepository implements FareRepository {
    private static class FareEntry {
        String riderId;
        Fare fare;
        Instant expiryTime;

        public FareEntry(String riderId, Fare fare, Instant expiryTime) {
            this.riderId = riderId;
            this.fare = fare;
            this.expiryTime = expiryTime;
        }
    }

    private final ConcurrentMap<String, FareEntry> fareCache = new ConcurrentHashMap<>();
    private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();

    public InMemoryFareRepository() {
        cleaner.scheduleAtFixedRate(this::evictExpiredFares, 1, 1, TimeUnit.MINUTES);
    }

    private void evictExpiredFares() {
        Instant now = Instant.now();
        fareCache.entrySet().removeIf(entry -> now.isAfter(entry.getValue().expiryTime));
    }

    @Override
    public void save(Fare fare, String riderId, Duration ttl) {
        Instant expiryTime = Instant.now().plus(ttl);
        fareCache.put(fare.getId(), new FareEntry(riderId, fare, expiryTime));
    }

    @Override
    public Fare getFare(String fareId, String riderId) {
        FareEntry fareEntry = fareCache.get(fareId);

        if (fareEntry == null || Instant.now().isAfter(fareEntry.expiryTime)) {
            return null;
        }

        if (!fareEntry.riderId.equals(riderId)) {
            return null;
        }

        return fareEntry.fare;
    }
}
