package product;

import java.util.List;

public class Espresso extends Coffee{
    public Espresso(String name, List<Ingredient> ingredientList, int price) {
        super(name, CoffeeType.ESPRESSO, ingredientList, price);
    }
}
