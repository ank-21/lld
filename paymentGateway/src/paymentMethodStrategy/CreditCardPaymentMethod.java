package paymentMethodStrategy;

public class CreditCardPaymentMethod implements PaymentMethod{
    @Override
    public boolean initiatePayment(double amount) {
        // Do some adapter pattern to do 3rd party coding
        return true;
    }

    @Override
    public boolean verifyPayment(String transactionId) {
        return true;
    }

    @Override
    public void rollBack() {
        System.out.println("Rolling back payment by credit card");
    }
}
