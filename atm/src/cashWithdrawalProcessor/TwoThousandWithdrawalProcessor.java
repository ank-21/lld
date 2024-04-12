package cashWithdrawalProcessor;

import model.ATM;

public class TwoThousandWithdrawalProcessor extends CashWithdrawalProcessor{
    public TwoThousandWithdrawalProcessor(CashWithdrawalProcessor nextCashWithdrawalProcessor){
        super(nextCashWithdrawalProcessor);
    }

    @Override
    public void withdraw(ATM atm, int remainingAmount, int twoThousandDeductedNote, int fiveHundredDeductedNote, int oneHundredDeductedNote) throws Exception {

        int required =  remainingAmount / 2000;
        int balance = remainingAmount % 2000;

        if(required <= atm.getNoOf2kNotes()) {
            atm.setNoOf2kNotes(atm.getNoOf2kNotes() - required);
            twoThousandDeductedNote = required;
        }
        else {
            balance = balance + (required - atm.getNoOf2kNotes()) * 2000;
            twoThousandDeductedNote = atm.getNoOf2kNotes();
            atm.setNoOf2kNotes(0);
        }

        if(balance != 0){
            //calling its next cash withdrawal i.e. 500 cash withdrawal to take money
            super.withdraw(atm, balance, twoThousandDeductedNote, fiveHundredDeductedNote, oneHundredDeductedNote);
        }else{
            System.out.println("You received " + twoThousandDeductedNote + " 2000 notes");
        }

    }
}
