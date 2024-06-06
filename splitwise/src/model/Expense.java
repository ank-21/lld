package model;

import model.split.Split;

import java.util.List;

public class Expense {
    double amount;
    private String expenseId;
    private String description;
    private User paidBy;
    private List<User> owesUserList;
    private SplitType splitType;
    private List<Split> splits;


    public Expense(double amount, String expenseId, String description, User paidBy, List<User> owesUserList, SplitType splitType, List<Split> splits) {
        this.amount = amount;
        this.expenseId = expenseId;
        this.description = description;
        this.paidBy = paidBy;
        this.owesUserList = owesUserList;
        this.splitType = splitType;
        this.splits = splits;
    }

    public double getAmount() {
        return amount;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getDescription() {
        return description;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<User> getOwesUserList() {
        return owesUserList;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public List<Split> getSplits() {
        return splits;
    }
}
