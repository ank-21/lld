package state;

import cashWithdrawalProcessor.CashWithdrawalProcessor;
import cashWithdrawalProcessor.FiveHundredWithdrawalProcessor;
import cashWithdrawalProcessor.HundredWithdrawalProcessor;
import cashWithdrawalProcessor.TwoThousandWithdrawalProcessor;
import model.ATM;
import model.Card;
import model.TransactionType;

public class CashWithdrawal implements ATMState{
    ATM atm;

    public CashWithdrawal(ATM atm) {
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
        throw new Exception("Wrong option selected");
    }

    @Override
    public void cashWithdrawal(Card card, int withdrawAmount) throws Exception {
        int currentAccountBalance = card.getAccount().getBalance();
        if(withdrawAmount > currentAccountBalance){
            throw new Exception("Sorry, but you don't have the required balance");
        }
        CashWithdrawalProcessor cashWithdrawalProcessor = new TwoThousandWithdrawalProcessor(new FiveHundredWithdrawalProcessor(new HundredWithdrawalProcessor(null)));
        cashWithdrawalProcessor.withdraw(atm, withdrawAmount, 0, 0, 0);
        card.getAccount().setBalance(currentAccountBalance - withdrawAmount);
        System.out.println("Your remaining balance is " + card.getAccount().getBalance());
        returnCard();
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
