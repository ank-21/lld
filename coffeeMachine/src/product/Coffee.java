package product;

import java.util.List;

public class Coffee {
    String name;
    CoffeeType coffeeType;
    List<Ingredient> ingredientList;
    int price;

    public Coffee(String name, CoffeeType coffeeType, List<Ingredient> ingredientList, int price) {
        this.name = name;
        this.coffeeType = coffeeType;
        this.ingredientList = ingredientList;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public int getPrice() {
        return price;
    }
}
