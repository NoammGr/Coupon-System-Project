package DAO;

import java.sql.Connection;
import java.sql.Date;

import Connection.ConnectionPool;
import ConnectionSystem.ClientType;
import ConnectionSystem.LoginManager;
import Core.Category;
import Core.Company;
import Core.Coupon;
import Core.Customer;
import Facades.AdminFacade;
import Facades.ClientFacade;
import Facades.CompanyFacade;
import Facades.CustomersFacade;

public class Test {

    // private static CompanyFacade loginManager = (CompanyFacade)
    // LoginManager.getInstance().login("email6", "password3",
    // ClientType.Company);

    public static void main(String[] args) {
        // CustomersDBDAO customersDBDAO = new CustomersDBDAO();
        // CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
        // AdminFacade adminFacade = new AdminFacade();
        // CompanyFacade companyFacade = new CompanyFacade();
        // CustomersFacade customersFacade = new CustomersFacade();
        // LoginManager loginManager = new LoginManager();
        try (Connection connection = ConnectionPool.getInstance().getConnection();) {
            // Company company = new Company(0, "name6", "email6", "password3");
            // Category category = Category.Restaurant;
            // String s = "2022-10-22";
            // Date date = Date.valueOf(s);
            Customer customer = new Customer(1, "Avi1", "Hazi", "email123@gmail.com",
            "1245235436");
            // Coupon coupon = new Coupon(41, 2, category, "Updated", "Updated",
            // date, date,
            // 200, 1000, "image");
            // System.out.println(companiesDBDAO.getAllCompanies());
            // companiesDBDAO.addCompany(company);
            // companiesDBDAO.deleteCompany(company);
            // customersDBDAO.addCustomer(customer);
            // customersDBDAO.updateCustomer(customer);
            // customersDBDAO.deleteCustomer(2);
            // System.out.println(customersDBDAO.getAllCustomers());
            // System.out.println(customersDBDAO.getOneCustomer(5));
            // couponsDBDAO.addCoupon(coupon);
            // couponsDBDAO.updateCoupon(coupon);
            // couponsDBDAO.deleteCoupon(3);
            // System.out.println(couponsDBDAO.getAllCoupon());
            // System.out.println(couponsDBDAO.getOneCoupon(2));
            // couponsDBDAO.addCouponPurchase(4, 14);
            // couponsDBDAO.deleteCouponPurchase(3, 2);
            // adminFacade.Login("admin@admin.com", "admin");
            // adminFacade.addCustomer(customer);
            // adminFacade.removeCustomer(customer);
            // adminFacade.createCompany(company);
            // adminFacade.deleteCompany(company);
            // System.out.println(adminFacade.getAlCustomers());
            // System.out.println(adminFacade.getAllCompanies());
            // companyFacade.Login("email0", "password0");
            // companyFacade.addCoupon(coupon);
            // companyFacade.updateCoupon(coupon);
            // companyFacade.deleteCoupon(2);
            // loginManager.login("email6", "password3", ClientType.Company);
            // loginManager.createCoupon(coupon);
            couponsDBDAO.getCustomerCoupons(1);
            // customersFacade.purchaseCoupon(coupon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
