package main.java.com.walletApp.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Wallet {
    private BigDecimal balance;
    List<Transaction> transactions;

    public Wallet() {
        this.balance = new BigDecimal(0);
        this.transactions = new CopyOnWriteArrayList<>();
    }
}
