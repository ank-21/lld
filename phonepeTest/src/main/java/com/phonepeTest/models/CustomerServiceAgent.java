package main.java.com.phonepeTest.models;

import main.java.com.phonepeTest.enums.IssueStatus;
import main.java.com.phonepeTest.enums.IssueType;

import java.util.*;

public class CustomerServiceAgent {
    private String id;
    private String name;

    // Set for avoiding duplicacy
    private Set<IssueType> expertise;
    private List<Issue> assignedIssues;


    public CustomerServiceAgent(String id, String name, Set<IssueType> expertise) {
        this.id = id;
        this.name = name;
        this.expertise = expertise;
        this.assignedIssues = Collections.synchronizedList(new ArrayList<>());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<IssueType> getExpertise() {
        return expertise;
    }

    public List<Issue> getAssignedIssues() {
        return assignedIssues;
    }

    public void markIssueResolved(String issueId) {
        synchronized (assignedIssues) { // Synchronized block for iteration
            for (Issue issue : assignedIssues) {
                if (issue.getId().equals(issueId) && issue.getStatus() != IssueStatus.RESOLVED) {
                    issue.setStatus(IssueStatus.RESOLVED);
                    System.out.println("Issue " + issueId + " resolved by " + name);
                    return;
                }
            }
            System.out.println("Issue not found or already resolved.");
        }
    }

    public void assignIssue(Issue issue) {
        assignedIssues.add(issue);  // Thread-safe addition
    }

    @Override
    public String toString() {
        return "CustomerServiceAgent{id='" + id + "', name='" + name + "', expertise=" + expertise +
                ", assignedIssues=" + assignedIssues + "}";
    }

    public boolean canHandleIssue(IssueType type) {
        return expertise.contains(type);
    }
}
