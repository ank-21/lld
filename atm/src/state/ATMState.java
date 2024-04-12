package state;

import model.ATM;
import model.Card;
import model.TransactionType;

public interface ATMState {
    void insertCard() throws Exception;
    void AuthenticatePin(Card card, int pin) throws Exception;
    void selectOperation(TransactionType type) throws Exception;
    void displayBalance(Card card) throws Exception;
    void cashWithdrawal(Card card, int withdrawAmount) throws Exception;
    void cancel() throws Exception;
    void returnCard() throws Exception;
}
