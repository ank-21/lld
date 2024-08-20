package product;

import java.util.List;

public class Cappuccino extends Coffee{
    public Cappuccino(String name, List<Ingredient> ingredientList, int price) {
        super(name, CoffeeType.CAPPUCCINO, ingredientList, price);
    }
}
