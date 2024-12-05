package main.java.com.walletApp.models;

import java.math.BigDecimal;

public interface TopUpHandler {
    void handleTopUp(BigDecimal amount);
}
