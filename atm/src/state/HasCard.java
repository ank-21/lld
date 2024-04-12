package state;

import model.ATM;
import model.Card;
import model.TransactionType;

public class HasCard implements ATMState{
    ATM atm;

    public HasCard(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() throws Exception {
        throw new Exception("Card also inserted");
    }

    @Override
    public void AuthenticatePin(Card card, int pin) throws Exception {
        if(card.getPin() == pin){
            atm.setCurrentATMState(new SelectOperation(atm));
            System.out.println("Pin Verified");
        }
    }

    @Override
    public void selectOperation(TransactionType type) throws Exception {
        throw new Exception("Please Authenticate card first!");
    }

    @Override
    public void displayBalance(Card card) throws Exception {
        throw new Exception("Please Authenticate card first!");
    }

    @Override
    public void cashWithdrawal(Card card, int withdrawAmount) throws Exception {
        throw new Exception("Please Authenticate card first!");
    }

    @Override
    public void cancel() throws Exception {
        System.out.println("Transaction cancelled");
        atm.setCurrentATMState(new Idle(atm));
        returnCard();
    }

    @Override
    public void returnCard() throws Exception {
        System.out.println("Your card has been returned");
    }
}
