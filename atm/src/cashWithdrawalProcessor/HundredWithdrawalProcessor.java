package cashWithdrawalProcessor;

import model.ATM;

public class HundredWithdrawalProcessor extends CashWithdrawalProcessor{
    public HundredWithdrawalProcessor(CashWithdrawalProcessor nextCashWithdrawalProcessor){
        super(nextCashWithdrawalProcessor);
    }

    public void withdraw(ATM atm, int remainingAmount, int twoThousandDeductedNote, int fiveHundredDeductedNote, int oneHundredDeductedNote) throws Exception{
        int required = remainingAmount / 100;
        int balance = remainingAmount % 100;

        if(required <= atm.getNoOf100Notes()){
            atm.setNoOf100Notes(atm.getNoOf100Notes() - required);
            oneHundredDeductedNote = required;
        }else{
            balance = balance + (required - atm.getNoOf100Notes()) * 100;
            oneHundredDeductedNote = atm.getNoOf100Notes();
            atm.setNoOf100Notes(0);
        }
        if (balance != 0){
            atm.setNoOf2kNotes(atm.getNoOf2kNotes() + twoThousandDeductedNote);
            atm.setNoOf500Notes(atm.getNoOf500Notes() + fiveHundredDeductedNote);
            atm.setNoOf100Notes(atm.getNoOf100Notes() + oneHundredDeductedNote);
            throw new Exception("We support notes of 2000, 500, 100 only.");
        }else{
            System.out.println("You received " + twoThousandDeductedNote + " 2000 notes, " + fiveHundredDeductedNote + " 500 notes, " + oneHundredDeductedNote + " 100 notes.");
        }
    }
}
