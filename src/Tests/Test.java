package Tests;

import java.sql.Connection;
import java.sql.Date;

import Connection.ConnectionPool;
import ConnectionSystem.ClientType;
import ConnectionSystem.LoginManager;
import Core.Category;
import Core.Company;
import Core.Coupon;
import Core.Customer;
import DAO.CompaniesDBDAO;
import DAO.CouponsDBDAO;
import DAO.CustomersDBDAO;
import DailyJob.CouponExpirationDailyJob;
import Facades.AdminFacade;
import Facades.ClientFacade;
import Facades.CompanyFacade;
import Facades.CustomersFacade;

public class Test {

    static CouponExpirationDailyJob couponExpirationDailyJob = new CouponExpirationDailyJob();
    static Thread dThread = new Thread(couponExpirationDailyJob);

    // private static AdminFacade loginManagerAdmin = (AdminFacade) LoginManager.getInstance().login(
    //         "admin@admin.com", "admin",
    //         ClientType.Administrator);

    // private static CompanyFacade loginManagerCompany = (CompanyFacade) LoginManager.getInstance().login(
    //         "email6", "password3",
    //         ClientType.Company);

    // private static CustomersFacade loginManagerCustomer = (CustomersFacade) LoginManager.getInstance().login(
    //         "EldarJB@gmail.com", "12314321",
    //         ClientType.Customer);

    public void testAll() {
        // dThread.start();
        // CustomersDBDAO customersDBDAO = new CustomersDBDAO();
        // CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        // CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
        // AdminFacade adminFacade = new AdminFacade();
        // CompanyFacade companyFacade = new CompanyFacade();
        // CustomersFacade customersFacade = new CustomersFacade();
        // LoginManager loginManager = new LoginManager();
        try (Connection connection = ConnectionPool.getInstance().getConnection();) {
            // Company company = new Company(0, "Intel", "Intel@gmail.com", "IntelProjects100");
            // Category category = Category.Restaurant;
            // String s = "2022-11-18";
            // Date date = Date.valueOf(s);
            // Customer customer = new Customer(0, "Eldar", "JB", "EldarJB@gmail.com",
            //         "12314321");
            // Coupon coupon = new Coupon(2, 1, category, "IDK", "IDK",
            //         date, date,
            //         200, 1000, "image");
            // System.out.println(companiesDBDAO.getAllCompanies());
            // companiesDBDAO.addCompany(company);
            // companiesDBDAO.deleteCompany(company.getId());
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
            // couponsDBDAO.addCouponPurchase(18, 41);
            // couponsDBDAO.deleteCouponPurchase(1, 1);
            // adminFacade.login("admin@admin.com", "admin");
            // adminFacade.addCompany(null);
            // adminFacade.addCustomer(null);
            // adminFacade.deleteCompany(null);
            // adminFacade.deleteCustomer(null);
            // adminFacade.getAllCompanies();
            // adminFacade.getAllCustomers();
            // adminFacade.getOneCompany(0);
            // adminFacade.getOneCustomer(0);
            // adminFacade.updateCompany(null);
            // adminFacade.updateCustomer(null);
            // System.out.println(adminFacade.getAllCustomers());
            // System.out.println(adminFacade.getAllCompanies());
            // companyFacade.login("email0", "password0");
            // companyFacade.addCoupon(coupon);
            // companyFacade.updateCoupon(coupon);
            // companyFacade.deleteCoupon(2);
            // loginManager.login("email6", "password3", ClientType.Company);
            // couponsDBDAO.getCustomerCoupon(1);
            // customersFacade.purchaseCoupon(coupon);
            // loginManagerAdmin.login("null", "null");
            // loginManagerAdmin.addCustomer(null);
            // loginManagerAdmin.addCompany(null);
            // loginManagerAdmin.deleteCompany(null);
            // loginManagerAdmin.getAllCustomers();
            // loginManagerAdmin.getAllCompanies();
            // loginManagerAdmin.getOneCompany(0);
            // loginManagerAdmin.getOneCustomer(0);
            // loginManagerAdmin.deleteCustomer(null);
            // loginManagerAdmin.updateCompany(null);
            // loginManagerAdmin.updateCustomer(null);
            // loginManagerCompany.login("null", "null");
            // loginManagerCompany.addCoupon(null);
            // loginManagerCompany.deleteCoupon(0);
            // loginManagerCompany.getCompanyCoupons();
            // loginManagerCompany.getCompanyCoupons(null);
            // loginManagerCompany.getCompanyCoupons(0);
            // loginManagerCompany.getCompanyDetails();
            // loginManagerCompany.updateCoupon(null);
            // loginManagerCustomer.login("null", "null");
            // loginManagerCustomer.purchaseCoupon(coupon);
            // System.out.println(loginManagerCustomer.getCustomerCoupon());
            // System.out.println(loginManagerCustomer.getCustomerCoupon(1000));
            // System.out.println(loginManagerCustomer.getCustomerCoupon(Category.Restaurant));
            // System.out.println(loginManagerCustomer.getCustomerCoupon());
            // System.out.println(loginManagerCustomer.getCustomerCoupon(date));
            // System.out.println(loginManagerCustomer.getCustomerDetailes());
            // dThread.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
