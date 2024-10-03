package controllers;

import models.PaymentException;
import models.Transaction;
import models.TransactionStatus;
import paymentMethodStrategy.PaymentMethod;

import java.util.HashMap;
import java.util.UUID;

public class PaymentController {
    private PaymentMethod paymentMethod;
    HashMap<String, Transaction> transactions;

    public PaymentController(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        transactions = new HashMap<>();
    }

    public synchronized boolean makePayment(double amount) throws PaymentException{
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(transactionId, amount);
//        if(transactions.containsKey(transactionId)){
//            throw new PaymentException("Transaction Id already exists");
//        }
//        transactions.put(transactionId, transaction);
        if(transactions.putIfAbsent(transactionId, transaction) != null){
            throw new PaymentException("Transaction Id already exists");
        }
        transaction.setTransactionStatus(TransactionStatus.INITIATED);

        boolean initiatedPayment = paymentMethod.initiatePayment(amount);
        if(!initiatedPayment){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            throw new PaymentException("Transaction has failed to initiate");
        }
        boolean verifiedPayment = paymentMethod.verifyPayment(transactionId);
        if(!verifiedPayment){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            throw new PaymentException("Payment couldn't be verified!");
        }
        else{
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            return true;
        }
    }
}
