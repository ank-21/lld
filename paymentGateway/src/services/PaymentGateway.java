package services;

import controllers.PaymentController;
import paymentMethodStrategy.PaymentMethod;

/*
models/: Contains your User class, and possibly other domain classes.
controllers/: Contains your UserController class which will handle incoming requests.
services/: Could contain a UserService class that implements the business logic for user-related operations.
 */
public class PaymentGateway {
    private static final int maxRetries = 3;
    private PaymentController paymentController;

    public PaymentGateway(PaymentMethod paymentMethod){
        this.paymentController = new PaymentController(paymentMethod);
    }

    public boolean makePayment(double amount){
        int attempts = 0;

        while (attempts < maxRetries){
            try {
                return paymentController.makePayment(amount);
            } catch (Exception e){
                System.err.println("Payment failed. Attempt " + (attempts + 1) + " of " + maxRetries);
                System.err.println("Error " + e.getMessage());
                attempts++;
            }
        }
        System.err.println("The transaction has failed after " + maxRetries + " maximum retries");
        return false;
    }
}
