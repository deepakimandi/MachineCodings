package org.example.CustomerIssueResolution1.repository;

import org.example.CustomerIssueResolution1.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepo {
    private HashMap<String, Product> products;

    public ProductRepo() {
        this.products = new HashMap<>();
    }

    public List<Product> findByCategoryId(String categoryId) {
        return products.values().stream()
                .filter(product -> product.getCategory() != null && product.getCategory().getId().equals(categoryId))
                .collect(Collectors.toList());
    }

    List<Product> findByNameContainingOrDescriptionContaining(String name, String description) {
        return products.values().stream()
                .filter(product -> (product.getName() != null && product.getName().contains(name)) || (product.getDescription() != null && product.getDescription().contains(name)))
                .collect(Collectors.toList());
    }

    public void save(Product product) {
        if (product != null && product.getId() != null) {
            products.put(product.getId(), product);
        }
    }
}
