package models;

import factories.RepaymentTenureFactory;
import strategies.InterestStrategy;

import java.util.ArrayList;
import java.util.List;

public class Loan {
    private double principalAmount;
    private double interestRate;
    private int tenure;
    private String adminUserName;
    private String customerUserName;
    private List<Double> emiPayments;
    private double emiPaid;
    private double remainingAmount;
    private RepaymentTenure repaymentTenure;

    public Loan(double principalAmount, double interestRate, int tenure, String adminUserName, String customerUserName) {
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.tenure = tenure;
        this.adminUserName = adminUserName;
        this.customerUserName = customerUserName;
        this.emiPayments = new ArrayList<>();
        this.emiPaid = 0;
        this.remainingAmount = principalAmount;
        setRepaymentTenure(RepaymentTenure.QUARTERLY);
    }

    public void fetchInfoAboutLoan(){
        System.out.println("Loan Details");
        System.out.println("Customer : " + this.customerUserName);
        System.out.println("Principal Amount : " + this.principalAmount);
        System.out.println("Interest Rate : " + this.interestRate);
        System.out.println("Tenure : " + this.tenure);
        System.out.println("Repayment tenure : " + this.repaymentTenure);
        System.out.println("EMI Paid : " + this.emiPaid);
        System.out.println("Principal Amount remaining : " + this.remainingAmount);
        System.out.println("Admin : " + this.adminUserName);
    }

    public void calculateEMIPayment(InterestStrategy interestStrategy){
        double interest =  interestStrategy.calculateInterest(principalAmount, interestRate, tenure);
        double totalAmt = interest + this.principalAmount;

        int totalTime = RepaymentTenureFactory.getTime(this.repaymentTenure, tenure);
        double emi = totalAmt / totalTime;

        for(int i = 0; i < totalTime; i++){
            emiPayments.add(emi);
        }
    }

    public void makeEMIPayment(){
        if(remainingAmount != 0 && !emiPayments.isEmpty()){
            double amount = emiPayments.getFirst();
            emiPayments.removeFirst();
            this.remainingAmount -= amount;
            this.emiPaid += amount;
        }
    }

    public void setRepaymentTenure(RepaymentTenure repaymentTenure) {
        this.repaymentTenure = repaymentTenure;
    }
}
