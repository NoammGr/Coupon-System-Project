package DAO;

import java.sql.Connection;
import java.sql.Date;

import Connection.ConnectionPool;
import Core.Category;
import Core.Coupon;
import Core.Customer;

public class Test {
    public static void main(String[] args) {
        // CustomersDBDAO customersDBDAO = new CustomersDBDAO();
        // CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
        try (Connection connection = ConnectionPool.getInstance().getConnection();) {
            // Company company = new Company(13, "name3", "email3", "password3");
            // Customer customer = new Customer(0, "Avi", "Hazi", "email@gmail.com",
            Category category = Category.Restaurant;
            String s = "2022-09-22";
            Date date = Date.valueOf(s);
            Coupon coupon = new Coupon(3, 2, category, "Hellooo", "Worldddd", date, date, 200, 1000, "image");
            // "1245235436");
            // System.out.println(companiesDBDAO.getAllCompanies());
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
