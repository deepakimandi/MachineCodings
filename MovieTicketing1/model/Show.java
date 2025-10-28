package org.example.MovieTicketing1.model;

import java.util.Date;

public class Show {

    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final Date startTime;
    private final Integer durationInMinutes;

    public Show(final String id, final Movie movie, final Screen screen, final Date startTime, final Integer durationInMinutes) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
    }
    public String getId() {
        return id;
    }
    public Movie getMovie() { return movie;}
    public Screen getScreen() {
        return screen;
    }
    public Date getStartTime() {
        return startTime;
    }
    public Integer getdurationInMinutes() {
        return durationInMinutes;
    }
}