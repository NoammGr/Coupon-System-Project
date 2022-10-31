package Facades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import Core.Coupon;
import Core.CouponSystemException;
import Core.Customer;
import DAO.CouponsDAO;
import DAO.CouponsDBDAO;
import DAO.CustomersDAO;
import DAO.CustomersDBDAO;

public class CustomersFacade extends ClientFacade {

    private Customer customer;

    private int customerId;

    public CustomersFacade() {

    }

    public CustomersFacade(Customer customer) {
        super();
        this.customer = customer;
    }

    @Override
    public boolean Login(String email, String password) throws CouponSystemException {
        Collection<Customer> customers = new ArrayList<Customer>();
        customers = customersDAO.getAllCustomers();
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                this.customerId = customer.getId();
                System.out.println("Welcome " + email + " !");
                return true;
            }
        }
        throw new CouponSystemException("Wrong email or password please try again !");
    }

    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
        CouponsDAO couponsDAO = new CouponsDBDAO();
        Coupon tempCoupon = couponsDAO.getOneCoupon(coupon.getId());
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        if (tempCoupon == null) {
            throw new CouponSystemException("Coupon  dose not exist !");
        }
        if (tempCoupon.getEndDate() == date) {
            throw new CouponSystemException("Coupon passed deadline");
        }
        if (tempCoupon.getAmount() == 0) {
            throw new CouponSystemException("Coupon out of stock ! please try again later");
        }
        couponsDAO.addCouponPurchase(this.customerId, tempCoupon.getId());
        tempCoupon.setAmount(tempCoupon.getAmount() - 1);
        couponsDAO.updateCoupon(tempCoupon);
    }

    public ArrayList<Integer> getCustomerCoupon() {
        CouponsDAO couponsDAO = new CouponsDBDAO();
        ArrayList<Integer> couponTemp = new ArrayList<Integer>();
        couponTemp = couponsDAO.getCustomerCoupons(this.customerId);
        return couponTemp;
    }
}
