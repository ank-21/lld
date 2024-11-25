package models;

public class PercentageOffNextItemCoupon extends Coupon{
    private double percentage;

    public PercentageOffNextItemCoupon(Coupon nextCoupon, double percentage) {
        super(nextCoupon);
        this.percentage = percentage;
    }

    @Override
    void applyCoupon(Cart cart) {
        if(!cart.getProducts().isEmpty()){
            Product product = cart.getProducts().getFirst();
            double discountedPrice = product.getPrice() * (1 - percentage / 100);
            System.out.println("Applying a discount of " + percentage + "% on " + product.getName());
            product.setPrice(discountedPrice);
            System.out.println("3");
            // This is done for sequentially applying the coupons
            super.applyCoupon(cart);
        }
    }
}
