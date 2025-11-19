package org.example.CustomerIssueResolution1.service;

import org.example.CustomerIssueResolution1.model.Movie;
import org.example.CustomerIssueResolution1.model.Screen;
import org.example.CustomerIssueResolution1.model.Show;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ShowService {
    private final Map<Integer, Show> shows;
    private final AtomicInteger showCounter;

    public ShowService() {
        this.shows = new HashMap<>();
        this.showCounter = new AtomicInteger(0);
    }

    public Show getShow(final int showId) throws Exception {
        if (!shows.containsKey(showId)) {
            throw new Exception("Show with ID " + showId + " not found.");
        }
        return shows.get(showId);
    }

    public Show createShow(final Movie movie, final Screen screen, final Date startTime, final Integer durationInSeconds) {
        int showId = showCounter.incrementAndGet();
        final Show show = new Show(showId, movie, screen, startTime, durationInSeconds);
        this.shows.put(showId, show);
        return show;
    }

    private List<Show> getShowsForScreen(final Screen screen) {
        final List<Show> response = new ArrayList<>();
        for (Show show : shows.values()) {
            if (show.getScreen().getScreenId() == screen.getScreenId()) { // Compare by screen ID
                response.add(show);
            }
        }
        return response;
    }
}