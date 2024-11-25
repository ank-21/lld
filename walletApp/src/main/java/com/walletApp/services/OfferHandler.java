package main.java.com.walletApp.services;

import main.java.com.walletApp.models.Transaction;
import main.java.com.walletApp.models.User;

import java.math.BigDecimal;

public abstract class OfferHandler {
    private OfferHandler nextOfferHandler;

    public OfferHandler(OfferHandler nextOfferHandler){
        this.nextOfferHandler = nextOfferHandler;
    }

    void applyOffer(Transaction transaction){
        if(nextOfferHandler != null){
            nextOfferHandler.applyOffer(transaction);
        }
    }

    protected abstract void canApplyAndHandleOffer(Transaction transaction);
    protected abstract void handleOffer(Transaction transaction, User user);
}
