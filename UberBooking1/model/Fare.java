package org.example.UberBooking1.model;

import lombok.Getter;
import org.example.UberBooking1.model.product.Product;

import java.time.Instant;
import java.util.UUID;

@Getter
public class Fare {
    private final String id;
    private final Product product;
    private final Location src;
    private final Location dest;
    private final double estimatedFare;
    private final Instant createdAt;

    public Fare(Product product, Location src, Location dest, double estimatedFare) {
        this.id = UUID.randomUUID().toString();
        this.product = product;
        this.src = src;
        this.dest = dest;
        this.estimatedFare = estimatedFare;
        this.createdAt = Instant.now();
    }
}
