package models;

public class NthPercentageCoupon extends Coupon{
    private double percentage;
    public NthPercentageCoupon(Coupon nextCoupon, double discountRate) {
        super(nextCoupon);
        this.percentage = discountRate;
    }

    @Override
    public void applyCoupon(Cart cart) {
        for(Product product : cart.getProducts()){
            System.out.println("Applying a discount of " + percentage + "% on " + product.getName());
            double discountedPrice = product.getPrice() * (1 - percentage / 100);
            product.setPrice(discountedPrice);
        }
        System.out.println("1");
        super.applyCoupon(cart); // Pass to the next coupon in the chain
    }
}
