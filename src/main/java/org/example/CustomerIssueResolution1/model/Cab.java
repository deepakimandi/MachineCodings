package org.example.CustomerIssueResolution1.model;

import lombok.Setter;

public class Cab {
    String id;
    String driverName;
    Trip currentTrip;
    Location currentLocation;
    Boolean isAvailable;

    public Cab(String id, String driverName) {
        this.id = id;
        this.driverName = driverName;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Cab{" +
                "id='" + id + '\'' +
                ", driverName='" + driverName + '\'' +
                ", currentLocation=" + currentLocation +
                ", isAvailable=" + isAvailable +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getDriverName() {
        return driverName;
    }

    public Trip getCurrentTrip() {
        return currentTrip;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public void setCurrentTrip(Trip currentTrip) {
        this.currentTrip = currentTrip;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
