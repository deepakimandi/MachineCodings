package org.example.UberBooking1.service;

import lombok.AllArgsConstructor;
import org.example.CustomerIssueResolution1.model.Driver;
import org.example.CustomerIssueResolution1.model.Location;
import org.example.CustomerIssueResolution1.model.product.Product;
import org.example.CustomerIssueResolution1.repository.DriverRepository;
import org.example.CustomerIssueResolution1.strategy.matching.IDriverMatchingStrategy;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DriverMatchingService {
    private final DriverRepository driverRepo;
    private final IDriverMatchingStrategy driverMatchingStrategy;

    public Driver findDriver(Location src, Product product) {
        List<Driver> nearbyDrivers = driverRepo.getNearbyDrivers(src, 2);

        List<Driver> filteredDrivers = nearbyDrivers.stream()
                .filter(driver -> driver.getVehicle() != null &&
                        driver.getVehicle().getSupportedProducts().contains(product))
                .collect(Collectors.toList());

        return driverMatchingStrategy.findDriver(src, filteredDrivers);
    }
}
