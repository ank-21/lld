package model.split;

import model.SplitType;

public class ExpenseSplitFactory {
    public static ExpenseSplit getExpenseSplitType(SplitType splitType){

        if(splitType == SplitType.EQUAL){
            return new EqualExpenseSplitType();
        }else if(splitType == SplitType.UNEQUAL){
            return new UnequalExpenseSplitType();
        }else{
            return new PercentageExpenseSplitType();
        }
    }
}
