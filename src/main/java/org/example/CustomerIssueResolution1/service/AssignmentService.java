package org.example.CustomerIssueResolution1.service;

import org.example.CustomerIssueResolution1.enums.IssueStatus;
import org.example.CustomerIssueResolution1.model.Agent;
import org.example.CustomerIssueResolution1.model.Issue;
import org.example.CustomerIssueResolution1.repository.AgentRepository;
import org.example.CustomerIssueResolution1.repository.IssueRepository;
import org.example.CustomerIssueResolution1.strategy.AssignmentStrategy;

import java.util.ArrayList;
import java.util.List;

public class AssignmentService {

    private final AgentRepository agentRepository;
    private final IssueRepository issueRepository;
    private final AssignmentStrategy strategy;

    // Constructor
    public AssignmentService(AgentRepository agentRepository, IssueRepository issueRepository, AssignmentStrategy strategy) {
        this.agentRepository = agentRepository;
        this.issueRepository = issueRepository;
        this.strategy = strategy;
    }

    // Assign an issue to an agent
    public void assignIssue(String issueId) {
        Issue issue = issueRepository.getById(issueId);
        if (issue == null) throw new IllegalArgumentException("Issue not found");

        List<Agent> agents = new ArrayList<>(agentRepository.getAll());
        Agent assigned = strategy.assign(agents, issue);

        if (assigned != null) {
            assigned.setAssignedIssueId(issue.getId());
            issue.setAssignedAgentId(assigned.getId());
            System.out.println(">>> Issue " + issueId + " assigned to agent " + assigned.getId());
        } else {
            boolean addedToWaitlist = false;
            for (Agent agent : agents) {
                if (agent.getExpertise().contains(issue.getIssueType())) {
                    agent.getWaitList().add(issue.getId());
                    issue.setStatus(IssueStatus.WAITING);
                    System.out.println(">>> Issue " + issueId + " added to waitlist of Agent " + agent.getId());
                    addedToWaitlist = true;
                    break;
                }
            }
            if (!addedToWaitlist) {
                System.out.println(">>> No agent found with expertise for issue " + issueId);
            }
        }
    }
}
