package model;

public enum TransactionType {
    CASH_WITHDRAWAL,
    DISPLAY_BALANCE;

    public static void showAllTransactionTypes(){
        for(TransactionType type : TransactionType.values()){
            System.out.println(type.name());
        }
    }
}
