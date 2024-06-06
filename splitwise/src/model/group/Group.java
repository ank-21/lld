package model.group;

import model.Expense;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupId;
    private String groupName;
    private String description;
    private List<User> groupMembers;
    private List<Expense> groupExpenseList;

    public Group(String groupId, String groupName, String description, User user) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.description = description;
        this.groupMembers = new ArrayList<>();
        addMembers(user);
        this.groupExpenseList = new ArrayList<>();
    }

    public void addMembers(User user){
        groupMembers.add(user);
    }

    public String getGroupId() {
        return groupId;
    }
    
    public void addExpense(Expense expense){
        groupExpenseList.add(expense);
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDescription() {
        return description;
    }

    public List<User> getGroupMembers() {
        return groupMembers;
    }

    public List<Expense> getExpenseList() {
        return groupExpenseList;
    }
}
