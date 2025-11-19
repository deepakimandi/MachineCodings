package org.example.CustomerIssueResolution1.service;

import org.example.CustomerIssueResolution1.enums.IssueType;
import org.example.CustomerIssueResolution1.model.Agent;
import org.example.CustomerIssueResolution1.repository.AgentRepository;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AgentService {
    private final AgentRepository agentRepository;

    public void addAgent(String name, String email, List<IssueType> issueTypes) {
        String id = "A" + UUID. randomUUID(). toString(). substring (0, 6);
        Agent agent = new Agent(id, email, name, new HashSet<>(issueTypes));
        agentRepository.save(agent) ;
        System.out.println(">>> Agent " + id + " created");
    }

    public void viewAgentsWorkHistory() {
        for (Agent agent : agentRepository.getAll()) {
            System.out.println(agent.getId() + " -> " + agent.getHistory());
        }
    }
}
