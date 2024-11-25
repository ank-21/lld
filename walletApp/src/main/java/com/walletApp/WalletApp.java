package main.java.com.walletApp;

import main.java.com.walletApp.models.TopUpMode;
import main.java.com.walletApp.services.TransactionService;
import main.java.com.walletApp.services.UserService;
import main.java.com.walletApp.services.WalletService;

import java.math.BigDecimal;

public class WalletApp {
    public static void main(String[] args) throws Exception {
        UserService userService = UserService.getInstance();
        WalletService walletService = new WalletService();

        // Create user
        userService.registerUser("ank21", "Ankit");
        userService.registerUser("min26", "Minal");

        // Check balance
        // walletService.checkBalance("ank21");

        walletService.topUpWallet("ank21", new BigDecimal(100), TopUpMode.UPI);

        walletService.sendMoney("ank21", "min26", new BigDecimal(50));
    }
}
