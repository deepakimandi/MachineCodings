package org.example.MovieTicketing1;

import org.example.CustomerIssueResolution1.repository.BookingRepository;
import org.example.CustomerIssueResolution1.repository.MovieRepository;
import org.example.CustomerIssueResolution1.repository.ShowRepository;
import org.example.CustomerIssueResolution1.repository.TheatreRepository;
import org.example.CustomerIssueResolution1.strategy.locking.ISeatLockProvider;
import org.example.CustomerIssueResolution1.strategy.locking.InMemorySeatLockProvider;
import org.example.CustomerIssueResolution1.strategy.payment.DebitCardStrategy;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        TheatreRepository theatreRepo = new TheatreRepository();
        MovieRepository movieRepo = new MovieRepository();
        ShowRepository showRepo = new ShowRepository();
        BookingRepository bookingRepo = new BookingRepository();

        ISeatLockProvider lockProvider = new InMemorySeatLockProvider(5);

        TheatreService theatreService = new TheatreRepository(theatreRepo);
        MovieService movieService = new MovieRepository(movieRepo);
        ShowService showService = new ShowService(showRepo);
        BookingService bookingService = new BookingService(bookingRepo, lockProvider);
        PaymentService paymentService = new PaymentService(new DebitCardStrategy(), bookingService)

        Theatre t1 = theatreService.createTheatre("SRMT Inox");
        Screen s1 = theatreService.addScreen("AUDI1", t1);
        Seat seat1 = new RegularSeat("s1-1", 100);
        Seat seat2 = new RegularSeat("s1-2", 100);
        Seat seat3 = new RegularSeat("s1-3", 100);
        Seat seat4 = new RegularSeat("s1-4", 100);
        theatreService.addSeat(t1, s1, seat1);
        theatreService.addSeat(t1, s1, seat2);
        theatreService.addSeat(t1, s1, seat3);
        theatreService.addSeat(t1, s1, seat4);

        User user1 = new User("Deepak", "deepak@email.com");
        Booking booking = bookingService.createBooking(user1, show, List.of(seat1, seat2));
        paymentService.processPayment(booking);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            try {
                Booking booking1 = bookingService.createBooking(user1, show, List.of(seat1, seat2));
                Thread.sleep(1000);
                paymentService.processPayment(booking1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        executor.submit(() -> {
            try {
                Booking booking2 = bookingService.createBooking(user1, show, List.of(seat2, seat3));
                Thread.sleep(1000);
                paymentService.processPayment(booking1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });



        Booking booking1 = bookingService.createBooking(user1, show, List.of(seat1, seat2));
        Thread.sleep(6000);

        Booking booking2 = bookingService.createBooking(user1, show, List.of(seat2, seat3));
        paymentService.processPayment(booking1);

        Thread.sleep(1000);
        paymentService.processPayment(booking2);


    }
}