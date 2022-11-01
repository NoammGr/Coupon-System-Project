package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
// import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Connection.ConnectionPool;
// import Core.Coupon;
import Core.CouponSystemException;
import Core.Customer;

/**
 * This class is implementation of CustomersDAO , in this class we are using sql
 * syntax and connetion pool
 * to connect with the data base.
 */
public class CustomersDBDAO implements CustomersDAO {

    /**
     * Using this method will check if the customer exists ,
     * to use this method you need to insert customer email and customer password .
     */
    @Override
    public boolean isCustomerExists(String email, String password) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        boolean isExists = false;
        String sql = "select locate(?,?) from Companies";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.getString(3) == email && resultSet.getString(4) == password) {
                System.out.println("Company is exists !");
                return isExists = true;
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            throw new CouponSystemException("email or password is wrong try again !");
        } finally {
            connectionPool.restoreConnection(connection);
        }
        return isExists;
    }

    /**
     * Using this method will add customer to data base ,
     * you need to insert customer object in order to make this mthod work .
     */
    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "insert into Customers(id, first_name, last_name, email, password) values(?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setString(4, customer.getEmail());
            statement.setString(5, customer.getPassword());
            statement.executeUpdate();
            System.out.println("Added user successfully !");
        } catch (Exception e) {
            throw new CouponSystemException("Erorr in addCustomer method  - " + e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * Using this method will update custoemr in data base ,
     * you need to insert customer object in order to make this mthod work .
     */
    @Override
    public void updateCustomer(Customer customer) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "update Customers set id = ?, first_name = ?, last_name = ?, email = ?, password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setString(4, customer.getEmail());
            statement.setString(5, customer.getPassword());
            statement.executeUpdate();
            System.out.println("Customer updated successfully !");
        } catch (Exception e) {
            throw new CouponSystemException();
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * Using this method will delete customer ,
     * you need to insert customer-id .
     */
    @Override
    public void deleteCustomer(int customerId) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "delete from Customers where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, customerId);
            statement.executeUpdate();
            System.out.println("Customer has been deleted !");
        } catch (Exception e) {
            throw new CouponSystemException();
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * This method returns all customers exists in data base .
     */
    @Override
    public ArrayList<Customer> getAllCustomers() throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        String sql = "select * from Customers";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                customers.add(new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5)));
            }
            resultSet.close();
            statement.close();
            return customers;
        } catch (Exception e) {
            throw new CouponSystemException();
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * This method will return !one! customer object ,
     * you need to insert custoemr-id .
     */
    @Override
    public Customer getOneCustomer(int customerId) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Customer customer = new Customer();
        String sql = "select * from Customers where id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
                customer.setFirstName(resultSet.getString(2));
                customer.setLastName(resultSet.getString(3));
                customer.setEmail(resultSet.getString(4));
                customer.setPassword(resultSet.getString(5));
            } else {
                System.out.println("There was a problem");
                return null;
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            throw new CouponSystemException();
        } finally {
            connectionPool.restoreConnection(connection);
        }
        return customer;
    }

    /**
     * Using this method will return customer object ,
     * using customer-email .
     */
    public Customer getOneCustomer(String email) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Customer customer = new Customer();
        String sql = "select * from Customers where email = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
                customer.setFirstName(resultSet.getString(2));
                customer.setLastName(resultSet.getString(3));
                customer.setEmail(resultSet.getString(4));
                customer.setPassword(resultSet.getString(5));
            } else {
                return null;
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            throw new CouponSystemException();
        } finally {
            connectionPool.restoreConnection(connection);
        }
        return customer;
    }
}
