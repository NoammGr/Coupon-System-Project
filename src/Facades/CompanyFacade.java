package Facades;

import java.util.ArrayList;
import java.util.Collection;

import Core.Category;
import Core.Company;
import Core.Coupon;
import Core.CouponSystemException;
import Core.Customer;
import DAO.CompaniesDAO;
import DAO.CompaniesDBDAO;
import DAO.CouponsDBDAO;

public class CompanyFacade extends ClientFacade {

    private Company company;

    private int companyId;

    public CompanyFacade() {
    }

    public CompanyFacade(Company company) {
        super();
        this.company = company;
    }

    public boolean Login(String email, String password) throws CouponSystemException {
        CompaniesDAO companiesDBDAO = new CompaniesDBDAO();
        Collection<Company> companies = new ArrayList<Company>();
        companies = companiesDBDAO.getAllCompanies();
        for (Company company : companies) {
            if (company.getEmail().equals(email) && company.getPassword().equals(password)) {
                this.companyId = company.getId();
                System.out.println("Welcome " + email + " !");
                return true;
            }
        }
        throw new CouponSystemException("Email or password is worng , try again !");
    }

    public void addCoupon(Coupon coupon) throws CouponSystemException {
        // CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
        if (couponsDBDAO.getCoupon(coupon.getTitle()) == null) {
            couponsDBDAO.addCoupon(coupon);
            // companiesDBDAO.updateCompanyCoupon(company, coupon);
        } else {
            throw new CouponSystemException("Check the coupon name and try again !");
        }
    }

    public void updateCoupon(Coupon coupon) throws CouponSystemException {
        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
        Coupon couponTemp = new Coupon();
        couponTemp = couponsDBDAO.getOneCoupon(coupon.getId());
        if (couponTemp.getTitle() == null) {
            System.out.println("Coupon dosn't exist !");
        }
        if (couponTemp.getId() == coupon.getId() && couponTemp.getCompanyId() == coupon.getCompanyId()) {
            couponsDBDAO.updateCoupon(coupon);
        } else {
            throw new CouponSystemException("you cannot change company id or coupon id ");
        }
    }

    public void deleteCoupon(int couponId) throws CouponSystemException {
        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
        Collection<Coupon> coupons = new ArrayList<>();
        if (couponsDBDAO.getOneCoupon(couponId) == null) {
            throw new CouponSystemException("Coupon is not exist ! ");
        }
        coupons.addAll(couponsDBDAO.getAllCoupon());
        for (Coupon coupon : coupons) {
            if (coupon.getId() == couponId) {
                couponsDBDAO.deleteCoupon(couponId);
            }
        }
        System.out.println("Coupon has been deleted !");
        Collection<Customer> customers = new ArrayList<>();
        customers.addAll(customersDAO.getAllCustomers());
        for (Customer customer : customers) {
            couponsDAO.deleteCouponPurchase(customer.getId(), couponId);
        }
        System.out.println("All customers coupons deleted !");
    }

    public Collection<Coupon> getCompanyCoupons() throws CouponSystemException {
        Collection<Coupon> tempCoupons = new ArrayList<Coupon>();
        Collection<Coupon> coupons = new ArrayList<Coupon>();
        tempCoupons.addAll(couponsDAO.getAllCoupon());
        for (Coupon coupon : tempCoupons) {
            if (coupon.getCompanyId() == this.companyId) {
                coupons.add(coupon);
            }
            return coupons;
        }
        throw new CouponSystemException("Coupon is not exist !");
    }

    public Collection<Coupon> getCompanyCoupons(Category category) throws CouponSystemException {
        Collection<Coupon> tempCoupons = new ArrayList<Coupon>();
        Collection<Coupon> coupons = new ArrayList<Coupon>();
        tempCoupons.addAll(coupons);
        for (Coupon coupon : coupons) {
            if (coupon.getCompanyId() == this.companyId) {
                if (coupon.getCategory() == category) {
                    coupons.add(coupon);
                }
            }
            return coupons;
        }
        throw new CouponSystemException("Coupon is not exist !");
    }

    public Collection<Coupon> getCompanyCoupons(double maxPrice) throws CouponSystemException {
        Collection<Coupon> tempCoupons = new ArrayList<Coupon>();
        Collection<Coupon> coupons = new ArrayList<Coupon>();
        tempCoupons.addAll(coupons);
        for (Coupon coupon : coupons) {
            if (coupon.getCompanyId() == this.companyId) {
                if (coupon.getPrice() == maxPrice) {
                    coupons.add(coupon);
                }
            }
            return coupons;
        }
        throw new CouponSystemException("Coupon is not exist !");
    }

    public Company getCompanyDetails() {
        return this.company;
    }
}
