import services.LoanService;
import services.UserService;

public class EMICalculator {
    public static void main(String[] args) {
        UserService userService = UserService.getInstance();
        LoanService loanService = LoanService.getInstance();

        userService.addUser("ank21", "Ankit", true);
        userService.addUser("aay11", "Aayusha", false);
        userService.addUser("muk17", "Mukul", false);
        userService.addUser("mi26", "Minal", false);

        loanService.createLoan(100000, 8.4, 3, "ank21", "aay11");
        loanService.makeEMIPayments("aay11");
        loanService.fetchLoanOfAnUser("aay11", "muk17");
        loanService.fetchLoanOfAnUser("aay11", "ank21");
        loanService.createLoan(50000, 7.6, 5, "ank21", "aay11");
        loanService.fetchLoanOfAnUser("aay11", "aay11");
    }
}
