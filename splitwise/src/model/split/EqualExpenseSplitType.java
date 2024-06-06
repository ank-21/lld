package model.split;


import model.SplitType;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class EqualExpenseSplitType extends ExpenseSplit{
    SplitType splitType = SplitType.EQUAL;

    @Override
    public boolean validateRequest(double amount, List<Split> split) {
        return true;
    }

    @Override
    public List<Split> computeAmount(double amount, List<User> groupMembers) {
        int groupMemberSize = groupMembers.size();
        List<Split> splits = new ArrayList<>();

        double individualSplitAmount = amount / groupMemberSize;
        double individualSplitPercentage = (double) 100 / groupMemberSize;

        for(User user : groupMembers){
            System.out.println(user.getName() + " share is of " + individualSplitAmount);
            splits.add(new Split(user, individualSplitAmount, individualSplitPercentage));
        }
        return splits;
    }
}
