package state;

public interface State {
    void selectProduct() throws Exception;
    void insertCoins() throws  Exception;
    void cancelTransaction() throws Exception;
    void dispenseAmount() throws Exception;
    void dispenseItem() throws Exception;
    void calculateChangeAmount() throws Exception;
    void updateInventory() throws Exception;
}
