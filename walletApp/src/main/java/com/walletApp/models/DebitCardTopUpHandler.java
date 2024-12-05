package main.java.com.walletApp.models;

import java.math.BigDecimal;

public class DebitCardTopUpHandler implements TopUpHandler{
    @Override
    public void handleTopUp(BigDecimal amount) {
        System.out.println("Adding money to the wallet using Debit Card");
    }
}
