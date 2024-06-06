package model.split;

import model.SplitType;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UnequalExpenseSplitType extends ExpenseSplit{
    SplitType splitType = SplitType.UNEQUAL;

    @Override
    public boolean validateRequest(double amount, List<Split> split) {
        return true;
    }

    @Override
    public List<Split> computeAmount(double amount, List<User> groupMembers) {
        int groupMemberSize = groupMembers.size();
        List<Split> splits = new ArrayList<>();

        for (int i = 0; i < groupMemberSize; i++){
            double individualSplitAmount = amount / groupMemberSize * i;
            double individualSplitPercentage = (double) 100 / groupMemberSize * i;
            System.out.println(groupMembers.get(i).getName() + " share is of " + individualSplitAmount);
            splits.add(new Split(groupMembers.get(i), individualSplitAmount, individualSplitPercentage));
        }
        return splits;
    }
}
