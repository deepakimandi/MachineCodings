package org.example.UberBooking1.model.product;

import org.example.CustomerIssueResolution1.enums.ProductType;

public abstract class Product {
    private String id;
    private ProductType type;

    public abstract double getBaseRate();
    public abstract double getPerKmRate();
    public abstract double getPerMinRate();
    public abstract ProductType getType();
}
