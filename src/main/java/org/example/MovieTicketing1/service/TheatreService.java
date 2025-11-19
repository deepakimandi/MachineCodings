package org.example.MovieTicketing1.service;

import org.example.CustomerIssueResolution1.model.Screen;
import org.example.CustomerIssueResolution1.model.Seat;
import org.example.CustomerIssueResolution1.model.Theatre;
import org.example.CustomerIssueResolution1.repository.TheatreRepository;

public class TheatreService {
    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public Theatre createTheatre(final String theatreName) {
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatres.put(theatreId, theatre);
        return theatre;
    }

    public Screen addScreen(final String screenName, final Theatre theatre) {
        Screen screen = createScreen(screenName, theatre);
        theatre.addScreen(screen);
        return screen;
    }

    public Seat addSeat(Theatre theatre, Screen screen, Seat seat) {

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