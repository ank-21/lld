package main.java.com.phonepeTest.factories;

import main.java.com.phonepeTest.enums.IssueStatus;
import main.java.com.phonepeTest.enums.IssueType;
import main.java.com.phonepeTest.models.Issue;

import java.util.UUID;

public class IssueFactory {
    public static Issue createIssue(IssueType issueType, String description, String subject, String transactionId, String customerEmail, String customerId) {
        return new Issue(UUID.randomUUID().toString(), issueType, description, subject, transactionId, customerEmail, customerId, IssueStatus.TO_DO);
    }
}
