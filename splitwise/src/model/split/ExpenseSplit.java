package model.split;

import model.SplitType;
import model.User;

import java.util.List;

public abstract class ExpenseSplit {
    SplitType splitType;
    public abstract boolean validateRequest(double amount, List<Split> split);
    public abstract List<Split> computeAmount(double amount, List<User> groupMembers);

    public SplitType getSplitType() {
        return splitType;
    }
}
