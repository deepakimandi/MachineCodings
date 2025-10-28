package org.example.CustomerIssueResolution1;

import org.example.CustomerIssueResolution1.enums.IssueStatus;
import org.example.CustomerIssueResolution1.enums.IssueType;
import org.example.CustomerIssueResolution1.model.Issue;
import org.example.CustomerIssueResolution1.repository.AgentRepository;
import org.example.CustomerIssueResolution1.repository.IssueRepository;
import org.example.CustomerIssueResolution1.service.AssignmentService;
import org.example.CustomerIssueResolution1.service.AgentService;
import org.example.CustomerIssueResolution1.service.IssueService;
import org.example.CustomerIssueResolution1.strategy.DefaultAssignmentStrategy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Repositories
        AgentRepository agentRepository = new AgentRepository();
        IssueRepository issueRepository = new IssueRepository();

        // Services
        AgentService agentService = new AgentService(agentRepository);
        IssueService issueService = new IssueService(issueRepository, agentRepository);
        AssignmentService assignmentService = new AssignmentService(
                agentRepository, issueRepository, new DefaultAssignmentStrategy()
        );

        // Create issues
        Issue i1 = issueService.createIssue("T1", IssueType.PAYMENT_RELATED, "Payment failed", "Payment not completed", "testUser1@test.com");
        Issue i2 = issueService.createIssue("T2", IssueType.MUTUAL_FUND_RELATED, "MF query", "Need details of MF", "testUser2@test.com");
        Issue i3 = issueService.createIssue("T3", IssueType.PAYMENT_RELATED, "Payment error", "Payment debited but not processed", "testUser2@test.com");

        // Add agents
        agentService.addAgent("agent1@test.com", "Agent 1", Arrays.asList(IssueType.PAYMENT_RELATED, IssueType.MUTUAL_FUND_RELATED));
        agentService.addAgent("agent2@test.com", "Agent 2", Collections.singletonList(IssueType.PAYMENT_RELATED));

        // Assign issues
        assignmentService.assignIssue(i1.getId());
        assignmentService.assignIssue(i2.getId());
        assignmentService.assignIssue(i3.getId());

        // View filtered issues
        System.out.println("\n--- Issues for testUser2@test.com ---");
        issueService.getIssues(Map.of("email", "testUser2@test.com"))
                .forEach(System.out::println);

        System.out.println("\n--- Payment Related Issues ---");
        issueService.getIssues(Map.of("type", "PAYMENT_RELATED"))
                .forEach(System.out::println);

        // Update and resolve issues
        issueService.updateIssue(i3.getId(), IssueStatus.IN_PROGRESS, "Waiting for payment confirmation");
        issueService.resolveIssue(i3.getId(), "Payment failed. Debited amount will be refunded");

        // View agent work history
        System.out.println("\n--- Agent Work History ---");
        agentService.viewAgentsWorkHistory();
    }
}
