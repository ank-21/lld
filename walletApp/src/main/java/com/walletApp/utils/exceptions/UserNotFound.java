package main.java.com.walletApp.utils.exceptions;

public class UserNotFound extends Exception{
    public UserNotFound(String userId) {
        super("User not Found with userId " + userId);
    }
}
