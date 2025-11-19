package org.example.UberBooking1.repository;

import org.example.CustomerIssueResolution1.model.Location;
import org.example.UberBooking1.model.Driver;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DriverRepository {
    private Map<String, Driver> drivers;

    public void save(Driver driver) {
        drivers.put(driver.getId(), driver);
    }

    public Driver getById(String id) {
        return drivers.get(id);
    }

    public List<Driver> getNearbyDrivers(Location src, double radius) {
        return drivers.values().stream()
                .filter(driver -> driver.isAvailable()
                        && src.distanceTo(driver.getCurrLocation()) <= radius)
                .collect(Collectors.toList());
    }
}
