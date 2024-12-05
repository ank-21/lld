package main.java.com.walletApp.services;

import java.math.BigDecimal;

public class WalletToBankTransactionProcessor implements TransactionProcessor{
    @Override
    public void process(String senderId, String receiverId, BigDecimal amount, OfferHandler offerHandler) {
        // Apply Business logic here
    }
}
