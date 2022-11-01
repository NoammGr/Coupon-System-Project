package DAO;

import java.util.ArrayList;

import Core.Coupon;
import Core.Customer;

public interface CouponsDAO {
    public void addCoupon(Coupon coupon);

    public void couponAmount(int couponId, int amount);

    public void updateCoupon(Coupon coupon);

    public void deleteCoupon(int couponId);

    public ArrayList<Coupon> getAllCoupon();

    public Coupon getOneCoupon(int couponId);

    public void addCouponPurchase(int customerId, int couponId);

    public void deleteCouponPurchase(int customerId, int couponId);

    public ArrayList<Coupon> getCustomerCoupon(Customer customer);

    public ArrayList<Coupon> getCustomerCoupon(int customerId);
}
