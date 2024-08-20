package Inventory;

public interface InventoryObserver {
    public void update(String item, int quantityLeft);
}
