package org.example.MovieTicketing1.service;

import org.example.CustomerIssueResolution1.model.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MovieService {

    private final Map<Integer, Movie> movies;
    private final AtomicInteger movieCounter;

    public MovieService() {
        this.movies = new HashMap<>();
        this.movieCounter = new AtomicInteger(0);
    }

    public Movie getMovie(final int movieId) throws Exception {
        if (!movies.containsKey(movieId)) {
            throw new Exception("Movie with ID " + movieId + " not found.");
        }
        return movies.get(movieId);
    }

    public Movie createMovie(final String movieName, final int durationInMinutes) {
        int movieId = movieCounter.incrementAndGet();
        Movie movie = new Movie(movieId, movieName, durationInMinutes);
        movies.put(movieId, movie);
        return movie;
    }
}