package org.example.CustomerIssueResolution1.repository;

import org.example.CustomerIssueResolution1.model.Category;
import org.example.CustomerIssueResolution1.model.Product;

import java.util.HashMap;

public class CategoryRepo {
    private HashMap<String, Category> categories;

    public CategoryRepo() {
        this.categories = new HashMap<>();
    }
}
