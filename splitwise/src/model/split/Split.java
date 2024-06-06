package model.split;

import model.User;

public class Split {
    private User user;
    private double amount;
    private double percentage;

    public Split(User user, double amount, double percentage) {
        this.user = user;
        this.amount = amount;
        this.percentage = percentage;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public double getPercentage() {
        return percentage;
    }
}
