package org.example.UberBooking1.repository;

import org.example.CustomerIssueResolution1.model.Rider;
import java.util.Map;

public class RiderRepository {
    private Map<String, Rider> riders;

    public void save(Rider rider) {
        riders.put(rider.getId(), rider);
    }

    public Rider getById(String id) {
        return riders.get(id);
    }
}
