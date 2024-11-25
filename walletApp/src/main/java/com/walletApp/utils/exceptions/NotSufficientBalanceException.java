package main.java.com.walletApp.utils.exceptions;

import main.java.com.walletApp.models.User;

public class NotSufficientBalanceException extends Exception{
    public NotSufficientBalanceException(User user) {
        super(user.getUserName() + " does not have sufficient Balance for the transaction");
    }
}
