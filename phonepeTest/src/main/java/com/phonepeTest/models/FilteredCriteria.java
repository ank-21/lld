package main.java.com.phonepeTest.models;

import main.java.com.phonepeTest.enums.IssueStatus;
import main.java.com.phonepeTest.enums.IssueType;

public class FilteredCriteria {
    private String customerEmail;
    private IssueType issueType;
    private IssueStatus status;
    private String transactionId;

    // Constructors
    public FilteredCriteria() {
    }

    public FilteredCriteria(String customerEmail, IssueType issueType, IssueStatus status, String transactionId) {
        this.customerEmail = customerEmail;
        this.issueType = issueType;
        this.status = status;
        this.transactionId = transactionId;
    }

    // Getters and Setters
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    @Override
    public String toString() {
        return "FilteredCriteria{" +
                "customerEmail='" + customerEmail + '\'' +
                ", issueType=" + issueType +
                ", status=" + status +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
