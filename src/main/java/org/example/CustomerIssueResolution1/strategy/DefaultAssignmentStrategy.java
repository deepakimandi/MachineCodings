package org.example.CustomerIssueResolution1.strategy;

import org.example.CustomerIssueResolution1.model.Agent;
import org.example.CustomerIssueResolution1.model.Issue;

import java.util.List;

public class DefaultAssignmentStrategy implements AssignmentStrategy {

    @Override
    public Agent assign(List<Agent> agents, Issue issue) {
        for (Agent agent : agents) {
            if (agent.isAvailable() && agent.getExpertise().contains(issue.getIssueType())) {
                return agent; // Assign the first available agent with matching expertise
            }
        }
        return null; // No suitable agent found
    }
}
