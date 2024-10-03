package factories;

import paymentMethodStrategy.CreditCardPaymentMethod;
import paymentMethodStrategy.DebitCardPaymentMethod;
import paymentMethodStrategy.PaymentMethod;
import paymentMethodStrategy.UPIPaymentMethod;

public class PaymentMethodFactory {
    public static PaymentMethod getPaymentMethod(String methodType){
        switch (methodType.trim().toLowerCase()){
            case "debitcard": {
                return new DebitCardPaymentMethod();
            }
            case "creditcard": {
                return new CreditCardPaymentMethod();
            }
            case "upi": {
                return new UPIPaymentMethod();
            }
            default: {
                throw new IllegalArgumentException("Unsupported Payment Methods..." + methodType);
            }
        }
    }
}
