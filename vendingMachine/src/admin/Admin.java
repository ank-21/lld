package admin;

import model.Item;
import model.ItemShelf;
import model.ItemType;
import model.VendingMachine;
import state.State;
import user.User;

import java.util.Scanner;

public class Admin {
    public static void main(String[] args) {
        try {
            VendingMachine vendingMachine = VendingMachine.getInstance(3);
            System.out.println("Hey Admin, Let's start filling up items in the inventory");
            // fillUpInventory(vendingMachine);

            Scanner sc = new Scanner(System.in);

            System.out.println("Hey User, Do you want to buy anything : Y/N ");
            String userPurchasing = sc.nextLine();
            while ("Y".equalsIgnoreCase(userPurchasing)) {

                System.out.println("The current inventory looks like this\n");
                displayInventory(vendingMachine);
                boolean transactionOver = false;

                User user = User.getInstance(); // Not needed
                while (!transactionOver) {
                    try {
                        int choice = user.selectOption();
                        transactionOver = actionOnChoice(choice, vendingMachine);
                    } catch (Exception e) {
                        System.out.println("Warning : " + e.getMessage() + "\n");
                    }
                }
                System.out.println("Hey User, Do you want to buy anything : Y/N");
                userPurchasing = sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Received an error" + e.getMessage());
        }
    }

    private static void fillUpInventory(VendingMachine vendingMachine) {
        ItemShelf[] products = vendingMachine.getInventory().getProducts();

        System.out.println("Currently, we have these products to be added in the store");

//        for (ItemType type : ItemType.values()) {
//            System.out.print(type.toString().charAt(0) + type.toString().substring(1).toLowerCase() + " ");
//        }
//        System.out.println("\n");

//        Scanner sc = new Scanner(System.in);
//        String name, type;
//        int price, qty;
//
//        for (ItemShelf product : products) {
//
//            System.out.print("Please enter the item type : ");
//            type = sc.nextLine().trim().toUpperCase();
//            ItemType itemType = ItemType.valueOf(type);
//
//            System.out.print("Please enter the item name : ");
//            name = sc.nextLine();
//
//            System.out.print("Please enter the item price : ");
//            price = sc.nextInt();
//            sc.nextLine();
//
//            System.out.print("Please enter the item quantity : ");
//            qty = sc.nextInt();
//            sc.nextLine();
//            //What should we do here ?
//            while (qty > product.getCapacity()) {
//                System.out.println("Please don't add more quantity than the capacity of " + product.getCapacity());
//                System.out.print("Please enter the item quantity : ");
//                qty = sc.nextInt();
//                sc.nextLine();
//            }
//
//            Item newItem = new Item(name, price, itemType);
//
//            product.setItem(newItem);
//            product.setQuantity(qty);
//
//            System.out.println("Thanks for adding an item in stock\n");
//        }
    }

    private static void displayInventory(VendingMachine vendingMachine) {
        ItemShelf[] products = vendingMachine.getInventory().getProducts();

        for (ItemShelf product : products) {
            System.out.println("Code : " + product.getCode() + " | " +
                    "  Name : " + product.getItem().getName() + " | " +
                    "  Price : " + product.getItem().getPrice() + " | " +
                    "  Stock Left : " + product.getQuantity() + " | " +
                    "  Type : " + product.getItem().getItemType()
            );
            System.out.println("--------------------------------------------------------------------------------------");
        }
    }

    private static boolean actionOnChoice(int choice, VendingMachine vendingMachine) throws Exception {
        State vendingState = vendingMachine.getVendingMachineState();

        if (choice == 1) {
            //user selects a product
            vendingState.selectProduct();
        } else if (choice == 2) {
            //user inserts a coin
            vendingState.insertCoins();
        } else if (choice == 3) {
            //user collects the item
            vendingState.dispenseItem();
            return vendingMachine.getPurchasedQty() == 0;
        } else if (choice == 4) {
            //user collects change if needed
            vendingState.dispenseAmount();
            return true;
        } else if (choice == 5) {
            vendingState.cancelTransaction();
            return true;
        }
        return false;
    }
}
