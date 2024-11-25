package main.java.com.walletApp.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private String transactionId;
    private String senderId;
    private String receiverId;
    private LocalDateTime createdAt;
    private TransactionStatus transactionStatus;
    private BigDecimal amount;

    public Transaction(String senderId, String receiverId, TransactionStatus transactionStatus, BigDecimal amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.createdAt = LocalDateTime.now();
        this.transactionStatus = transactionStatus;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
