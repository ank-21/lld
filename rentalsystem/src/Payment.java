public class Payment {
    Bill bill;
    PaymentMode paymentMode;

    public Payment(Bill bill, PaymentMode paymentMode) {
        this.bill = bill;
        this.paymentMode = paymentMode;
    }
}
