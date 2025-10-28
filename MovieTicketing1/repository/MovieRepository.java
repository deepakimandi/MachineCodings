package org.example.MovieTicketing1.repository;

import org.example.CustomerIssueResolution1.model.Movie;

import java.util.HashMap;
import java.util.Map;

public class MovieRepository {
    private final Map<String, Movie> movies = new HashMap<>();

    public void save(Movie movie) {
        movies.put(movie.getId(), movie);
    }

    public Movie get(String id) throws Exception {
        if (!movies.containsKey(id)) {
            throw new Exception("Show with ID " + id + " not found.");
        }
        return movies.get(id);
    }


}
