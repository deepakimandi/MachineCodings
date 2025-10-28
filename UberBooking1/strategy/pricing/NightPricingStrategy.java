package org.example.UberBooking1.strategy.pricing;

import org.example.CustomerIssueResolution1.model.Location;

import java.time.LocalTime;

public class NightPricingStrategy implements PricingStrategy {

    @Override
    public double calculateFare(Location src, Location dest) {
        LocalTime now = LocalTime.now();

        boolean isNight = now.isAfter(LocalTime.of(22, 0)) || now.isBefore(LocalTime.of(6, 0));
        if (isNight) {
            return 50.0;
        }
        return 0.0;
    }
}
