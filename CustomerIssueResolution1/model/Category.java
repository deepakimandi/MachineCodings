package org.example.CustomerIssueResolution1.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Category {
    private String id;
    private String name;
    private List<Product> productList;
    private final LocalDateTime createdAt = LocalDateTime.now();

    public Category(String id, String name, List<Product> productList) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.productList = productList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
