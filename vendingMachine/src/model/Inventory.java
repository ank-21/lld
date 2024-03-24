package model;

import java.util.HashMap;
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

        for(int productItr = 0; productItr < products.length; productItr++){
            ItemShelf shelf = new ItemShelf();
            //every shelf at the start has 20 items to keep at max
            shelf.setCapacity(20);
            shelf.setCode(startCode);
            shelf.setQuantity(0);
            codeMapToShelf.put(startCode,shelf);
            startCode++;
            products[productItr] = shelf;
        }
    }

    public ItemShelf getItemShelfByCode(int code){
        return codeMapToShelf.get(code);
    }
}
