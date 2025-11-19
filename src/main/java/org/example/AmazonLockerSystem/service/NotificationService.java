package org.example.AmazonLockerSystem.service;

import lombok.AllArgsConstructor;
import org.example.AmazonLockerSystem.repository.AgentRepository;

@AllArgsConstructor
public class NotificationService {
    private final CustomerRepository customerRepo;
    private final AgentRepository agentRepo;

    public void notifyCustomer(Package pkg, OtpInfo otpInfo) {
        Customer customer = customerRepo.getById(pkg.getCustomerId());
        System.out.println("Notification to Customer [" + customer.getId() + "]: " +
                "Your package (ID: " + pkg.getId() + ") is ready for pickup at locker '" +
                pkg.getLockerName() + "'. OTP: " + otpInfo.getOtp());
    }

    public void notifyAgent(Package pkg) {
        DeliveryAgent agent = agentRepo.getById(pkg.getAgentId());
        System.out.println("Notification to Agent [" + agent.getId() + "]: " +
                "Package (ID: " + pkg.getId() + ") has been assigned for delivery from locker '" +
                pkg.getLockerName() + "'.");
    }
}
