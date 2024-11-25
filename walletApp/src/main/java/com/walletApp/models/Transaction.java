package main.java.com.walletApp.models;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private String senderId;
    private String receiverId;
    private LocalDateTime createdAt;
    private TransactionStatus transactionStatus;
}
