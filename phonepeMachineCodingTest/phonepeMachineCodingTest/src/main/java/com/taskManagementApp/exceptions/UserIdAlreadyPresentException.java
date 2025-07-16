package main.java.com.taskManagementApp.exceptions;

public class UserIdAlreadyPresentException extends Exception{
    public UserIdAlreadyPresentException(String message) {
        super(message);
    }
}
