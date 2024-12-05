package main.java.com.walletApp.services;

import main.java.com.walletApp.models.Transaction;
import main.java.com.walletApp.models.TransactionStatus;
import main.java.com.walletApp.models.User;
import main.java.com.walletApp.models.Wallet;
import main.java.com.walletApp.utils.exceptions.InvalidAmountTransaction;
import main.java.com.walletApp.utils.exceptions.NotSufficientBalanceException;
import main.java.com.walletApp.utils.exceptions.UserNotFound;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WalletToWalletTransactionProcessor implements TransactionProcessor{
    @Override
    public void process(String senderId, String receiverId, BigDecimal amount, OfferHandler offerHandler) throws InvalidAmountTransaction, UserNotFound, NotSufficientBalanceException {
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidAmountTransaction();
        }
        User sender = UserService.getInstance().getUsers().get(senderId);
        User receiver = UserService.getInstance().getUsers().get(receiverId);

        if(sender == null){
            throw new UserNotFound(senderId);
        }

        if(receiver == null){
            throw new UserNotFound(receiverId);
        }


        Wallet senderWallet = sender.getWallet();
        Wallet receiverWallet = receiver.getWallet();


        synchronized (senderWallet){
            synchronized (receiverWallet){
                // Check sender balance
                if(senderWallet.getBalance().compareTo(amount) < 0){
                    throw new NotSufficientBalanceException(sender);
                }
                // Deduct and credit balances
                senderWallet.setBalance(senderWallet.getBalance().subtract(amount));
                receiverWallet.setBalance(receiverWallet.getBalance().add(amount));

                // Create and Store Transaction
                Transaction transaction = new Transaction(senderId, receiverId, TransactionStatus.INITIATED, amount);

                senderWallet.addTransaction(transaction);
                receiverWallet.addTransaction(transaction);

                System.out.println("The transaction of amount " + amount.setScale(2, RoundingMode.CEILING) + " is successful");
                transaction.setTransactionStatus(TransactionStatus.SUCCESS);

                // Apply cashback - Offer
                offerHandler.applyOffer(transaction);
            }
        }
    }
}
