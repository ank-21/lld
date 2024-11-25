package main.java.com.walletApp.utils.exceptions;

public class TransactionNotSuccessful extends Exception{
    public TransactionNotSuccessful() {
        super("Transaction is not successful");
    }
}
