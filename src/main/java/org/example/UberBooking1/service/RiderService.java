package org.example.UberBooking1.service;

import lombok.AllArgsConstructor;
import org.example.CustomerIssueResolution1.model.Rider;
import org.example.CustomerIssueResolution1.repository.RiderRepository;

@AllArgsConstructor
public class RiderService {
    private final RiderRepository riderRepository;

    public void registerRider(Rider rider) {
        riderRepository.save(rider);
    }

    public Rider getRiderById(String riderId) {
        Rider rider = riderRepository.getById(riderId);
        if (rider == null) {
            throw new RuntimeException("Rider not found with ID: " + riderId);
        }
        return rider;
    }
}
