package org.example.UberBooking1.model;

import lombok.Getter;
import lombok.Setter;
import org.example.UberBooking1.model.product.Product;
import org.example.CustomerIssueResolution1.enums.RideStatus;

import java.util.UUID;

@Getter
@Setter
public class Ride {
    private String id;
    private Product product;
    private Location src;
    private Location dest;
    private Rider rider;
    private Driver driver;
    private Vehicle vehicle;
    private RideStatus rideStatus;

    public Ride(Product product, Location src, Location dest, Rider rider) {
        this.id = UUID.randomUUID().toString();
        this.product = product;
        this.src = src;
        this.dest = dest;
        this.rider = rider;
        this.rideStatus = RideStatus.CREATED;
    }
}
