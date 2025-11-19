//package org.example.AmazonLockerSystem.strategy;
//
//import java.util.List;
//import java.util.Random;
//
//public class RandomAgentAssignmentStrategy implements AgentAssignmentStrategy {
//    private final Random random = new Random();
//
//    @Override
//    public DeliveryAgent assignAgent(List<DeliveryAgent> agents) {
//        if (agents == null || agents.isEmpty()) {
//            return null;
//        }
//        return agents.get(random.nextInt(agents.size()));
//    }
//
//}
