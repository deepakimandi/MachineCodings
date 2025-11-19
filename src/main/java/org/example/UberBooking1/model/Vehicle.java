package org.example.UberBooking1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.UberBooking1.model.product.Product;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Vehicle {
    private String numberPlate;
    private List<Product> supportedProducts;
}
