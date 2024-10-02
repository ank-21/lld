package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private ItemShelf[] products = null;
    private Map<Integer, ItemShelf> codeMapToShelf = null;
    public Inventory(int inventorySize){
        products = new ItemShelf[inventorySize];
        codeMapToShelf = new HashMap<Integer, ItemShelf>();
        createInitialEmptyInventory();
    }

    public ItemShelf[] getProducts() {
        return products;
    }

    private void createInitialEmptyInventory(){
        int startCode = 101;
        List<Item> itemList = new ArrayList<>();

        // Static code for interview
        itemList.add(new Item("Munch", 20, ItemType.CHOCOLATE));
        itemList.add(new Item("Oreo", 35, ItemType.COOKIES));
        itemList.add(new Item("Lays", 10, ItemType.MUNCHIES));

        for(int productItr = 0; productItr < products.length; productItr++){
            ItemShelf shelf = new ItemShelf();
            //every shelf at the start has 20 items to keep at max
            shelf.setCapacity(20);
            shelf.setCode(startCode);
            shelf.setQuantity(10);
            shelf.setItem(itemList.get(productItr));
            codeMapToShelf.put(startCode, shelf);
            startCode++;
            products[productItr] = shelf; // can be a list
        }
    }

    public ItemShelf getItemShelfByCode(int code){
        return codeMapToShelf.get(code);
    }
}
