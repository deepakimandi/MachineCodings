package org.example.MovieTicketing1.model;

public class Movie {

    private final String id;
    private final String title;
    private final int movieDurationInMinutes;

    public Movie(String id, String title, int movieDurationInMinutes) {
        this.id = id;
        this.title = title;
        this.movieDurationInMinutes = movieDurationInMinutes;
    }

    public String getId() {return id;}
    public String getTitle() {return title;}
    public int getMovieDuration() { return movieDurationInMinutes;}
}