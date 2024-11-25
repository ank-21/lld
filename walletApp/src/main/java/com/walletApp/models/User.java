package main.java.com.walletApp.models;

public class User {
    private String userId;
    private String userName;
    private Wallet wallet;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.wallet = new Wallet();
    }
}
