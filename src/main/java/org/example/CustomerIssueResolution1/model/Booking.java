package org.example.CustomerIssueResolution1.model;

import org.example.CustomerIssueResolution1.enums.BookingStatus;

import java.util.List;

public class Booking {

    private final String id;
    private final Show show;
    private final List<Seat> seatsBooked;
    private final User user;
    private BookingStatus bookingStatus;
    private double price;

    public Booking(final String id, final Show show, final User user, final List<Seat> seatsBooked, double price) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus = BookingStatus.CREATED;
        this.price = price;
    }

    public boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.CONFIRMED;
    }

    public void confirmBooking() throws Exception {
        if (this.bookingStatus != BookingStatus.CREATED) {
            throw new Exception("Cannot confirm a booking that is not in the Created state.");
        }
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public void expireBooking() throws Exception {
        if (this.bookingStatus != BookingStatus.CREATED) {
            throw new Exception("Cannot expire a booking that is not in the Created state.");
        }
        this.bookingStatus = BookingStatus.EXPIRED; // Update the booking status to expire.
    }

    public String getId() {
        return id;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeatsBooked() {
        return seatsBooked;
    }

    public User getUser() {
        return user;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }
}