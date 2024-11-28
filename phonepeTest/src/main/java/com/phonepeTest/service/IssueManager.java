package main.java.com.phonepeTest.service;

import main.java.com.phonepeTest.factories.IssueFactory;
import main.java.com.phonepeTest.models.CustomerServiceAgent;
import main.java.com.phonepeTest.models.FilteredCriteria;
import main.java.com.phonepeTest.models.Issue;
import main.java.com.phonepeTest.enums.IssueType;
import main.java.com.phonepeTest.strategies.AssignmentStrategy;
import main.java.com.phonepeTest.utils.exception.AgentNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class IssueManager {
    private AgentManager agentManager;
    private final Map<String, Issue> issueDatabase;
    private final AssignmentStrategy assignmentStrategy;

    public IssueManager(AgentManager agentManager, AssignmentStrategy assignmentStrategy) {
        this.agentManager = agentManager;
        this.assignmentStrategy = assignmentStrategy;
        this.issueDatabase = new ConcurrentHashMap<>();
    }

    public Issue raiseIssue(IssueType issueType, String description, String subject, String transactionId, String customerEmail, String customerId) throws AgentNotFoundException {
        Issue issue = IssueFactory.createIssue(issueType, description, subject, transactionId, customerEmail, customerId);
        issueDatabase.put(issue.getId(), issue);
        try {
            assignIssue(issue);
        } catch (AgentNotFoundException e){
            System.err.println("Could not assign issue: " + e.getMessage());
        }
        return issue;
    }

    private synchronized void assignIssue(Issue issue) throws AgentNotFoundException {
        CustomerServiceAgent assignedAgent = assignmentStrategy.assignAgent(agentManager.getAllAgents(), issue);
        assignedAgent.assignIssue(issue);
        System.out.println("Assigned Issue " + issue.getId() + " to Agent " + assignedAgent.getName());
    }

    public Issue searchIssueById(String issueId) {
        return issueDatabase.get(issueId);
    }

    public List<Issue> getIssues(FilteredCriteria criteria) {
        return issueDatabase.values().stream()
                .filter(issue -> criteria.getCustomerEmail() == null || issue.getCustomerEmail().equals(criteria.getCustomerEmail()))
                .filter(issue -> criteria.getIssueType() == null || issue.getType() == criteria.getIssueType())
                .filter(issue -> criteria.getStatus() == null || issue.getStatus() == criteria.getStatus())
                .filter(issue -> criteria.getTransactionId() == null || issue.getTransactionId().equals(criteria.getTransactionId()))
                .collect(Collectors.toList());
    }

    public List<Issue> searchIssuesByCustomerEmail(String email) {
        return issueDatabase.values().stream()
                .filter(issue -> issue.getCustomerEmail().equals(email))
                .collect(Collectors.toList());
    }
}

