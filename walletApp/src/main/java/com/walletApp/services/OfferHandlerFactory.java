package main.java.com.walletApp.services;

public class OfferHandlerFactory {
    public static OfferHandler getOfferHandler(){
        return new FirstTransactionOfferHandler(new SameBalanceOfferHandler(null ));
    }
}
