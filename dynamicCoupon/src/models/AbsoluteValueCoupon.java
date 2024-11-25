package models;

public class AbsoluteValueCoupon extends Coupon {
    private double price;
    public AbsoluteValueCoupon(Coupon nextCoupon, double price) {
        super(nextCoupon);
        this.price = price;
    }

    @Override
    void applyCoupon(Cart cart) {
        for(Product product : cart.getProducts()){
            System.out.println("Applying a discount of Rs " + price + " on " + product.getName());
            product.setPrice(product.getPrice() - price);
        }
        System.out.println("2");
        super.applyCoupon(cart);
    }
}
