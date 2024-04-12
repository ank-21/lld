package state;

import model.ATM;
import model.Card;
import model.TransactionType;

public class Idle implements ATMState{
    ATM atm;

    public Idle(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() throws Exception {
        atm.setCurrentATMState(new HasCard(atm));
        System.out.println("Your card has inserted successfully");
    }

    @Override
    public void AuthenticatePin(Card card, int pin) throws Exception {
        throw new Exception("Please insert your card first!");
    }

    @Override
    public void selectOperation(TransactionType type) throws Exception {
        throw new Exception("Please insert your card first!");
    }

    @Override
    public void displayBalance(Card card) throws Exception {
        throw new Exception("Please insert your card first!");
    }

    @Override
    public void cashWithdrawal(Card card, int withdrawAmount) throws Exception {
        throw new Exception("Please insert your card first!");
    }

    @Override
    public void cancel() throws Exception {
        System.out.println("Transaction cancelled");
    }

    @Override
    public void returnCard() throws Exception {
        throw new Exception("Please insert your card first!");
    }
}
