package Inventory;

public class LoggerInventoryObserver implements InventoryObserver{
    @Override
    public void update(String item, int quantityLeft) {
        System.out.println("INVENTORY LOG: [Alert] " + item + " is in low quantity, only " + quantityLeft + " quantities remaining" + "\n");
    }
}
