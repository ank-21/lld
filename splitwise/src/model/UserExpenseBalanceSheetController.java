package model;


import model.group.Group;
import model.split.Split;

import java.util.HashMap;
import java.util.List;

public class UserExpenseBalanceSheetController {
    public void updateBalanceSheet(double amount, User paidBy, Group group, List<Split> splits){

        //updating the balance sheet of the user who has paid
        UserExpenseBalanceSheet paidByUserBalanceSheet = paidBy.getBalanceSheet();
        paidByUserBalanceSheet.setTotalPayment(paidByUserBalanceSheet.getTotalPayment() + amount);


        for(Split split : splits){
            User user = split.getUser();
            double splitAmount = split.getAmount();

            if(user.getId().equals(paidBy.getId())){
                user.getBalanceSheet().setTotalGetBack(user.getBalanceSheet().getTotalGetBack() + amount - splitAmount);
            }else{
                //These people owe the user who paid
                //balance sheet of users with the user who paid (Key)
                if(user.getBalanceSheet().getFriendVsBalanceMap().containsKey(paidBy)){
                    //owe user already have a history
                    Balance balance = user.getBalanceSheet().getFriendVsBalanceMap().get(paidBy);
                    balance.setAmountOwe(balance.getAmountOwe() + splitAmount);
                }else{
                    Balance firstBalance = new Balance();
                    firstBalance.setAmountOwe(splitAmount);
                    user.getBalanceSheet().getFriendVsBalanceMap().put(paidBy, firstBalance);
                }

                //balance sheet of paid user with these users as friend(key)
                if(paidByUserBalanceSheet.getFriendVsBalanceMap().containsKey(user)){
                    Balance balance = paidByUserBalanceSheet.getFriendVsBalanceMap().get(user);
                    balance.setAmountGetBack(balance.getAmountGetBack() + splitAmount);
                }else{
                    Balance firstBalance = new Balance();
                    firstBalance.setAmountGetBack(splitAmount);
                    paidByUserBalanceSheet.getFriendVsBalanceMap().put(user, firstBalance);
                }
            }
            user.getBalanceSheet().setTotalExpense(user.getBalanceSheet().getTotalExpense() + splitAmount);
        }
    }

    public void showBalanceOfEachUser(User user){
        String name = user.getName();
        UserExpenseBalanceSheet balanceSheet = user.getBalanceSheet();
        HashMap<User, Balance> userBalanceHashMap = balanceSheet.getFriendVsBalanceMap();

        System.out.println("\n");
        System.out.println("Balance sheet of User : " + name);
        System.out.println("Total payment done by " + name + " is " + balanceSheet.getTotalPayment());
        System.out.println("Total expenses by " + name + " is " + balanceSheet.getTotalExpense());
        System.out.println("Total amount to get back is " + balanceSheet.getTotalGetBack());
        System.out.println("Total amount owe is " + balanceSheet.getTotalOwe());

        System.out.println("Each friend data for user : " + name);

        userBalanceHashMap.forEach((key, value) -> {
            System.out.print("User : " + key.getName() + "\t");
            System.out.print("Amount Owe : " + value.getAmountOwe() + "\t");
            System.out.print("Amount get back : " + value.getAmountGetBack());
            System.out.print("\n");
        });
        System.out.println("\n\n");
    }
}
