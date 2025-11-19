package org.example.UberBooking1.service;

import lombok.AllArgsConstructor;
import org.example.CustomerIssueResolution1.model.Fare;
import org.example.CustomerIssueResolution1.model.Location;
import org.example.CustomerIssueResolution1.model.product.Product;
import org.example.CustomerIssueResolution1.repository.FareRepository;
import org.example.CustomerIssueResolution1.strategy.pricing.PricingStrategy;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class FareEstimationService {
    private final FareRepository fareRepository;
    private final PricingStrategy pricingStrategy;

    public Map<Product, Double> getFareEstimates(Location src, Location dest, List<Product> products) {
        Map<Product, Double> estimates = new HashMap<>();
        double distanceKm = src.distanceTo(dest);
        int durationMin = (int) (distanceKm * 2);

        for (Product product : products) {
            double baseFare = calculateBaseFare(product, distanceKm, durationMin);
            double surge = pricingStrategy.calculateFare(src, dest);

            estimates.put(product, baseFare + surge);
        }

        return estimates;
    }

    private double calculateBaseFare(Product product, double distanceKm, int durationMin) {
        return product.getBaseRate() + product.getPerKmRate() * distanceKm + product.getPerMinRate() * durationMin;
    }

    public Fare createFare(String riderId, Product product, Location src, Location dest) {
        double distanceKm = src.distanceTo(dest);
        int durationMin = (int) (distanceKm * 2);

        double baseFare = calculateBaseFare(product, distanceKm, durationMin);
        double surge = pricingStrategy.calculateFare(src, dest);

        Fare fare = new Fare(product, src, dest, baseFare + surge);
        fareRepository.save(fare, riderId, Duration.ofSeconds(5));

        return fare;
    }
}
