package org.example.UberBooking1.strategy.matching;

import org.example.CustomerIssueResolution1.model.Driver;
import org.example.CustomerIssueResolution1.model.Location;

import java.util.List;

public interface IDriverMatchingStrategy {
    public Driver findDriver(Location src, List<Driver> filteredDrivers);
}
