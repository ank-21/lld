package models;

public class NthItemTypeDiscountCoupon extends Coupon{
    private double percentageDiscount;
    private ProductType productType;
    private int nthCount;

    public NthItemTypeDiscountCoupon(Coupon nextCoupon, double percentageDiscount, ProductType productType, int nthCount) {
        super(nextCoupon);
        this.percentageDiscount = percentageDiscount;
        this.productType = productType;
        this.nthCount = nthCount;
    }

    @Override
    void applyCoupon(Cart cart) {
        int count = 0;
        for(Product product : cart.getProducts()){
            if(product.getProductType() == productType){
                count++;
                if(count == nthCount){
                    System.out.println("Applying discount of " + percentageDiscount + "% on " + product.getName() + " as " + nthCount + "th prodcuct added in the cart of the type " + productType);
                    double discountedPrice = product.getPrice() * (1 - percentageDiscount / 100);
                    product.setPrice(discountedPrice);
                    break;
                }
            }
        }
        super.applyCoupon(cart);
    }
}
