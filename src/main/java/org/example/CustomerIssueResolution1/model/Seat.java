package org.example.CustomerIssueResolution1.model;

import org.example.CustomerIssueResolution1.enums.SeatCategory;

public class Seat {
    private final int seatId;
    private final int row;
    private final SeatCategory seatCategory;

    public Seat(final int seatId,final int row, final SeatCategory seatCategory) {
        this.seatId = seatId;
        this.row = row;
        this.seatCategory = seatCategory;
    }

    public int getSeatId() {
        return seatId;
    }
    public int getRow() {
        return row;
    }
    public SeatCategory getSeatCategory() {
        return seatCategory;
    }
}