package model;

import java.util.HashMap;

public class UserExpenseBalanceSheet {
    private HashMap<User, Balance> FriendVsBalanceMap;
    private double totalPayment;
    private double totalOwe;
    private double totalGetBack;
    private double totalExpense;

    public UserExpenseBalanceSheet() {
        FriendVsBalanceMap = new HashMap<>();
        totalExpense = 0;
        totalPayment = 0;
        totalOwe = 0;
        totalGetBack = 0;
    }

    public HashMap<User, Balance> getFriendVsBalanceMap() {
        return FriendVsBalanceMap;
    }

    public void setFriendVsBalanceMap(HashMap<User, Balance> friendVsBalanceMap) {
        FriendVsBalanceMap = friendVsBalanceMap;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public double getTotalOwe() {
        return totalOwe;
    }

    public void setTotalOwe(double totalOwe) {
        this.totalOwe = totalOwe;
    }

    public double getTotalGetBack() {
        return totalGetBack;
    }

    public void setTotalGetBack(double totalGetBack) {
        this.totalGetBack = totalGetBack;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }
}
