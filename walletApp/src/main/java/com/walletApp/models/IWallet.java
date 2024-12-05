package main.java.com.walletApp.models;

import java.math.BigDecimal;
import java.util.List;

public interface IWallet {
    BigDecimal getBalance();
    List<Transaction> getTransactions();
    void addTransaction(Transaction transaction);
}
