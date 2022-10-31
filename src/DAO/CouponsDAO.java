package DAO;

import java.util.ArrayList;

import Core.Coupon;

public interface CouponsDAO {
    public void addCoupon(Coupon coupon);

    public void couponAmount(int couponId, int amount);

    public void updateCoupon(Coupon coupon);

    public void deleteCoupon(int couponId);

    public ArrayList<Coupon> getAllCoupon();

    public Coupon getOneCoupon(int couponId);

    public ArrayList<Integer> getCustomerCoupons(int customerId);

    public void addCouponPurchase(int customerId, int couponId);

    public void deleteCouponPurchase(int customerId, int couponId);
}
