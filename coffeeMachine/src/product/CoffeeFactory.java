package product;

import java.util.List;

public class CoffeeFactory {
    public static Coffee getCoffee(CoffeeType coffeeType, List<Ingredient> ingredients, int price){
        switch (coffeeType){
            case CAPPUCCINO:
                return new Cappuccino("Cappuccino", ingredients, price);
            case LATTE:
                return new Latte("latte", ingredients, price);
            case ESPRESSO:
                return new Espresso("Espresso", ingredients, price);
        }
        return null;
    }
}
