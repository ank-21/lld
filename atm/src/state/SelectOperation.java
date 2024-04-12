package state;

import model.ATM;
import model.Card;
import model.TransactionType;

public class SelectOperation implements ATMState{
    ATM atm;

    public SelectOperation(ATM atm) {
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
        if(type == TransactionType.DISPLAY_BALANCE){
            atm.setCurrentATMState(new CheckBalance(atm));
        }else if (type == TransactionType.CASH_WITHDRAWAL){
            atm.setCurrentATMState(new CashWithdrawal(atm));
        }else{
            throw new Exception("Invalid option!");
        }
    }

    @Override
    public void displayBalance(Card card) throws Exception {
        throw new Exception("Please select the operation!");
    }

    @Override
    public void cashWithdrawal(Card card, int withdrawAmount) throws Exception {
        throw new Exception("Please select the operation!");
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
