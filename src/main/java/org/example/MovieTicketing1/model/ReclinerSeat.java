package org.example.MovieTicketing1.model;

import org.example.CustomerIssueResolution1.enums.SeatType;

public class ReclinerSeat extends Seat {
    public ReclinerSeat(String id, double price) {
        super(id, price);
    }

    public SeatType getType() {
        return SeatType.RECLINER;
    }
}
