package state;

import model.Coin;
import model.VendingMachine;

import java.util.List;

public class CoinsDispensed implements State{
    VendingMachine vendingMachine;

    public CoinsDispensed(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct() throws Exception {
        throw new Exception("The item is already selected, Please select collect change to get refund");
    }

    @Override
    public void insertCoins() throws Exception {
        throw new Exception("Please select collect change to get refund");
    }

    @Override
    public void cancelTransaction() throws Exception {
        throw new Exception("Please select collect change to get refund");
    }

    @Override
    public void dispenseAmount() throws Exception {
        int remainingAmount = vendingMachine.getAmountInserted() - vendingMachine.getPurchasedQty() * vendingMachine.getPurchasedItemShelf().getItem().getPrice();
        if(remainingAmount > 0)
            System.out.println("Please collect the remaining amount of " + remainingAmount);
        else
            System.out.println("There is no change remaining.");
        vendingMachine.setVendingMachineState(new ReadyState(vendingMachine));
    }

    @Override
    public void dispenseItem() throws Exception {
        throw new Exception("Please select collect change to get refund");
    }

    @Override
    public void calculateChangeAmount() throws Exception {
        return;
    }

    @Override
    public void updateInventory() throws Exception {
        return;
    }
}
