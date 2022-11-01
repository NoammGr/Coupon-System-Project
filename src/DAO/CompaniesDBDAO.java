package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Connection.ConnectionPool;
import Core.Company;
import Core.Coupon;
import Core.CouponSystemException;

/**
 * This class is implementation of CompaniesDAO , in this class we are using sql syntax and connetion pool
 * to connect with the data base.
 */
public class CompaniesDBDAO implements CompaniesDAO {

    /**
     * Using this method will check if the company exists ,
     * to use this method you need to insert company email and company password .
     */
    @Override
    public boolean isCompanyExists(String Companyemail, String Companypassword) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        boolean isExists = false;
        String sql = "select locate(?,?) from Companies";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Companyemail);
            statement.setString(2, Companypassword);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.getString(3) == Companyemail && resultSet.getString(4) == Companypassword) {
                System.out.println("Company is exists !");
                return isExists = true;
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            throw new CouponSystemException("check email and password and try again !");
        } finally {
            connectionPool.restoreConnection(connection);
        }
        return isExists;
    }

    /**
     * This method will add company to date base using sql synatx .
     */
    @Override
    public void addCompany(Company company) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "insert into Companies(id, name, email, password) values(?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, company.getId());
            statement.setString(2, company.getName());
            statement.setString(3, company.getEmail());
            statement.setString(4, company.getPassword());
            statement.executeUpdate();
            System.out.println("Added company successfully !");
        } catch (Exception e) {
            throw new CouponSystemException("Check the company id !");

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * This method will update company parameters in server , to use this method you
     * need to insert company object.
     */
    @Override
    public void updateCompany(Company company) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "update Companies set id = ?, name = ?, email = ?, password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, company.getId());
            statement.setString(2, company.getName());
            statement.setString(3, company.getEmail());
            statement.setString(4, company.getPassword());
            statement.executeUpdate();
            System.out.println("Company updated successfully !");
        } catch (Exception e) {
            throw new CouponSystemException("Update company failed !");
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * Using this method will delete existing company , to use this method you need
     * to insert company-id .
     */
    @Override
    public void deleteCompany(int companyId) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "delete from Companies where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, companyId);
            statement.executeUpdate();
            System.out.println("Company has been deleted !");
        } catch (Exception e) {
            throw new CouponSystemException("Company delete has failed !");
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * This method will return all the existing companies
     */
    @Override
    public ArrayList<Company> getAllCompanies() throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        ArrayList<Company> companies = new ArrayList<Company>();
        String sql = "select * from Companies";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                companies.add(new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4)));
            }
            resultSet.close();
            statement.close();
            return companies;
        } catch (Exception e) {
            throw new CouponSystemException("Getting all company method failed !");
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * This method will return company object , using company-id.
     */
    @Override
    public Company getOneCompany(int companyId) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Company company = new Company();
        String sql = "select * from Companies where id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, companyId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                company.setId(resultSet.getInt(1));
                company.setName(resultSet.getString(2));
                company.setEmail(resultSet.getString(3));
                company.setPassword(resultSet.getString(4));
            } else {
                System.out.println("There was a problem");
                return null;
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            throw new CouponSystemException("Please check the company id !");
        } finally {
            connectionPool.restoreConnection(connection);
        }
        return company;
    }

    /**
     * Using this method will let companies update coupons.
     */
    public void updateCompanyCoupon(Company company, Coupon coupon) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "insert into Coupons(id, company_id, category, title, description, start_date, end_date, amount, price, image) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, coupon.getId());
            statement.setInt(2, company.getId());
            statement.setString(3, coupon.getCategory().toString());
            statement.setString(4, coupon.getTitle());
            statement.setString(5, coupon.getDescription());
            statement.setDate(6, coupon.getStartDate());
            statement.setDate(7, coupon.getEndDate());
            statement.setInt(8, coupon.getAmount());
            statement.setDouble(9, coupon.getPrice());
            statement.setString(10, coupon.getImage());
            statement.executeUpdate();
            System.out.println("Added coupon successfully !");
        } catch (Exception e) {
            throw new CouponSystemException("Update failed - ", e);

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }
}
