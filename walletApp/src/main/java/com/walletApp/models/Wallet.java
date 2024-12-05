package main.java.com.walletApp.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Wallet implements IWallet{
    private BigDecimal balance;
    List<Transaction> transactions;

    public Wallet() {
        this.balance = new BigDecimal(0);
        this.transactions = new CopyOnWriteArrayList<>();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }
}
