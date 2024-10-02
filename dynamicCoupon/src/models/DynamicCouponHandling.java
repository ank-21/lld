package models;

public class DynamicCouponHandling {
    public static void main(String[] args) {
        Product product1 = new Product("Watermelon", 65.0, "101", ProductType.FRUITS);
        Product product2 = new Product("Milk", 50.0, "306", ProductType.DAIRY);
        Product product3 = new Product("Oreo", 30.0, "352", ProductType.BISCUITS);
        Product product4 = new Product("Hide N Seek", 35.0, "356", ProductType.BISCUITS);
        Product product5 = new Product("Milk Bikis", 25.0, "359", ProductType.BISCUITS);
        Product product6 = new Product("Good day Chocochips", 30.0, "355", ProductType.BISCUITS);

        Cart cart = new Cart();
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);
        cart.addProduct(product4);
        cart.addProduct(product5);
        cart.addProduct(product6);


        System.out.println("Initial Price of the cart before applying coupon");
        for (Product product : cart.getProducts()) {
            System.out.println(product);
        }

        // First the starting coupon will get applied
        Coupon couponChain = new NthItemTypeDiscountCoupon(new PercentageOffNextItemCoupon(new AbsoluteValueCoupon(new NthPercentageCoupon(null, 10), 5), 10), 10, ProductType.BISCUITS, 2);
        couponChain.applyCoupon(cart);

        System.out.println("Products after applying coupons:");
        for (Product product : cart.getProducts()) {
            System.out.println(product);
        }

        // Display total price after applying coupons
        System.out.println("Total Price after applying coupons: " + cart.getTotalPrice());
    }
}
