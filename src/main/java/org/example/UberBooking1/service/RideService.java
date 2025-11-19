package org.example.UberBooking1.service;

import lombok.AllArgsConstructor;
import org.example.CustomerIssueResolution1.model.*;
import org.example.CustomerIssueResolution1.model.product.Product;
import org.example.CustomerIssueResolution1.repository.FareRepository;
import org.example.CustomerIssueResolution1.repository.RideRepository;

@AllArgsConstructor
public class RideService {
    private RideRepository rideRepo;
    private FareRepository fareRepo;
    private DriverMatchingService driverMatchingService;

    public Ride requestRide(String fareId, Product product, Location src, Location dest, Rider rider) {
        Fare fare = fareRepo.getFare(fareId, rider.getId());

        if (fare == null) {
            throw new RuntimeException("Fare has expired. Please try again.");
        }

        if (fare.getProduct().getType() != product.getType()) {
            throw new RuntimeException("Fare does not match selected product.");
        }

        Ride ride = new Ride(product, src, dest, rider);

        Driver driver = driverMatchingService.findDriver(src, product);

        if (driver == null) {
            throw new RuntimeException("Driver not found.");
        }

        ride.setDriver(driver);
        ride.setVehicle(driver.getVehicle());

        rideRepo.save(ride);
        return ride;
    }
}
