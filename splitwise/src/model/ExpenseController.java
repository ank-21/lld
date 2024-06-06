package model;

import model.group.Group;
import model.split.Split;
import model.split.ExpenseSplit;


import java.util.ArrayList;
import java.util.List;

public class ExpenseController {
    List<Expense> expenseList;

    public ExpenseController(){
        this.expenseList = new ArrayList<>();
    }

    public boolean createExpense(double amount, String expenseId, String description, User paidBy, Group group, List<User> owesUserList, ExpenseSplit expenseSplitTypeObj, List<Split> splits){

        if(!expenseSplitTypeObj.validateRequest(amount, splits)){
            return false;
        }

        Expense expense = new Expense(amount, expenseId, description, paidBy, owesUserList, expenseSplitTypeObj.getSplitType(), splits);
        expenseList.add(expense);
        group.addExpense(expense);
        return true;
    }
}
