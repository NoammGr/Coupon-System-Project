package Facades;

import java.util.ArrayList;
import java.util.Collection;

import Core.Company;
import Core.Coupon;
import Core.CouponSystemException;
import Core.Customer;
import DAO.CompaniesDBDAO;
import DAO.CouponsDBDAO;
import DAO.CustomersDAO;
import DAO.CustomersDBDAO;

/**
 * This class contains all the logical work .
 */
public class AdminFacade extends ClientFacade {

    public AdminFacade() {

    }

    /**
     * Login method for admins .
     */
    public boolean login(String email, String password) throws CouponSystemException {
        if (email == "admin@admin.com" && password == "admin") {
            System.out.println("Welcome " + email + " !");
            return true;
        } else {
            throw new CouponSystemException("Email or password is worng , try again !");
        }
    }

    /**
     * This method will allow admin to add new companies .
     */
    public void addCompany(Company company) throws CouponSystemException {
        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        if (companiesDBDAO.getOneCompany(company.getId()) == null) {
            companiesDBDAO.addCompany(company);
        } else {
            throw new CouponSystemException("company already exists ! ");
        }
    }

    /**
     * This method will allow admin to update companies ,
     * be aware that the admin can't change company id, and company name !
     */
    public void updateCompany(Company company) throws CouponSystemException {
        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        Company comp = new Company();
        if (companiesDBDAO.getOneCompany(company.getId()) == null) {
            throw new CouponSystemException("Company doesn't exist !");
        }
        if (company.getId() != comp.getId()) {
            throw new CouponSystemException("It's impossible to change 'Company id' !");
        }
        if (!company.getName().equals(comp.getName())) {
            throw new CouponSystemException("It's impossible to change 'Company name' !");
        }
        companiesDBDAO.updateCompany(company);
    }

    /**
     * This method will allow admin to delete company !
     */
    public void deleteCompany(Company company) throws CouponSystemException {
        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
        Company comp = new Company();
        comp = companiesDBDAO.getOneCompany(company.getId());
        Collection<Coupon> coupons = new ArrayList<>();

        if (companiesDBDAO.getOneCompany(company.getId()) == null) {
            throw new CouponSystemException("Company doesn't exist , check the Id again !");
        }
        coupons.addAll(comp.getCoupons());
        for (Coupon coupon : coupons) {
            couponsDBDAO.deleteCoupon(coupon.getId());
        }
        System.out.println("All coupons has been deleted ! ");
        // coupons.addAll(couponsDBDAO.getAllCoupon());
        companiesDBDAO.deleteCompany(company.getId());
    }

    /**
     * This method will allow admin to get all companies registered !
     */
    public Collection<Company> getAllCompanies() throws CouponSystemException {
        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        Collection<Company> companies = new ArrayList<Company>();
        try {
            companies.addAll(companiesDBDAO.getAllCompanies());
        } catch (Exception e) {
            throw new CouponSystemException("Function error !", e);
        }
        return companies;
    }

    /**
     * This method will return company object using company-id ,
     * using this method will also look if company exists !
     */
    public Company getOneCompany(int companyId) throws CouponSystemException {
        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        if (companiesDBDAO.getOneCompany(companyId) == null) {
            throw new CouponSystemException("Company doesn't exist , check the Id again !");
        }
        return companiesDBDAO.getOneCompany(companyId);
    }

    /**
     * This method allow admin to add customer !
     */
    public void addCustomer(Customer customer) throws CouponSystemException {
        CustomersDAO customersDBDAO = new CustomersDBDAO();
        if (customersDBDAO.getOneCustomer(customer.getEmail()) == null) {
            customersDBDAO.addCustomer(customer);
        } else {
            throw new CouponSystemException("Customer already exist !");
        }
        // if (customersDBDAO.getOneCustomer(customer.getId()).getEmail() ==
        // customer.getEmail()) {
        // throw new CouponSystemException("Customer already exist !");
        // } else {
        // customersDBDAO.addCustomer(customer);
        // }
    }

    /**
     * * This method allow admin to update customer info , * be aware that admin
     * can't change customer-id !
     */
    public void updateCustomer(Customer customer) throws CouponSystemException {
        CustomersDAO customersDBDAO = new CustomersDBDAO();
        Customer custom = new Customer();
        custom = customersDBDAO.getOneCustomer(customer.getId());
        if (customersDBDAO.getOneCustomer(customer.getId()) == null) {
            throw new CouponSystemException("Customer doesn't exist !");
        }
        if (customer.getId() != custom.getId()) {
            throw new CouponSystemException("It's impossible to change 'Customer id' !");
        }
        customersDBDAO.updateCustomer(customer);
    }

    /**
     * This method will delete customer and all of this coupons,
     * in order to use this method you need to insert customer object
     */
    public void deleteCustomer(Customer customer) throws CouponSystemException {

        CustomersDBDAO customersDBDAO = new CustomersDBDAO();
        Customer custom = new Customer();
        custom = customersDBDAO.getOneCustomer(customer.getId());
        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
        Collection<Coupon> coupons = new ArrayList<>();

        if (custom.getId() == -1) {
            throw new CouponSystemException("Customer doesn't exist !");
        }
        coupons.addAll(custom.getCoupons());

        for (Coupon coupon : coupons) {
            couponsDBDAO.deleteCouponPurchase(customer.getId(), coupon.getId());
            couponsDBDAO.deleteCoupon(coupon.getId());
        }

        System.out.println("All coupons has been deleted ! ");
        customersDBDAO.deleteCustomer(customer.getId());
    }

    /**
     * This method will return all customers exists in app !
     */
    public Collection<Customer> getAllCustomers() throws CouponSystemException {
        CustomersDBDAO customersDBDAO = new CustomersDBDAO();
        Collection<Customer> customers = new ArrayList<Customer>();
        try {
            customers.addAll(customersDBDAO.getAllCustomers());
        } catch (Exception e) {
            throw new CouponSystemException("Function error !", e);
        }
        return customers;
    }

    /**
     * This method returns customer object using customer-id
     */
    public Customer getOneCustomer(int customerId) throws CouponSystemException {
        CustomersDBDAO customersDBDAO = new CustomersDBDAO();
        if (customersDBDAO.getOneCustomer(customerId) == null) {
            throw new CouponSystemException("Customer doesn't exist !");
        }
        return customersDBDAO.getOneCustomer(customerId);
    }
}
