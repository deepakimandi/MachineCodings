package org.example.CustomerIssueResolution1.model;

public class Movie {

    private final int movieId;
    private final String movieName;
    private final int movieDurationInMinutes;

    public Movie(int i, String movieName, int movieDurationInMinutes) {
        this.movieId = i;
        this.movieName = movieName;
        this.movieDurationInMinutes = movieDurationInMinutes;
    }

    public int getMovieId() {return movieId;}
    public String getMovieName() {return movieName;}
    public int getMovieDuration() { return movieDurationInMinutes;}
}