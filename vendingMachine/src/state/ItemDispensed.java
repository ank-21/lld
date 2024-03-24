package state;

import model.Coin;
import model.VendingMachine;

import java.util.List;

public class ItemDispensed implements State{
    VendingMachine vendingMachine;

    public ItemDispensed(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct() throws Exception {
        throw new Exception("The item is already selected, Please select collect refund to get your change amount");
    }

    @Override
    public void insertCoins() throws Exception {
        throw new Exception("The item is already dispensed, Please select collect refund to get your change amount");
    }

    @Override
    public void cancelTransaction() throws Exception {
        throw new Exception("The item is already dispensed, Please select collect refund to get your change amount");
    }

    @Override
    public void dispenseAmount() throws Exception {
        throw new Exception("The item is already dispensed, Please select collect refund to get your change amount");
    }

    @Override
    public void dispenseItem() throws Exception {
        throw new Exception("The item is already dispensed, Please select collect refund to get your change amount");
    }

    @Override
    public void calculateChangeAmount() throws Exception {
        int remainingAmount = vendingMachine.getAmountInserted() - vendingMachine.getPurchasedQty() * vendingMachine.getPurchasedItemShelf().getItem().getPrice();
        if(remainingAmount > 0){
            System.out.println("You have the change amount of " + remainingAmount);
            System.out.println("Please press the number correspond to collect change to take out your remaining coins");
        }else{
            vendingMachine.getVendingMachineState().dispenseAmount();
        }
    }

    @Override
    public void updateInventory() throws Exception {

        //remove the product from the shelf
        int currQty = vendingMachine.getPurchasedItemShelf().getQuantity();
        int purchasedQty = vendingMachine.getPurchasedQty();
        vendingMachine.getPurchasedItemShelf().setQuantity(currQty - purchasedQty);
        vendingMachine.setVendingMachineState(new CoinsDispensed(vendingMachine));

        //calculate change amount
        calculateChangeAmount();
    }
}
