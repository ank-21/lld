
import product.Coffee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        // Setup Admin
        CoffeeMachineAdmin coffeeMachineAdmin = CoffeeMachineAdmin.getInstance();
        coffeeMachineAdmin.addItemsInInventory();

        List<Coffee> coffeeMenu = new ArrayList<>();
        coffeeMachineAdmin.setupMenu(coffeeMenu);
        coffeeMachineAdmin.displayMenu(coffeeMenu);

        // Asking user to purchase the coffee
        serveUser(coffeeMachineAdmin, coffeeMenu);
    }
    private static void serveUser(CoffeeMachineAdmin coffeeMachineAdmin, List<Coffee> coffeeMenu){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Do you want to buy a coffee : Y / N ");
//        String choice = sc.nextLine();

        // Null check in future
        Coffee coffee1 = coffeeMachineAdmin.selectCoffee("Espresso", coffeeMenu);
        coffeeMachineAdmin.dispenseCoffee(coffee1);
        Coffee coffee2 = coffeeMachineAdmin.selectCoffee("Latte", coffeeMenu);
        coffeeMachineAdmin.dispenseCoffee(coffee2);
        Coffee coffee3 = coffeeMachineAdmin.selectCoffee("Latte", coffeeMenu);
        coffeeMachineAdmin.dispenseCoffee(coffee3);
        Coffee coffee4 = coffeeMachineAdmin.selectCoffee("Cappuccino", coffeeMenu);
        coffeeMachineAdmin.dispenseCoffee(coffee4);
        Coffee coffee5 = coffeeMachineAdmin.selectCoffee("Cappuccino", coffeeMenu);
        coffeeMachineAdmin.dispenseCoffee(coffee5);
        Coffee coffee6 = coffeeMachineAdmin.selectCoffee("Espresso", coffeeMenu);
        coffeeMachineAdmin.dispenseCoffee(coffee6);
        Coffee coffee7 = coffeeMachineAdmin.selectCoffee("Cappuccino", coffeeMenu);
        coffeeMachineAdmin.dispenseCoffee(coffee7);
    }
}
