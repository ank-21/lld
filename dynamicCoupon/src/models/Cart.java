package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public double getTotalPrice(){
        double price = 0;
        for(Product product : products){
            price += product.getPrice();
        }
        return price;
    }
}
