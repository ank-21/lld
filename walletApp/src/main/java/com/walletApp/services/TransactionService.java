package main.java.com.walletApp.services;

import main.java.com.walletApp.models.*;
import main.java.com.walletApp.utils.exceptions.InvalidAmountTransaction;
import main.java.com.walletApp.utils.exceptions.NotSufficientBalanceException;
import main.java.com.walletApp.utils.exceptions.UserNotFound;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransactionService {
    private static volatile TransactionService INSTANCE;
    private OfferHandler offerHandler;
    private TransactionService(){
        offerHandler = new FirstTransactionOfferHandler(new SameBalanceOfferHandler(null ));
    }

    public static TransactionService getInstance(){
        if(INSTANCE == null){
            synchronized (TransactionService.class){
                if(INSTANCE == null){
                    INSTANCE = new TransactionService();
                }
            }
        }
        return INSTANCE;
    }

    public void processTopUp(String userId, BigDecimal amount, TopUpMode topUpMode) throws InvalidAmountTransaction {
        if(amount.compareTo(new BigDecimal(0)) < 0){
            System.out.println("Minimum TopUp amount should be greater than 0");
            throw new InvalidAmountTransaction();
        }
        switch (topUpMode){
            case UPI -> System.out.println("Adding money to the wallet using UPI");
            case DEBIT_CARD -> System.out.println("Adding money to the wallet using Debit Card");
            case CREDIT_CARD -> System.out.println("Adding money to the wallet using Credit Card");
        }
        User user = UserService.getInstance().getUsers().get(userId);
        user.getWallet().setBalance(user.getWallet().getBalance().add(amount));
    }

    public void processTransaction(String senderId, String receiverId, BigDecimal amount) throws InvalidAmountTransaction, NotSufficientBalanceException, InterruptedException, UserNotFound {
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
