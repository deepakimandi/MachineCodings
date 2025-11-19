package org.example.MovieTicketing1.service;

import org.example.CustomerIssueResolution1.model.Movie;
import org.example.CustomerIssueResolution1.model.Screen;
import org.example.CustomerIssueResolution1.model.Show;
import org.example.CustomerIssueResolution1.repository.ShowRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShowService {
    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public Show getShow(final String  showId) throws Exception {
        return showRepository.get(showId);
    }

    public Show createShow(final Movie movie, final Screen screen, final Date startTime, final Integer durationInSeconds) {
        final Show show = new Show(UUID.randomUUID().toString(), movie, screen, startTime, durationInSeconds);
        showRepository.save(show);
        return show;
    }

    private List<Show> getShowsByScreen(final Screen screen) {
        return showRepository.getAllShows().stream()
                .filter(s -> s.getScreen().equals(screen))
                .collect(Collectors.toList());
    }

    private List<Show> getShowsByMovieTitle(final String title) {
        return showRepository.getAllShows().stream()
                .filter(s -> s.getMovie().getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }
}