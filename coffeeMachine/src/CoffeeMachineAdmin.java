import Inventory.Inventory;
import Inventory.InventoryManager;
import Inventory.ItemName;
import product.Coffee;
import product.CoffeeFactory;
import product.CoffeeType;
import product.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachineAdmin {
    public static volatile CoffeeMachineAdmin instance = null;
    private InventoryManager inventoryManager;

    private CoffeeMachineAdmin(){
        inventoryManager = InventoryManager.getInstance();
    }

    public static CoffeeMachineAdmin getInstance(){
        if(instance == null){
            synchronized (CoffeeMachineAdmin.class){
                if(instance == null){
                    instance = new CoffeeMachineAdmin();
                }
            }
        }
        return instance;
    }

    public void addItemsInInventory(){
        inventoryManager.addInventory("Milk", ItemName.MILK, 6, 30, 2);
        inventoryManager.addInventory("Coffee Powder Sachet", ItemName.COFFEE_POWDER_SACHET, 6, 5, 3);
        inventoryManager.addInventory("Sugar sachet", ItemName.SUGAR_SACHET, 10, 2, 4);
    }

    public void setupMenu(List<Coffee> coffeeMenu){
        addItemInMenu(CoffeeType.CAPPUCCINO, 2, 1, 2, coffeeMenu);
        addItemInMenu(CoffeeType.LATTE, 1, 1, 2, coffeeMenu);
        addItemInMenu(CoffeeType.ESPRESSO, 0, 2, 3, coffeeMenu);
    }

    private void addItemInMenu(CoffeeType coffeeType, int milkQuantity, int coffeePowderQuantity, int sugarQuantity, List<Coffee> coffeeMenu){
        Inventory milk = inventoryManager.getInventoryMap().get(ItemName.MILK);
        Inventory coffee_powder = inventoryManager.getInventoryMap().get(ItemName.COFFEE_POWDER_SACHET);
        Inventory sugar = inventoryManager.getInventoryMap().get(ItemName.SUGAR_SACHET);

        Ingredient milkIngredient = new Ingredient(milk.getItemName(), milkQuantity);
        Ingredient coffeePowderIngredient = new Ingredient(coffee_powder.getItemName(), coffeePowderQuantity);
        Ingredient sugarIngredient = new Ingredient(sugar.getItemName(), sugarQuantity);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(milkIngredient);
        ingredients.add(coffeePowderIngredient);
        ingredients.add(sugarIngredient);

        int price = milk.getPricePerItem() * milkIngredient.getQuantity() + coffee_powder.getPricePerItem() * coffeePowderIngredient.getQuantity() + sugar.getPricePerItem() * sugarIngredient.getQuantity();
        Coffee coffee = createCoffeeByFactoryPattern(coffeeType, ingredients, price);
        coffeeMenu.add(coffee);
    }

    private Coffee createCoffeeByFactoryPattern(CoffeeType coffeeType, List<Ingredient> ingredients, int price){
        return CoffeeFactory.getCoffee(coffeeType, ingredients, price);
    }

    public void displayMenu(List<Coffee> coffeeMenu){
        System.out.println("Coffee Menu");

        for(Coffee coffee : coffeeMenu){
            System.out.println("Name: " + coffee.getName() + "Price: " + coffee.getPrice());
        }
        System.out.println("\n");
    }

    public synchronized Coffee selectCoffee(String coffeeName, List<Coffee> coffeeMenu){
        for (Coffee coffee : coffeeMenu){
            if(coffee.getName().equalsIgnoreCase(coffeeName)){
                return coffee;
            }
        }
        return null;
    }

    // synchronized for handling concurrent request
    public synchronized void dispenseCoffee(Coffee coffee){
        List<Ingredient> ingredients = coffee.getIngredientList();
        if(hasEnoughIngredients(ingredients)){

            for(Ingredient ingredient : ingredients){
                inventoryManager.updateQuantity(ingredient.getItemName(), ingredient.getQuantity());
            }
            // Handle payment in future
            System.out.println("Hey User your " + coffee.getName() + " has been dispensed");
        }else{
            System.out.println("Sorry, we are out of stock for today!");
        }
    }

    private boolean hasEnoughIngredients(List<Ingredient> ingredients){
        for(Ingredient ingredient : ingredients){
            if(ingredient.getQuantity() > inventoryManager.getInventoryMap().get(ingredient.getItemName()).getQuantityLeft()){
                return false;
            }
        }
        return true;
    }
}
