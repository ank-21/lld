package paymentMethodStrategy;

public interface PaymentMethod {
    boolean initiatePayment(double amount);
    boolean verifyPayment(String transactionId);
    void rollBack();
}
