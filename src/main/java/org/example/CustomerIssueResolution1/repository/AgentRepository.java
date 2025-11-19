package org.example.CustomerIssueResolution1.repository;

import org.example.CustomerIssueResolution1.model.Agent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AgentRepository {

    private final Map<String, Agent> agents = new HashMap<>();

    // Save or update an agent
    public void save(Agent agent) {
        agents.put(agent.getId(), agent);
    }

    // Get agent by ID
    public Agent getById(String id) {
        return agents.get(id);
    }

    // Get all agents
    public Collection<Agent> getAll() {
        return agents.values();
    }

    // Optional: Delete agent by ID
    public void deleteById(String id) {
        agents.remove(id);
    }
}
