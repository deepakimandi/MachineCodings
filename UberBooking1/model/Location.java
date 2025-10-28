package org.example.UberBooking1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Location {
    private final double latitude;
    private final double longitude;

    public double distanceTo(Location other) {
        double dx = this.latitude - other.latitude;
        double dy = this.longitude - other.longitude;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
