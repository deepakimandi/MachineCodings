package org.example.CustomerIssueResolution1.model;

import org.example.CustomerIssueResolution1.enums.IssueStatus;
import org.example.CustomerIssueResolution1.enums.IssueType;

public class Issue {
    private final String id;
    private final String transactionId;
    private final IssueType issueType;
    private final String subject;
    private final String description;
    private final String email;
    private IssueStatus status;
    private String resolution;
    private String assignedAgentId;

    // Constructor
    public Issue(String id, String transactionId, IssueType issueType,
                 String subject, String description, String email,
                 IssueStatus status, String resolution, String assignedAgentId) {
        this.id = id;
        this.transactionId = transactionId;
        this.issueType = issueType;
        this.subject = subject;
        this.description = description;
        this.email = email;
        this.status = status;
        this.resolution = resolution;
        this.assignedAgentId = assignedAgentId;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getAssignedAgentId() {
        return assignedAgentId;
    }

    public void setAssignedAgentId(String assignedAgentId) {
        this.assignedAgentId = assignedAgentId;
    }

    // toString method
    @Override
    public String toString() {
        return "Issue{" +
                "id='" + id + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", issueType=" + issueType +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", resolution='" + resolution + '\'' +
                ", assignedAgentId='" + assignedAgentId + '\'' +
                '}';
    }
}
