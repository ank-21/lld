package Inventory;

import java.util.ArrayList;
import java.util.List;

// Observable class - not used an interface & separate observables as not much difference in the childs
public class Inventory {
    private String item;
    private ItemName itemName;
    private int totalQuantity;
    private int quantityLeft;
    private int pricePerItem;
    private int lowThreshold;

    private List<InventoryObserver> observerList;

    public Inventory(String item, ItemName itemName, int totalQuantity, int pricePerItem, int lowThreshold) {
        this.item = item;
        this.itemName = itemName;
        this.totalQuantity = totalQuantity;
        this.quantityLeft = totalQuantity;
        this.pricePerItem = pricePerItem;
        this.lowThreshold = lowThreshold;
        this.observerList = new ArrayList<>();
    }

    void addObserver(InventoryObserver observer){
        observerList.add(observer);
    }

    void removeObserver(InventoryObserver observer){
        observerList.remove(observer);
    }

    void notifyObservers(){
        for(InventoryObserver observer : observerList){
            observer.update(item, quantityLeft);
        }
    }

    void updateQuantity(int quantityUsed){
        quantityLeft = quantityLeft - quantityUsed;
        if(quantityLeft <= lowThreshold){
            notifyObservers();
        }
    }

    public String getItem() {
        return item;
    }

    public ItemName getItemName() {
        return itemName;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getQuantityLeft() {
        return quantityLeft;
    }

    public int getPricePerItem() {
        return pricePerItem;
    }

    public int getLowThreshold() {
        return lowThreshold;
    }

    public List<InventoryObserver> getObserverList() {
        return observerList;
    }
}
