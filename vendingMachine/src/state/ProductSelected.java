package state;

import model.Coin;
import model.CoinType;
import model.VendingMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductSelected implements State{
    VendingMachine vendingMachine;

    public ProductSelected(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct() throws Exception {
        throw new Exception("The item is already selected, Please either cancel transaction or proceed to insert coins");
    }

    @Override
    public void insertCoins() throws Exception {

        int cost = vendingMachine.getPurchasedQty() * vendingMachine.getPurchasedItemShelf().getItem().getPrice();
        List<Coin> coins = acceptCoin(vendingMachine, cost);
        vendingMachine.setCoins(coins);

        //changing the state to coins collected state
        vendingMachine.setVendingMachineState(new CoinsCollected(vendingMachine));
    }

    @Override
    public void cancelTransaction() throws Exception {
        System.out.println("Thanks for taking your time out!");
        vendingMachine.setVendingMachineState(new ReadyState(vendingMachine));
    }

    @Override
    public void dispenseAmount() throws Exception {
        throw new Exception("Please insert coin first!");
    }

    @Override
    public void dispenseItem() throws Exception {
        throw new Exception("Please insert coin first!");
    }

    @Override
    public void calculateChangeAmount() throws Exception {
        return;
    }

    @Override
    public void updateInventory() throws Exception {
        return;
    }

    private static List<Coin> acceptCoin(VendingMachine vendingMachine, int cost){
        int amountInserted = 0;
        String coinValue;
        List<Coin> coins = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (amountInserted < cost){
            System.out.println("Please enter the coins in the following type for " + vendingMachine.getPurchasedItemShelf().getItem().getName() + " which would cost " + cost);
            for(CoinType coin : CoinType.values()){
                System.out.print(coin + " ");
            }
            System.out.println("\n");

            coinValue = sc.nextLine().trim();
            Coin newCoin = new Coin(CoinType.valueOf(coinValue.toUpperCase()));
            coins.add(newCoin);

            if("FIVE".equalsIgnoreCase(coinValue)){
                amountInserted += 5;
            }else if("TEN".equalsIgnoreCase(coinValue)){
                amountInserted += 10;
            }else if("TWENTY".equalsIgnoreCase(coinValue)){
                amountInserted += 20;
            }else if("FIFTY".equalsIgnoreCase(coinValue)){
                amountInserted += 50;
            }
        }
        vendingMachine.setAmountInserted(amountInserted);
        return coins;
    }
}
