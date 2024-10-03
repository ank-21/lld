package models;

import java.time.LocalDateTime;

public class Transaction {
    private final String transactionId;
    private final LocalDateTime createdAt;
    private final double amount;
    private TransactionStatus transactionStatus;

    public Transaction(String transactionId, double amount) {
        this.transactionId = transactionId;
        this.createdAt = LocalDateTime.now();
        this.amount = amount;
        this.transactionStatus = TransactionStatus.INITIATED;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public synchronized void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public synchronized TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }
}
