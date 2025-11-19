package org.example.CustomerIssueResolution1.service;

import org.example.CustomerIssueResolution1.enums.SeatCategory;
import org.example.CustomerIssueResolution1.model.Screen;
import org.example.CustomerIssueResolution1.model.Seat;
import org.example.CustomerIssueResolution1.model.Theatre;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TheatreService {

    private final Map<Integer, Theatre> theatres;
    private final Map<Integer, Screen> screens;
    private final Map<Integer, Seat> seats;

    private final AtomicInteger theatreCounter;
    private final AtomicInteger screenCounter;
    private final AtomicInteger seatCounter;

    public TheatreService() {
        this.theatres = new HashMap<>();
        this.screens = new HashMap<>();
        this.seats = new HashMap<>();
        this.theatreCounter = new AtomicInteger(0);
        this.screenCounter = new AtomicInteger(0);
        this.seatCounter = new AtomicInteger(0);
    }

    public Seat getSeat(final int seatId) throws Exception {
        if (!seats.containsKey(seatId)) {
            throw new Exception("Seat with ID " + seatId + " not found.");
        }
        return seats.get(seatId);
    }

    public Theatre getTheatre(final int theatreId) throws Exception{
        if (!theatres.containsKey(theatreId)) {
            throw new Exception("Theatre with ID " + theatreId + " not found.");
        }
        return theatres.get(theatreId);
    }

    public Screen getScreen(final int screenId) throws Exception  {
        if (!screens.containsKey(screenId)) {
            throw new Exception("Screen with ID " + screenId + " not found.");
        }
        return screens.get(screenId);
    }

    public Theatre createTheatre(final String theatreName) {
        int theatreId = theatreCounter.incrementAndGet();
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatres.put(theatreId, theatre);
        return theatre;
    }

    public Screen createScreenInTheatre(final String screenName, final Theatre theatre) {
        Screen screen = createScreen(screenName, theatre);
        theatre.addScreen(screen);
        return screen;
    }

    public Seat createSeatInScreen(final Integer rowNo, SeatCategory seatCategory, final Screen screen) {
        int seatId = seatCounter.incrementAndGet();
        Seat seat = new Seat(seatId, rowNo, seatCategory);
        seats.put(seatId, seat);
        screen.addSeat(seat);
        return seat;
    }

    private Screen createScreen(final String screenName, final Theatre theatre) {
        int screenId = screenCounter.incrementAndGet();
        Screen screen = new Screen(screenId, screenName, theatre);
        screens.put(screenId, screen);
        return screen;
    }
}