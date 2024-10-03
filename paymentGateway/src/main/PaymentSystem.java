package main;

import factories.PaymentMethodFactory;
import paymentMethodStrategy.PaymentMethod;
import services.PaymentGateway;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PaymentSystem {
    public static void main(String[] args) {
        PaymentMethod paymentMethod = PaymentMethodFactory.getPaymentMethod("creditcard");
        PaymentGateway paymentGateway = new PaymentGateway(paymentMethod);

        //for handling concurrent requests
        int numberOfThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < 10; i++) { // 10 payment tasks
            double amount = 5000 + i * 100; // Different amounts for each payment
            executorService.submit(() -> makeConcurrentPayment(paymentGateway, amount));
        }

        // Shutdown the ExecutorService after all tasks are submitted
        executorService.shutdown();

        try {
            // Wait for all tasks to complete or timeout
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                System.err.println("Some tasks didn't finish in time.");
                executorService.shutdownNow(); // Force shutdown if tasks didn't complete
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow(); // Handle interrupt
            Thread.currentThread().interrupt();
        }
    }

    private static void makeConcurrentPayment(PaymentGateway paymentGateway, double amount) {
        boolean transactionStatus = paymentGateway.makePayment(amount);

        if (transactionStatus) {
            System.out.println("Payment of Rs " + amount + " has been successfully done!");
        } else {
            System.out.println("Payment of Rs " + amount + " failed or has been cancelled.");
        }
    }
}
