package services;

import models.Admin;
import models.Loan;
import models.User;
import strategies.InterestStrategy;
import strategies.SimpleInterestStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoanService {
    private static volatile LoanService instance = null;
    private InterestStrategy interestStrategy;
    private UserService userService;

    private HashMap<String, List<Loan>> loans;

    private LoanService(){
        interestStrategy = new SimpleInterestStrategy();
        userService = UserService.getInstance();
        loans = new HashMap<>();
    }

    public static LoanService getInstance(){
        if(instance == null){
            synchronized (LoanService.class){
                if(instance == null){
                    instance = new LoanService();
                }
            }
        }
        return instance;
    }

    public void createLoan(double principalAmount, double interestRate, int tenure, String adminUserName, String customerUserName){
        User admin = userService.getUser(adminUserName);
        User customer = userService.getUser(customerUserName);
        if(admin instanceof Admin && customer != null){
            Loan loan = new Loan(principalAmount, interestRate, tenure, adminUserName, customerUserName);
            loan.calculateEMIPayment(this.interestStrategy);
            loans.computeIfAbsent(customerUserName, k -> new ArrayList<>()).add(loan);
            System.out.println("Loan added successfully...");
            showLoansOfUser(customerUserName);
        }
    }

    public void makeEMIPayments(String customerUserName){
        List<Loan> customerLoans = loans.get(customerUserName);
        if(customerLoans != null && !customerLoans.isEmpty()){
            for(Loan loan : customerLoans){
                loan.makeEMIPayment();
            }
            System.out.println("Loan EMI paid successfully for " + userService.getUser(customerUserName).getName());
            showLoansOfUser(customerUserName);
        }else{
            System.out.println("No active loans found for " + userService.getUser(customerUserName).getName());
        }
    }

    public void fetchLoanOfAnUser(String customerUserName, String requestedUserName){
        System.out.println("Loans view request by " + userService.getUser(requestedUserName).getName());
        if(customerUserName.equals(requestedUserName) || userService.getUser(requestedUserName) instanceof Admin){
            showLoansOfUser(customerUserName);
        }else{
            System.out.println("Access Denied, Please check loans of yours");
        }
    }

    private void showLoansOfUser(String customerUserName){
        List<Loan> customerLoans = loans.get(customerUserName);
        if(customerLoans != null && !customerLoans.isEmpty()){
            for(Loan loan : customerLoans){
                loan.fetchInfoAboutLoan();
            }
            System.out.println("---------------------------------------------");
        }else{
            System.out.println("No active loans found for " + userService.getUser(customerUserName).getName());
        }
    }

}
