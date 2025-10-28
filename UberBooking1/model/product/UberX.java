package org.example.UberBooking1.model.product;

import org.example.CustomerIssueResolution1.enums.ProductType;

public class UberX extends Product {
    @Override
    public double getBaseRate() {
        return 0;
    }

    @Override
    public double getPerKmRate() {
        return 0;
    }

    @Override
    public double getPerMinRate() {
        return 0;
    }

    @Override
    public ProductType getType() {
        return ProductType.UBER_X;
    }
}
