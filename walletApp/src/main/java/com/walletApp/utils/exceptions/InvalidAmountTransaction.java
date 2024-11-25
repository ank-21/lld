package main.java.com.walletApp.utils.exceptions;

public class InvalidAmountTransaction extends Exception{
    public InvalidAmountTransaction() {
        super("The transaction involves amount which is less than equal to 0");
    }
}
