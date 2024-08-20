package product;

import Inventory.ItemName;

public class Ingredient {
    private ItemName itemName;
    private int quantity;

    public Ingredient(ItemName itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public ItemName getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }
}
