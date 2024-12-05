package main.java.com.walletApp.models;

public class TopUpHandlerFactory {
    public static TopUpHandler getTopUpHandler(TopUpMode topUpMode){
        return switch (topUpMode) {
            case UPI -> new UPITopUpHandler();
            case DEBIT_CARD -> new DebitCardTopUpHandler();
            case CREDIT_CARD -> new CreditCardTopUpHandler();
        };
    }
}
