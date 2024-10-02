package models;

public class Coupon {
    private Coupon nextCoupon;

    public Coupon(Coupon nextCoupon) {
        this.nextCoupon = nextCoupon;
    }

    void applyCoupon(Cart cart){
        if(nextCoupon != null){
            nextCoupon.applyCoupon(cart);
        }
    }
}
