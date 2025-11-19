package org.example.CustomerIssueResolution1.model;

import org.example.CustomerIssueResolution1.enums.SeatType;

public class RegularSeat extends Seat {
    public RegularSeat(String id, double price) {
        super(id, price);
    }

    public SeatType getType() {
        return SeatType.REGULAR;
    }
}
