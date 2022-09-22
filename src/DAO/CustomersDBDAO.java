package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Connection.ConnectionPool;
import Core.CouponSystemException;
import Core.Customer;

public class CustomersDBDAO implements CustomersDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static Connection connection = connectionPool.getConnection();

    @Override
    public boolean isCustomerExists(String email, String password) {
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
            throw new CouponSystemException();
        } finally {
            connectionPool.restoreConnection(connection);
        }
        return isExists;
    }

    @Override
    public void addCustomer(Customer customer) {
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
            throw new CouponSystemException();
        } finally {
            connectionPool.restoreConnection(connection);
        }
        System.out.println("Disconnected from the server ");
    }

    @Override
    public void updateCustomer(Customer customer) {
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
        System.out.println("Disconnected from the server ");
    }

    @Override
    public void deleteCustomer(int customerId) {
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
        System.out.println("Disconnected from the server ");
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        String sql = "select * from Customers";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                customers.add(new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5)));
            }
            System.out.println("Done !");
            resultSet.close();
            statement.close();
            return customers;
        } catch (Exception e) {
            throw new CouponSystemException();
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    @Override
    public Customer getOneCustomer(int customerId) {
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
            System.out.println("Done !");
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
