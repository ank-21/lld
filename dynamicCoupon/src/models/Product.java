package models;

public class Product {
    private String name;
    private double price;
    private String code;
    private ProductType productType;

    public Product(String name, double price, String code, ProductType productType) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                " name = '" + name + "'" +
                ", price = Rs " + price +
                ", type = '" + productType + '\'' +
                " }";
    }
}
