//package org.example.AmazonLockerSystem.repository;
//
//import java.util.*;
//
//public class AgentRepository {
//    private final Map<String, DeliveryAgent> agents = new HashMap<>();
//    private final Map<String, List<DeliveryAgent>> zipToAgents = new HashMap<>();
//
//    public void save(DeliveryAgent agent) {
//        // Assuming DeliveryAgent has getId() and getZip() methods
//        agents.put(agent.getId(), agent);
//
//        zipToAgents
//                .computeIfAbsent(agent.getZip(), k -> new ArrayList<>())
//                .add(agent);
//    }
//
//    public DeliveryAgent getById(String id) {
//        return agents.get(id);
//    }
//
//    public List<DeliveryAgent> getByZip(String zip) {
//        return zipToAgents.getOrDefault(zip, Collections.emptyList());
//    }
//}
