package org.example.MovieTicketing1.repository;

import org.example.CustomerIssueResolution1.model.Screen;
import org.example.CustomerIssueResolution1.model.Seat;
import org.example.CustomerIssueResolution1.model.Theatre;

import java.util.Map;

public class TheatreRepository {
    private final Map<Integer, Theatre> theatres;
    private final Map<Integer, Screen> screens;
    private final Map<Integer, Seat> seats;


    public TheatreRepository(Map<Integer, Theatre> theatres, Map<Integer, Screen> screens, Map<Integer, Seat> seats) {
        this.theatres = theatres;
        this.screens = screens;
        this.seats = seats;
    }


}
