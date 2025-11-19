package org.example.UberBooking1.service;

import lombok.AllArgsConstructor;
import org.example.CustomerIssueResolution1.model.Driver;
import org.example.CustomerIssueResolution1.model.Location;
import org.example.CustomerIssueResolution1.repository.DriverRepository;

@AllArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;

    public void registerDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public void updateLocation(String driverId, Location newLocation) {
        Driver driver = driverRepository.getById(driverId);
        if (driver == null) {
            throw new RuntimeException("Driver not found with ID: " + driverId);
        }
        driver.setCurrLocation(newLocation);
    }
}
