package state;

import model.Coin;
import model.VendingMachine;

import java.util.List;

public interface State {
    void selectProduct() throws Exception;
    void insertCoins() throws  Exception;
    void cancelTransaction() throws Exception;
    void dispenseAmount() throws Exception;
    void dispenseItem() throws Exception;
    void calculateChangeAmount() throws Exception;
    void updateInventory() throws Exception;
}
