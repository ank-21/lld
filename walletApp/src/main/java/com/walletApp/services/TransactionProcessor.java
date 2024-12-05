package main.java.com.walletApp.services;

import main.java.com.walletApp.utils.exceptions.InvalidAmountTransaction;
import main.java.com.walletApp.utils.exceptions.NotSufficientBalanceException;
import main.java.com.walletApp.utils.exceptions.UserNotFound;

import java.math.BigDecimal;

public interface TransactionProcessor {
    void process(String senderId, String receiverId, BigDecimal amount, OfferHandler offerHandler) throws InvalidAmountTransaction, UserNotFound, NotSufficientBalanceException;
}
