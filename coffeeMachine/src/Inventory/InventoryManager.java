package Inventory;

import java.util.HashMap;

public class InventoryManager {
    private Inventory inventory;
    private InventoryObserver loggerInventoryObserver;
    private InventoryObserver userNotificationInventoryObserver;
    private HashMap<ItemName, Inventory> inventoryMap;

    private static volatile InventoryManager instance = null;

    private InventoryManager(){
        // Create observers
        loggerInventoryObserver = new LoggerInventoryObserver();
        userNotificationInventoryObserver = new UserNotificationInventoryObserver("9123421208");

        // Initialise inventory Map
        inventoryMap = new HashMap<>();
    }

    public static InventoryManager getInstance(){
        if(instance == null){
            synchronized (InventoryManager.class){
                if(instance == null){
                    instance = new InventoryManager();
                }
            }
        }
        return instance;
    }

    public void addInventory(String item, ItemName itemName, int totalQuantity, int pricePerItem, int lowThreshold){
        Inventory inventory = new Inventory(item, itemName, totalQuantity, pricePerItem, lowThreshold);
        inventory.addObserver(loggerInventoryObserver);
        inventory.addObserver(userNotificationInventoryObserver);
        inventoryMap.put(itemName, inventory);
    }

    public synchronized void updateQuantity(ItemName itemName, int quantityUsed){
        Inventory inventory = inventoryMap.get(itemName);
        inventory.updateQuantity(quantityUsed);
    }

    public HashMap<ItemName, Inventory> getInventoryMap() {
        return inventoryMap;
    }
}
