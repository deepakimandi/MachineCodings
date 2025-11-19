package org.example.MovieTicketing1.service;


import org.example.MovieTicketing1.model.Booking;
import org.example.MovieTicketing1.model.User;
import org.example.MovieTicketing1.repository.BookingRepository;
import org.example.MovieTicketing1.strategy.locking.ISeatLockProvider;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingRepository bookingRepository;
    private final ISeatLockProvider lockProvider;

    public BookingService(BookingRepository bookingRepository, ISeatLockProvider lockProvider) {
        this.lockProvider = lockProvider;
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(final User user, final Show show, final List<Seat> seats) throws Exception {
        if (isAnySeatAlreadyBooked(show, seats)) {
            throw new Exception("Seat Already Booked");
        }

        lockProvider.lockSeats(show, seats, user);

        double totalPrice = 0.0;
        for (Seat seat : seats) {
            totalPrice += seat.getPrice();
        }

        Booking booking = new Booking(UUID.randomUUID().toString(), show, user, seats, totalPrice);
        bookingRepository.save(booking);

        return booking;
    }

    public void confirmBooking(Booking booking) throws Exception {
        for (Seat seat : booking.getSeatsBooked()) {
            if (!lockProvider.validateLock(booking.getShow(), seat, booking.getUser())) {
                throw new Exception("Acquired Lock is either invalid or has Expired");
            }
        }
        booking.confirmBooking();
        bookingRepository.save(booking);
    }

    public List<Seat> getBookedSeats(Show show) {
        return bookingRepository.getBookingsByShow(show.getId()).stream()
                .filter(Booking::isConfirmed)
                .map(Booking::getSeatsBooked)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private boolean isAnySeatAlreadyBooked(final Show show, final List<Seat> seats) {
        List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat : seats) {
            if (bookedSeats.contains(seat)) {
                return true;
            }
        }
        return false;
    }


}
