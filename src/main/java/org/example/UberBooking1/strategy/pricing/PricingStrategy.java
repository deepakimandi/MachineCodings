package org.example.UberBooking1.strategy.pricing;

import org.example.CustomerIssueResolution1.model.Location;

public interface PricingStrategy {
    public double calculateFare(Location src, Location dest);
}
