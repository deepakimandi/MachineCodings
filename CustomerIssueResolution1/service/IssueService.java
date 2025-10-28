package org.example.CustomerIssueResolution1.service;

import org.example.CustomerIssueResolution1.enums.IssueStatus;
import org.example.CustomerIssueResolution1.enums.IssueType;
import org.example.CustomerIssueResolution1.model.Agent;
import org.example.CustomerIssueResolution1.model.Issue;
import org.example.CustomerIssueResolution1.repository.AgentRepository;
import org.example.CustomerIssueResolution1.repository.IssueRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IssueService {

    private final IssueRepository issueRepository;
    private final AgentRepository agentRepository;

    // Constructor
    public IssueService(IssueRepository issueRepository, AgentRepository agentRepository) {
        this.issueRepository = issueRepository;
        this.agentRepository = agentRepository;
    }

    // Create a new issue
    public Issue createIssue(String transactionId, IssueType issueType, String subject, String description, String email) {
        Issue issue = new Issue(
                java.util.UUID.randomUUID().toString(), // Generate unique ID
                transactionId,
                issueType,
                subject,
                description,
                email,
                IssueStatus.OPEN, // Default status
                null,            // No resolution initially
                null             // No assigned agent initially
        );

        issueRepository.save(issue);
        System.out.println(">>> Issue " + issue.getId() + " created against transaction \"" + transactionId + "\"");
        return issue;
    }

    // Get issues based on optional filters
    public List<Issue> getIssues(Map<String, String> filter) {
        return issueRepository.getAll().stream()
                .filter(issue -> {
                    if (filter.containsKey("email") && !issue.getEmail().equalsIgnoreCase(filter.get("email"))) {
                        return false;
                    }
                    if (filter.containsKey("type") && !issue.getIssueType().name().equalsIgnoreCase(filter.get("type"))) {
                        return false;
                    }
                    if (filter.containsKey("status") && !issue.getStatus().name().equalsIgnoreCase(filter.get("status"))) {
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    // Update status and resolution of an existing issue
    public void updateIssue(String issueId, IssueStatus status, String resolution) {
        Issue issue = issueRepository.getById(issueId);
        if (issue == null) throw new IllegalArgumentException("Issue not found");

        issue.setStatus(status);
        issue.setResolution(resolution);

        System.out.println(">>> " + issueId + " status updated to " + issue.getStatus());
    }

    // Mark issue as resolved
    public void resolveIssue(String issueId, String resolution) {
        Issue issue = issueRepository.getById(issueId);
        if (issue == null) throw new IllegalArgumentException("Issue not found");

        issue.setStatus(IssueStatus.RESOLVED);
        issue.setResolution(resolution);

        // Optional: Notify assigned agent if exists
        if (issue.getAssignedAgentId() != null) {
            Agent agent = agentRepository.getById(issue.getAssignedAgentId());
            if (agent != null) {
                agent.getHistory().add(issue.getId());
                agent.setAssignedIssueId(null);
            }
            System.out.println(">>> Notifying agent " + issue.getAssignedAgentId());
        }

        System.out.println(">>> " + issueId + " issue marked resolved");
    }
}
