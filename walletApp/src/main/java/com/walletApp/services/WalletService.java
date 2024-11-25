package main.java.com.walletApp.services;

import main.java.com.walletApp.models.TopUpMode;
import main.java.com.walletApp.models.TransactionStatus;
import main.java.com.walletApp.models.User;
import main.java.com.walletApp.utils.exceptions.InvalidAmountTransaction;
import main.java.com.walletApp.utils.exceptions.NotSufficientBalanceException;
import main.java.com.walletApp.utils.exceptions.UserNotFound;

import java.math.BigDecimal;

public class WalletService {
    public void checkBalance(String userId) throws UserNotFound {
        User user = UserService.getInstance().getUsers().get(userId);
        if(user == null){
            throw new UserNotFound(userId);
        }
        System.out.println("The balance of user is " + user.getWallet().getBalance());
    }

    public void topUpWallet(String userId, BigDecimal amount, TopUpMode topUpMode) throws InvalidAmountTransaction, UserNotFound {
        // validateTopUpMode - ToDo

        // Validate User
        if(!UserService.getInstance().validateUser(userId)){
            throw new UserNotFound(userId);
        }
        TransactionService.getInstance().processTopUp(userId, amount, topUpMode);
        checkBalance(userId);
    }

    public void sendMoney(String senderId, String receiverId, BigDecimal amount) throws UserNotFound, InvalidAmountTransaction, NotSufficientBalanceException, InterruptedException {
        if(!UserService.getInstance().validateUser(senderId))
            throw new UserNotFound(senderId);
        if(!UserService.getInstance().validateUser(receiverId))
            throw new UserNotFound(receiverId);

        TransactionService.getInstance().processTransaction(senderId, receiverId, amount);
        checkBalance(receiverId);
        checkBalance(senderId);
    }
}
