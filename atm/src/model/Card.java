package model;

public class Card {
    int cvv;
    int pin;
    String CardNumber;
    UserBankAccount account;

    public Card(int cvv, int pin, String cardNumber, UserBankAccount account) {
        this.cvv = cvv;
        this.pin = pin;
        CardNumber = cardNumber;
        this.account = account;
    }

    public int getCvv() {
        return cvv;
    }

    public int getPin() {
        return pin;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public UserBankAccount getAccount() {
        return account;
    }
}
