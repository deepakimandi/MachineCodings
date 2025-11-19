package org.example.UberBooking1.repository;

import org.example.CustomerIssueResolution1.model.Fare;

import java.time.Duration;

public interface FareRepository {
    void save(Fare fare, String riderId, Duration ttl);
    Fare getFare(String fareId, String riderId);
}
