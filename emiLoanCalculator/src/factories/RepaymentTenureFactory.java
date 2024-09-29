package factories;

import models.RepaymentTenure;

public class RepaymentTenureFactory {
    public static int getTime(RepaymentTenure repaymentTenure, int tenure){
        switch (repaymentTenure){
            case MONTHLY -> {
                return 12 * tenure;
            }
            case SEMI_ANNUALY -> {
                return 6 * tenure;
            }
            case QUARTERLY -> {
                return 4 * tenure;
            }
        }
        return 0;
    }
}
