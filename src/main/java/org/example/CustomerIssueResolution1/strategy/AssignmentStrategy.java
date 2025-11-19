package org.example.CustomerIssueResolution1.strategy;

import org.example.CustomerIssueResolution1.model.Agent;
import org.example.CustomerIssueResolution1.model.Issue;

import java.util.List;

public interface AssignmentStrategy {
    public Agent assign(List<Agent> agents, Issue issue);
}
