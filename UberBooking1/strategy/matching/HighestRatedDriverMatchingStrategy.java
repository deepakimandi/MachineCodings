package org.example.UberBooking1.strategy.matching;

import org.example.CustomerIssueResolution1.model.Driver;
import org.example.CustomerIssueResolution1.model.Location;

import java.util.Comparator;
import java.util.List;

public class HighestRatedDriverMatchingStrategy implements IDriverMatchingStrategy {
    @Override
    public Driver findDriver(Location src, List<Driver> filteredDrivers) {
        filteredDrivers.sort(Comparator.comparingDouble(Driver::getRating).reversed());

        for (Driver driver : filteredDrivers) {
            if (driver.markUnAvailable()) {
                return driver;
            }
        }
        return null;
    }
}
