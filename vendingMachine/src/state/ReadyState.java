package state;

import model.ItemShelf;
import model.VendingMachine;

import java.util.Scanner;

public class ReadyState implements State{
    private VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        this.vendingMachine.setPurchasedQty(0);
        this.vendingMachine.setAmountInserted(0);
        this.vendingMachine.setPurchasedItemShelf(null);
    }

    @Override
    public void selectProduct() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the code of the product you want to buy : ");
        int code = sc.nextInt();
        ItemShelf shelf = vendingMachine.getInventory().getItemShelfByCode(code);

        System.out.print("Please enter the quantity of "  + shelf.getItem().getName() + " you want to buy : ");
        int quantity = sc.nextInt();
        sc.nextLine();

        //get Item from the code number
        if(shelf.getQuantity() < quantity)  throw new Exception("Currently we have " + shelf.getQuantity() + " packets of " + shelf.getItem().getName());

        vendingMachine.setPurchasedQty(quantity);
        vendingMachine.setPurchasedItemShelf(shelf);
        //shelf.setQuantity(shelf.getQuantity() - quantity);
        //should be called in update inventory at the last of the transaction

        //changing state to product selected state
        vendingMachine.setVendingMachineState(new ProductSelected(vendingMachine));
    }

    @Override
    public void insertCoins() throws Exception {
        throw new Exception("Please select an item first");
    }

    @Override
    public void cancelTransaction() throws Exception {
    }

    @Override
    public void dispenseAmount() throws Exception {
        throw new Exception("Please select an item first");
    }

    @Override
    public void dispenseItem() throws Exception {
        throw new Exception("Please select an item first");
    }

    @Override
    public void calculateChangeAmount() throws Exception {
    }

    @Override
    public void updateInventory() throws Exception {
    }
}
