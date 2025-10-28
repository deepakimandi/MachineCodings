package org.example.UberBooking1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicBoolean;

@AllArgsConstructor
@Getter
@Setter
public class Driver {
    private String id;
    private String name;
    private Location currLocation;
    private AtomicBoolean available;
    private Vehicle vehicle;
    private double rating;

    public boolean isAvailable() {
        return available.get();
    }

    public void markAvailable() {
        available.set(true);
    }

    public boolean markUnAvailable() {
        return available.compareAndSet(true, false);
    }
}
