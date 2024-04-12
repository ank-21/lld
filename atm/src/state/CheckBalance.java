package state;

import model.ATM;
import model.Card;
import model.TransactionType;

public class CheckBalance implements ATMState{
    ATM atm;

    public CheckBalance(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() throws Exception {
        throw new Exception("Card is already inserted");
    }

    @Override
    public void AuthenticatePin(Card card, int pin) throws Exception {
        throw new Exception("Card is already authenticated");
    }

    @Override
    public void selectOperation(TransactionType type) throws Exception {
        throw new Exception("Operation is already selected");
    }

    @Override
    public void displayBalance(Card card) throws Exception {
        System.out.println("Your card " + card.getCardNumber() + " has a balance of " + card.getAccount().getBalance());
        returnCard();
    }

    @Override
    public void cashWithdrawal(Card card, int withdrawAmount) throws Exception {
        throw new Exception("Display balance is selected");
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
