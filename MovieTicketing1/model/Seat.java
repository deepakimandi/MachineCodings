package org.example.MovieTicketing1.model;

public abstract class Seat {
    private final String id;
    private double price;

    protected Seat(String id, double price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

}