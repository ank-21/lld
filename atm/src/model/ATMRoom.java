package model;

import state.Idle;

import java.util.Scanner;

// There should be another class handling this class
public class ATMRoom {
    private static User user;
    private static ATM atm;
    public static void main(String[] args) {
        atm = ATM.getInstance();
        setupATM(atm);
        createUser();
        atm.displayCurrentATMStatus();
        try{
            atm.getCurrentATMState().insertCard();
            atm.getCurrentATMState().AuthenticatePin(user.getCard(), 497);

            TransactionType type = selectOperation();
            atm.getCurrentATMState().selectOperation(type);
            if(type == TransactionType.CASH_WITHDRAWAL){
                System.out.println("Please enter the withdrawal amount");
                Scanner sc = new Scanner(System.in);
                int amount = sc.nextInt();
                atm.getCurrentATMState().cashWithdrawal(user.getCard(), amount);
            }else if(type == TransactionType.DISPLAY_BALANCE){
                atm.getCurrentATMState().displayBalance(user.getCard());
            }else{
                throw new Exception("Invalid option");
            }

        } catch (Exception e){
            System.out.println("Got an exception " + e.getMessage());
        }
    }

    private static TransactionType selectOperation(){
        TransactionType.showAllTransactionTypes();
        System.out.println("Please select one option");

        String option;
        Scanner sc = new Scanner(System.in);
        option = sc.nextLine().trim();

        if("CASH_WITHDRAWAL".equalsIgnoreCase(option)){
            return TransactionType.valueOf(option.toUpperCase());
        } else if ("DISPLAY_BALANCE".equalsIgnoreCase(option)) {
            return TransactionType.valueOf(option.toUpperCase());
        }
        return null;
    }

    private static void setupATM(ATM atm){
        atm.setBalance(8000);
        atm.setNoOf2kNotes(2);
        atm.setNoOf500Notes(5);
        atm.setNoOf100Notes(15);
        atm.setCurrentATMState(new Idle(atm));
    }

    private static void createUser(){
        user = new User("Ankit");
        UserBankAccount bankAccount = createUserAccount();
        user.setCard(new Card(123, 497, "123456784321", bankAccount));
        user.setAccount(bankAccount);
    }

    private static UserBankAccount createUserAccount(){
        return new UserBankAccount(3000);
    }
}
