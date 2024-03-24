package state;

import model.Coin;
import model.VendingMachine;

import java.util.List;

public class CoinsCollected implements State{
    VendingMachine vendingMachine;

    public CoinsCollected(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct() throws Exception {
        throw new Exception("The item is already selected, Please either cancel transaction to get refund or proceed to dispense coins");
    }

    @Override
    public void insertCoins() throws Exception {
        throw new Exception("You have already inserted coins!");
    }

    @Override
    public void cancelTransaction() throws Exception {
        vendingMachine.setVendingMachineState(new CoinsDispensed(vendingMachine));
    }

    @Override
    public void dispenseAmount() throws Exception {
        throw new Exception("Please first Collect the selected item");
    }

    @Override
    public void dispenseItem() throws Exception {
        System.out.println("Please collect your " + vendingMachine.getPurchasedQty() + " packets of " + vendingMachine.getPurchasedItemShelf().getItem().getName());
        vendingMachine.setVendingMachineState(new ItemDispensed(vendingMachine));
        vendingMachine.getVendingMachineState().updateInventory();
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
