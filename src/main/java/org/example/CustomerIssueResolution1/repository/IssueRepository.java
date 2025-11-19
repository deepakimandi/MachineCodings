package org.example.CustomerIssueResolution1.repository;

import org.example.CustomerIssueResolution1.model.Issue;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IssueRepository {

    private final Map<String, Issue> issues = new HashMap<>();

    // Save or update an issue
    public void save(Issue issue) {
        issues.put(issue.getId(), issue);
    }

    // Get an issue by ID
    public Issue getById(String id) {
        return issues.get(id);
    }

    // Get all issues
    public Collection<Issue> getAll() {
        return issues.values();
    }
}
