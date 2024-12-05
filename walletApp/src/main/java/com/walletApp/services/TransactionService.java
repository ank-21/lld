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
        offerHandler = OfferHandlerFactory.getOfferHandler();
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
        TopUpHandler handler = TopUpHandlerFactory.getTopUpHandler(topUpMode);
        handler.handleTopUp(amount);
        User user = UserService.getInstance().getUsers().get(userId);
        user.getWallet().setBalance(user.getWallet().getBalance().add(amount));
    }

    public void processTransaction(String senderId, String receiverId, BigDecimal amount) throws InvalidAmountTransaction, NotSufficientBalanceException, InterruptedException, UserNotFound {
        TransactionProcessor transactionProcessor = TransactionProcessorFactory.getTransactionProcessor(TransactionType.WALLET_TO_WALLET);
        transactionProcessor.process(senderId, receiverId, amount, offerHandler);
    }
}
