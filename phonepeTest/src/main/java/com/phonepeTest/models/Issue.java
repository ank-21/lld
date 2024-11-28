package main.java.com.phonepeTest.models;

import main.java.com.phonepeTest.enums.IssueStatus;
import main.java.com.phonepeTest.enums.IssueType;

import java.time.LocalDateTime;

public class Issue {
    private String id;
    private String transactionId;
    private IssueType type;
    private String subject;
    private String description;
    private String customerEmail;
    private String customerId;
    private IssueStatus status;
    private LocalDateTime createdAt;

    public Issue(String id, IssueType type, String description, String subject, String transactionId, String customerEmail, String customerId, IssueStatus status) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.subject = subject;
        this.transactionId = transactionId;
        this.customerEmail = customerEmail;
        this.customerId = customerId;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public IssueType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getSubject() {
        return subject;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public IssueStatus getStatus() {
        return status;
    }
    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Issue{id='" + id + "', customerId='" + customerId + "', customerEmail='" + customerEmail +
                "', type=" + type + ", status=" + status + ", description='" + description + "'}";
    }
}
