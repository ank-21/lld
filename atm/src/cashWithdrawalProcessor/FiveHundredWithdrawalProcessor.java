package cashWithdrawalProcessor;

import model.ATM;

public class FiveHundredWithdrawalProcessor extends CashWithdrawalProcessor{
    public FiveHundredWithdrawalProcessor(CashWithdrawalProcessor nextCashWithdrawalProcessor){
        super(nextCashWithdrawalProcessor);
    }

    public void withdraw(ATM atm , int remainingAmount, int twoThousandDeductedNote, int fiveHundredDeductedNote, int oneHundredDeductedNote) throws Exception{
        int required = remainingAmount / 500;
        int balance = remainingAmount % 500;

        if(required <= atm.getNoOf500Notes()){
            atm.setNoOf500Notes(atm.getNoOf500Notes() - required);
            fiveHundredDeductedNote = required;
        }else{
            balance = balance + (required - atm.getNoOf500Notes()) * 500;
            fiveHundredDeductedNote = atm.getNoOf500Notes();
            atm.setNoOf500Notes(0);
        }

        if(balance != 0){
            //calling its next cashwithdrawal i.e 100 cash withdrawal to take money
            super.withdraw(atm, balance, twoThousandDeductedNote, fiveHundredDeductedNote, oneHundredDeductedNote);
        }else{
            System.out.println("You received " + twoThousandDeductedNote + " 2000 notes, " + fiveHundredDeductedNote + " 500 notes");
        }
    }
}
