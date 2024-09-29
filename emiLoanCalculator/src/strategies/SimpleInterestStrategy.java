package strategies;

public class SimpleInterestStrategy implements InterestStrategy{
    @Override
    public double calculateInterest(double principalAmount, double rate, int tenure) {
        return principalAmount * rate * tenure / 100;
    }
}
