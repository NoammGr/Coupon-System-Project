package DAO;

import java.util.ArrayList;

import Core.Customer;

public interface CustomersDAO {
    public boolean isCustomerExists(String email, String password);

    public void addCustomer(Customer customer);

    public void updateCustomer(Customer customer);

    public void deleteCustomer(int customerId);

    public ArrayList<Customer> getAllCustomers();

    public Customer getOneCustomer(int customerId);
}
