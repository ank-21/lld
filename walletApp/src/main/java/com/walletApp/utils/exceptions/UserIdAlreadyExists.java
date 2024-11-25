package main.java.com.walletApp.utils.exceptions;

public class UserIdAlreadyExists extends Exception{
    public UserIdAlreadyExists(String userId) {
        super("User id " + userId + " already exists!");
    }
}
