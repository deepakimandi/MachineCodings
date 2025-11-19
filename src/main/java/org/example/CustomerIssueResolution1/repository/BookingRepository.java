package org.example.CustomerIssueResolution1.repository;

import org.example.CustomerIssueResolution1.model.Booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingRepository {
    private final HashMap<String, Booking> bookings = new HashMap<>();

    public void save(Booking booking) {
        bookings.put(booking.getId(), booking);
    }

    public Booking get(String id) {
        return bookings.get(id);
    }

    public List<Booking> getBookingsByShow(String showId) {
        List<Booking> showBookings = new ArrayList<>();
        for (Booking booking : bookings.values()) {
            if (booking.getShow().getId().equals(showId)) {
                showBookings.add(booking);
            }
        }
        return showBookings;
    }
}
