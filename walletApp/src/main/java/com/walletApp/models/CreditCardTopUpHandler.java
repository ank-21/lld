package main.java.com.walletApp.models;

import java.math.BigDecimal;

public class CreditCardTopUpHandler implements TopUpHandler{
    @Override
    public void handleTopUp(BigDecimal amount) {
        System.out.println("Adding money to the wallet using Credit Card");
    }
}
