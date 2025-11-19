//package org.example.AmazonLockerSystem.service;
//
//import org.example.AmazonLockerSystem.repository.AgentRepository;
//
//import java.util.List;
//
//public class AgentService {
//    private final AgentRepository agentRepo;
//    private final AgentAssignmentStrategy strategy;
//    private final NotificationService notificationService;
//
//    public AgentService(AgentRepository agentRepo,
//                        AgentAssignmentStrategy strategy,
//                        NotificationService notificationService) {
//        this.agentRepo = agentRepo;
//        this.strategy = strategy;
//        this.notificationService = notificationService;
//    }
//
//    public void registerAgent(DeliveryAgent agent) {
//        agentRepo.save(agent);
//    }
//
//    public DeliveryAgent assignAgentForDelivery(Locker locker, Package pkg) {
//        String zipCode = locker.getZip();
//        List<DeliveryAgent> agents = agentRepo.getByZip(zipCode);
//
//        if (agents == null || agents.isEmpty()) {
//            throw new RuntimeException("No delivery agents available for ZIP: " + zipCode);
//        }
//
//        DeliveryAgent assignedAgent = strategy.assignAgent(agents);
//        if (assignedAgent == null) {
//            throw new RuntimeException("Unable to assign delivery agent for package: " + pkg.getId());
//        }
//
//        pkg.setAgentId(assignedAgent.getId());
//        pkg.setStatus(PackageStatus.ASSIGNED_TO_AGENT);
//
//        notificationService.notifyAgent(pkg);
//
//        return assignedAgent;
//    }
//}
