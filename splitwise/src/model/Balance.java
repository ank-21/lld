package model;

public class Balance {
    private double amountOwe;
    private double amountGetBack;

    public Balance() {
        amountOwe = 0;
        amountGetBack = 0;
    }

    public double getAmountOwe() {
        return amountOwe;
    }

    public double getAmountGetBack() {
        return amountGetBack;
    }

    public void setAmountOwe(double amountOwe) {
        this.amountOwe = amountOwe;
    }

    public void setAmountGetBack(double amountGetBack) {
        this.amountGetBack = amountGetBack;
    }
}
