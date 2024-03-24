package model;

import state.ReadyState;
import state.State;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private Inventory inventory;
    private State vendingMachineState;
    private List<Coin> coins;

    private int amountInserted;

    private int purchasedQty;
    private ItemShelf purchasedItemShelf = null;

    private static VendingMachine INSTANCE;

    private VendingMachine(int inventorySize){
        vendingMachineState = new ReadyState(this);
        coins = new ArrayList<>();
        inventory = new Inventory(inventorySize);
    }

    public static VendingMachine getInstance(int inventorySize){
        if(INSTANCE == null){
            INSTANCE = new VendingMachine(inventorySize);
        }
        return INSTANCE;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public State getVendingMachineState() {
        return vendingMachineState;
    }

    public void setVendingMachineState(State vendingMachineState) {
        this.vendingMachineState = vendingMachineState;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public int getPurchasedQty() {
        return purchasedQty;
    }

    public void setPurchasedQty(int purchasedQty) {
        this.purchasedQty = purchasedQty;
    }

    public ItemShelf getPurchasedItemShelf() {
        return purchasedItemShelf;
    }

    public void setPurchasedItemShelf(ItemShelf purchasedItemShelf) {
        this.purchasedItemShelf = purchasedItemShelf;
    }

    public int getAmountInserted() {
        return amountInserted;
    }

    public void setAmountInserted(int amountInserted) {
        this.amountInserted = amountInserted;
    }
}
