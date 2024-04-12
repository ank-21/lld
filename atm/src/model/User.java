package model;

public class User {
    private Card card;
    private String name;
    private UserBankAccount account;

    public User(String name) {
        this.name = name;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setAccount(UserBankAccount account) {
        this.account = account;
    }

    public Card getCard() {
        return card;
    }

    public String getName() {
        return name;
    }

    public UserBankAccount getAccount() {
        return account;
    }
}
