package product;

import java.util.List;

public class Latte extends Coffee{
    public Latte(String name, List<Ingredient> ingredientList, int price) {
        super(name, CoffeeType.LATTE, ingredientList, price);
    }
}
