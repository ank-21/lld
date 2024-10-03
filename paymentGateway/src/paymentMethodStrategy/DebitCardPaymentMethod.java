package paymentMethodStrategy;

public class DebitCardPaymentMethod implements PaymentMethod{
    @Override
    public boolean initiatePayment(double amount) {
        return false;
    }

    @Override
    public boolean verifyPayment(String transactionId) {
        return false;
    }

    @Override
    public void rollBack() {
        System.out.println("Rolling back payment by debit card");
    }
}
