package org.example.CustomerIssueResolution1.repository;

import org.example.CustomerIssueResolution1.model.Show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShowRepository {
    private Map<String, Show> shows = new HashMap<>();

    public void save(Show show) {
        shows.put(show.getId(), show);
    }

    public Show get(String id) throws Exception {
        if (!shows.containsKey(id)) {
            throw new Exception("Show with ID " + id + " not found.");
        }
        return shows.get(id);
    }

    public List<Show> getAllShows() {
        return new ArrayList<>(shows.values());
    }
}
