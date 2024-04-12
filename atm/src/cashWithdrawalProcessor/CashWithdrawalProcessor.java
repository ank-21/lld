package cashWithdrawalProcessor;

import model.ATM;

public class CashWithdrawalProcessor {

    CashWithdrawalProcessor nextCashWithdrawalProcessor;

    public CashWithdrawalProcessor(CashWithdrawalProcessor nextCashWithdrawalProcessor){
        this.nextCashWithdrawalProcessor = nextCashWithdrawalProcessor;
    }

    public void withdraw(ATM atm, int remainingAmount, int twoThousandDeductedNote, int fiveHundredDeductedNote, int oneHundredDeductedNote) throws Exception{
        if(nextCashWithdrawalProcessor != null){
            nextCashWithdrawalProcessor.withdraw(atm, remainingAmount, twoThousandDeductedNote, fiveHundredDeductedNote, oneHundredDeductedNote);
        }
    }
}
