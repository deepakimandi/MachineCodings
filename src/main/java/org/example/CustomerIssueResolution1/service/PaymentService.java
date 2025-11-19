package org.example.CustomerIssueResolution1.service;

import org.example.CustomerIssueResolution1.model.Booking;
import org.example.CustomerIssueResolution1.model.User;
import org.example.CustomerIssueResolution1.strategy.payment.PaymentStrategy;

public class PaymentService {
    private final PaymentStrategy paymentStrategy;
    private final BookingService bookingService;

    public PaymentService(PaymentStrategy paymentStrategy, BookingService bookingService) {
        this.paymentStrategy = paymentStrategy;
        this.bookingService = bookingService;
    }

    public void processPayment(final Booking booking) throws Exception {
        if(paymentStrategy.processPayment()) {
            bookingService.confirmBooking(booking);
        }
    }
}
