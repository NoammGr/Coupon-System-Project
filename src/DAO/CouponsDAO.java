package DAO;

import java.util.ArrayList;

import Core.Coupon;

public interface CouponsDAO {
    public void addCoupon(Coupon coupon);

    public void updateCoupon(Coupon coupon);

    public void deleteCoupon(Coupon coupon);

    public ArrayList<Coupon> getAllCoupon();

    public Coupon getOneCoupon(Coupon coupon);

    public void addCouponPurchase(int customerId, int couponId);

    public void deleteCouponPurchase(int customerId, int couponId);
}
