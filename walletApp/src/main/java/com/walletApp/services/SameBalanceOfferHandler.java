package main.java.com.walletApp.services;

import main.java.com.walletApp.models.Transaction;
import main.java.com.walletApp.models.User;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SameBalanceOfferHandler extends OfferHandler{
    private BigDecimal cashback;
    private CashbackMode cashbackMode;

    public SameBalanceOfferHandler(OfferHandler nextOfferHandler) {
        super(nextOfferHandler);
        this.cashbackMode = CashbackMode.Absolute;
        this.cashback = new BigDecimal(10);
    }

    @Override
    void applyOffer(Transaction transaction) {
        canApplyAndHandleOffer(transaction);
        super.applyOffer(transaction);
    }

    @Override
    protected void canApplyAndHandleOffer(Transaction transaction) {
        String senderId = transaction.getSenderId();
        String receiverId = transaction.getReceiverId();
        User sender = UserService.getInstance().getUsers().get(senderId);
        User receiver = UserService.getInstance().getUsers().get(receiverId);

        if(sender.getWallet().getBalance().equals(receiver.getWallet().getBalance())){
            handleOffer(transaction, sender);
            handleOffer(transaction, receiver);
        }else{
            System.out.println("No offers since the wallet is not same!");
        }
    }

    @Override
    protected void handleOffer(Transaction transaction, User user) {
        BigDecimal cashbackValue;
        if(cashbackMode == CashbackMode.Absolute){
            cashbackValue = cashback.setScale(2, RoundingMode.CEILING);
        }else{
            cashbackValue = cashback.multiply(transaction.getAmount()).setScale(2, RoundingMode.CEILING);
        }
        System.out.println("User " + user.getUserName() + " received a cashback of Rs" + cashbackValue);
        user.getWallet().setBalance(user.getWallet().getBalance().add(cashbackValue));
        // Can add this in transaction - Business call
    }
}
