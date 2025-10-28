package org.example.CustomerIssueResolution1.model;


import org.example.CustomerIssueResolution1.enums.IssueType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Agent {
    private final String id;
    private final String email;
    private final String name;
    private final Set<IssueType> expertise;
    private String assignedIssueId;
    private final Queue<String> waitList = new LinkedList<>();
    private final List<String> history = new ArrayList<>();

    // Constructor
    public Agent(String id, String email, String name, Set<IssueType> expertise) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.expertise = expertise;
    }

    // Check if agent is available (no currently assigned issue)
    public boolean isAvailable() {
        return assignedIssueId == null;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Set<IssueType> getExpertise() {
        return expertise;
    }

    public String getAssignedIssueId() {
        return assignedIssueId;
    }

    public void setAssignedIssueId(String assignedIssueId) {
        this.assignedIssueId = assignedIssueId;
    }

    public Queue<String> getWaitList() {
        return waitList;
    }

    public List<String> getHistory() {
        return history;
    }
}
