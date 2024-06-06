import model.*;
import model.group.Group;
import model.group.GroupController;
import model.split.ExpenseSplitFactory;
import model.split.Split;
import model.split.ExpenseSplit;

import java.util.ArrayList;
import java.util.List;

public class Splitwise {
    UserController userController;
    GroupController groupController;
    UserExpenseBalanceSheetController userExpenseBalanceSheetController;
    ExpenseController expenseController;

    public Splitwise(){
        userController = new UserController();
        groupController = new GroupController();
        userExpenseBalanceSheetController = new UserExpenseBalanceSheetController();
        expenseController = new ExpenseController();
        //having a single expenseController which would manage all the expenses
    }

    void run(){
        addUser();
        addGroup();
        addMembersInGroup();
        addExpense(userController.getUser("A@21"), groupController.getGroup("G01"), 2000.0, "E01", "Food", SplitType.EQUAL);
        addExpense(userController.getUser("M@26"), groupController.getGroup("G01"), 1000.0, "E02", "Water activities", SplitType.UNEQUAL);
        addExpense(userController.getUser("A@21"), groupController.getGroup("G02"), 1000.0, "E02", "Drink and game", SplitType.EQUAL);
        showBalance();
    }

    private void addUser(){
        User user1 = new User("Ankit", "A@21", "ankitsrivastava21598@gmail.com");
        User user2 = new User("Aayusha", "AA@11", "aayusha1121@gmail.com");
        User user3 = new User("Minal", "M@26", "minal26@gmail.com");
        User user4 = new User("Mohit", "M@25", "mohit@gmail.com");

        userController.addUser(user1);
        userController.addUser(user2);
        userController.addUser(user3);
        userController.addUser(user4);
    }

    private void addGroup(){
        User user = userController.getUser("A@21");
        Group group1 = new Group("G01", "Party in Goa", "Trip in Goa finally", user);
        Group group2 = new Group("G02", "Fun in Mall", "Party at the pub in DLF mall", user);

        groupController.addGroup(group1);
        groupController.addGroup(group2);
    }

    private void addMembersInGroup(){
        Group group1 = groupController.getGroup("G01");
        group1.addMembers(userController.getUser("M@26"));
        group1.addMembers(userController.getUser("M@25"));

        Group group2 = groupController.getGroup("G02");
        group2.addMembers(userController.getUser("AA@11"));
    }

    private void addExpense(User paidBy, Group group, double amount, String expenseId, String description, SplitType splitType){
        List<User> groupMembers = group.getGroupMembers();
        //will call validateMember() for the group

        List<User> owesUserList = new ArrayList<>();
        for(User user : groupMembers){
            if(!user.getId().equals(paidBy.getId())){
                owesUserList.add(user);
            }
        }

        ExpenseSplit expenseSplitTypeObj = ExpenseSplitFactory.getExpenseSplitType(splitType);

        System.out.println("Expense done of Rs " + amount + " by " + paidBy.getName() + " for " + description);

        List<Split> splits = expenseSplitTypeObj.computeAmount(amount, groupMembers);



        boolean successfulExpense = expenseController.createExpense(amount, expenseId, description, paidBy, group, owesUserList, expenseSplitTypeObj, splits);
        if(successfulExpense){
            userExpenseBalanceSheetController.updateBalanceSheet(amount, paidBy, group, splits);
        }else{
            System.out.println("The transaction is unsuccessful");
        }
    }

    private void showBalance(){
        for(User user : userController.getUserList()){
            ShowBalanceForEachUser(user);
        }
    }
    private void ShowBalanceForEachUser(User user){
        userExpenseBalanceSheetController.showBalanceOfEachUser(user);
    }
}
