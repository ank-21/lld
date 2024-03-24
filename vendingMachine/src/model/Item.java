package model;

public class Item {
    private int price;
    private String name;
    private ItemType itemType;

    public Item(String name, int price, ItemType itemType) {
        this.name = name;
        this.price = price;
        this.itemType = itemType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
