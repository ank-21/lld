package main.java.com.walletApp.services;

public class TransactionProcessorFactory {
    public static TransactionProcessor getTransactionProcessor(TransactionType transactionType){
        return switch (transactionType) {
            case WALLET_TO_WALLET -> new WalletToWalletTransactionProcessor();
            case WALLET_TO_BANK -> new WalletToBankTransactionProcessor();
        };
    }
}
