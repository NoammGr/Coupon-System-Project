package Facades;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import Core.Category;
import Core.Coupon;
import Core.CouponSystemException;
import Core.Customer;
import DAO.CouponsDAO;
import DAO.CouponsDBDAO;

/**
 * This class contains all the logical work .
 */
public class CustomersFacade extends ClientFacade {

    private Customer customer;

    private int customerId;

    public CustomersFacade() {

    }

    public CustomersFacade(Customer customer) {
        super();
        this.customer = customer;
    }

    /**
     * This method allow customer to login to system !
     */
    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        Collection<Customer> customers = new ArrayList<Customer>();
        customers = customersDAO.getAllCustomers();
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                this.customerId = customer.getId();
                this.customer = customer;
                System.out.println("Welcome " + email + " !");
                return true;
            }
        }
        throw new CouponSystemException("Wrong email or password please try again !");
    }

    /**
     * This method allow customer to purchase coupon , only if the coupon date is
     * valid ,
     * and the amount of the coupon is valid .
     */
    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
        CouponsDAO couponsDAO = new CouponsDBDAO();
        Coupon tempCoupon = couponsDAO.getOneCoupon(coupon.getId());
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        try {
            if (tempCoupon == null) {
                throw new CouponSystemException("Coupon  dose not exist !");
            }
            if (tempCoupon.getEndDate() == date) {
                throw new CouponSystemException("Coupon passed deadline");
            }
            if (tempCoupon.getAmount() == 0) {
                throw new CouponSystemException("Coupon out of stock ! please try again later");
            }
            couponsDAO.addCouponPurchase(this.customerId, coupon.getId());
            tempCoupon.setAmount(tempCoupon.getAmount() - 1);
            couponsDAO.updateCoupon(tempCoupon);
        } catch (Exception e) {
            throw new CouponSystemException("Error in getting purchase coupon method ! " + e);
        }
    }

    /**
     * This method returns all logged in customer coupons .
     */
    public Collection<Coupon> getCustomerCoupon() {
        CouponsDAO couponsDAO = new CouponsDBDAO();
        Collection<Coupon> coupons = new ArrayList<Coupon>();
        try {
            coupons.addAll(couponsDAO.getCustomerCoupon(this.customerId));
        } catch (Exception e) {
            throw new CouponSystemException("Error in getting customer coupons method ! " + e);
        }
        return coupons;
    }

    /**
     * This method returns all customer coupons by category !
     */
    public Collection<Coupon> getCustomerCoupon(Category category) {
        CouponsDAO couponsDAO = new CouponsDBDAO();
        Collection<Coupon> coupons = new ArrayList<Coupon>();
        Collection<Coupon> tempCoupons = new ArrayList<Coupon>();
        try {
            tempCoupons.addAll(couponsDAO.getCustomerCoupon(customer));
            for (Coupon coupon : tempCoupons) {
                if (coupon.getCategory() == category) {
                    coupons.add(coupon);
                }
            }
        } catch (Exception e) {
            throw new CouponSystemException("Error in getting customer coupons method ! " + e);
        }
        return coupons;
    }

    /**
     * This method returns all customer coupons by max price !
     */
    public Collection<Coupon> getCustomerCoupon(double maxPrice) {
        CouponsDAO couponsDAO = new CouponsDBDAO();
        Collection<Coupon> coupons = new ArrayList<Coupon>();
        Collection<Coupon> tempCoupons = new ArrayList<Coupon>();
        try {
            tempCoupons.addAll(couponsDAO.getCustomerCoupon(customer));
            for (Coupon coupon : tempCoupons) {
                if (coupon.getPrice() <= maxPrice) {
                    coupons.add(coupon);
                }
            }
        } catch (Exception e) {
            throw new CouponSystemException("Error in getting customer coupons method ! " + e);
        }
        return coupons;
    }

    /**
     * This method returns all customer coupons by end date !
     */
    public Collection<Coupon> getCustomerCoupon(Date endDate) {
        CouponsDAO couponsDAO = new CouponsDBDAO();
        Collection<Coupon> coupons = new ArrayList<Coupon>();
        Collection<Coupon> tempCoupons = new ArrayList<Coupon>();
        try {
            tempCoupons.addAll(couponsDAO.getCustomerCoupon(customer));
            for (Coupon coupon : tempCoupons) {
                if (coupon.getEndDate().compareTo(endDate) < 0) {
                    coupons.add(coupon);
                }
            }
        } catch (Exception e) {
            throw new CouponSystemException("Error in getting customer coupons method ! " + e);
        }
        return coupons;
    }

    /**
     * This method will return customer object including his coupons !
     */
    public Customer getCustomerDetailes() {
        CouponsDAO couponsDAO = new CouponsDBDAO();
        ArrayList<Coupon> coupons = new ArrayList<Coupon>();
        try {
            coupons.addAll(couponsDAO.getCustomerCoupon(this.customerId));
            this.customer.setCoupons(coupons);
        } catch (Exception e) {
            throw new CouponSystemException("Error in getting customer detailes method ! " + e);
        }
        return this.customer;
    }
}
