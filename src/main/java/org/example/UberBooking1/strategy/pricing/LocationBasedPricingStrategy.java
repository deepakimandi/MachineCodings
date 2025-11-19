package org.example.UberBooking1.strategy.pricing;

import org.example.CustomerIssueResolution1.model.Location;

public class LocationBasedPricingStrategy implements PricingStrategy {
    @Override
    public double calculateFare(Location src, Location dest) {
        return 0.0;
    }
}
