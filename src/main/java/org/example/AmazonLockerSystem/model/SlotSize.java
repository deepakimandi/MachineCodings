package org.example.AmazonLockerSystem.model;

import lombok.Data;

@Data
public class SlotSize {
    private double length;
    private double width;
    private double height;

    public boolean canFit(PackageSize pkg) {
        return true;
    }
}
