package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Connection.ConnectionPool;
import Core.Category;
import Core.Coupon;
import Core.CouponSystemException;
import Core.Customer;

/**
 * This class is implementation of CouponsDAO , in this class we are using sql
 * syntax and connetion pool
 * to connect with the data base.
 */
public class CouponsDBDAO implements CouponsDAO {

    /**
     * This method used to update the coupon amount using sql syntax .
     */
    @Override
    public void couponAmount(int couponId, int amount) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "update Coupons set amount = ? where id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, couponId);
            statement.setInt(2, amount);
        } catch (SQLException e) {
            throw new CouponSystemException("Coupon out of stock !" + e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * This method used to add coupon using sql syntax .
     */
    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "insert into Coupons(id, company_id, category, title, description, start_date, end_date, amount, price, image) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, coupon.getId());
            statement.setInt(2, coupon.getCompanyId());
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
            throw new CouponSystemException("Adding coupon method problem : " + e);

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * This method used to update coupon using sql syntax .
     */
    @Override
    public void updateCoupon(Coupon coupon) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "update Coupons set company_id = ?, category = ?, title = ?, description = ?, start_date = ?, end_date = ?, amount = ?, price = ?, image = ? where id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, coupon.getCompanyId());
            statement.setString(2, coupon.getCategory().toString());
            statement.setString(3, coupon.getTitle());
            statement.setString(4, coupon.getDescription());
            statement.setDate(5, coupon.getStartDate());
            statement.setDate(6, coupon.getEndDate());
            statement.setInt(7, coupon.getAmount());
            statement.setDouble(8, coupon.getPrice());
            statement.setString(9, coupon.getImage());
            statement.setInt(10, coupon.getId());
            statement.executeUpdate();
            System.out.println("Coupon updated successfully !");
        } catch (Exception e) {
            throw new CouponSystemException("Coupon update failed : " + e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * This method used to delete coupon using sql syntax .
     */
    @Override
    public void deleteCoupon(int couponId) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "delete from Coupons where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, couponId);
            statement.executeUpdate();
            System.out.println("Coupon has been deleted !");
        } catch (Exception e) {
            throw new CouponSystemException("Coupon delete method problem : " + e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * Using this method will return all the coupon exists in data base .
     */
    @Override
    public ArrayList<Coupon> getAllCoupon() throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        ArrayList<Coupon> coupons = new ArrayList<Coupon>();
        String sql = "select * from Coupons";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                coupons.add(new Coupon(resultSet.getInt(1), resultSet.getInt(2),
                        Category.valueOf(resultSet.getString(3)),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7),
                        resultSet.getInt(8), resultSet.getDouble(9), resultSet.getString(10)));
            }
            resultSet.close();
            statement.close();
            return coupons;
        } catch (Exception e) {
            throw new CouponSystemException("Problem with get all coupon method : " + e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * Using this method will return a coupon object , to use this method you need
     * to insert coupon id .
     */
    @Override
    public Coupon getOneCoupon(int couponId) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Coupon coupon = new Coupon();
        String sql = "select * from Coupons where id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, couponId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                coupon.setId(resultSet.getInt(1));
                coupon.setCompanyId(resultSet.getInt(2));
                coupon.setCategory(Category.valueOf(resultSet.getString(3)));
                coupon.setTitle(resultSet.getString(4));
                coupon.setDescription(resultSet.getString(5));
                coupon.setStartDate(resultSet.getDate(6));
                coupon.setEndDate(resultSet.getDate(7));
                coupon.setAmount(resultSet.getInt(8));
                coupon.setPrice(resultSet.getDouble(9));
                coupon.setImage(resultSet.getString(10));
            } else {
                System.out.println("There was a problem");
                return null;
            }
            resultSet.close();
            statement.close();
            return coupon;
        } catch (Exception e) {
            throw new CouponSystemException("Please check the coupon id !" + e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * Using this method will add coupon purchase to customer , you need to insert
     * customer-id and coupon-id
     * in order to make this method work .
     */
    @Override
    public void addCouponPurchase(int customerId, int couponId) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "insert into customers_vs_coupons (customer_id, coupon_id) values(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.setInt(2, couponId);
            statement.executeUpdate();
            couponAmount(couponId, -1);
            System.out.println("Coupon added to customer successfully !");
        } catch (SQLException e) {
            throw new CouponSystemException("There was a problem adding your coupon ! " + e);
        }

    }

    /**
     * Using this method will delete coupon purchase from customer , you need to
     * insert
     * customer-id and coupon-id
     * in order to make this method work .
     */
    @Override
    public void deleteCouponPurchase(int customerId, int couponId) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "delete from customers_vs_coupons where customer_id = ? and coupon_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, customerId);
            statement.setLong(2, couponId);
            statement.executeUpdate();
            System.out.println("Coupon removed to customer successfully !");
            couponAmount(couponId, +1);
        } catch (Exception e) {
            throw new CouponSystemException("There was a problem removing your coupon ! " + e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * Using this method will return a coupon object , to use this method you need
     * to insert coupon title .
     */
    public Coupon getCoupon(String title) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Coupon coupon = new Coupon();
        String sql = "select * from Coupons where title = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                coupon.setId(resultSet.getInt(1));
                coupon.setCompanyId(resultSet.getInt(2));
                coupon.setCategory(Category.valueOf(resultSet.getString(3)));
                coupon.setTitle(resultSet.getString(4));
                coupon.setDescription(resultSet.getString(5));
                coupon.setStartDate(resultSet.getDate(6));
                coupon.setEndDate(resultSet.getDate(7));
                coupon.setAmount(resultSet.getInt(8));
                coupon.setPrice(resultSet.getDouble(9));
                coupon.setImage(resultSet.getString(10));
            } else {
                return null;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            throw new CouponSystemException("Coupon is not exist !" + e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
        return coupon;
    }

    /**
     * This method will allow you to get all coupons by category,
     * you need to insert category in order for this method to work.
     */
    public ArrayList<Coupon> getAllCouponByCategory(Category category) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        ArrayList<Coupon> coupons = new ArrayList<Coupon>();
        String sql = "select * from Coupons where category = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                coupons.add(new Coupon(resultSet.getInt(1), resultSet.getInt(2),
                        Category.valueOf(resultSet.getString(3)),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7),
                        resultSet.getInt(8), resultSet.getDouble(9), resultSet.getString(10)));
            }
            resultSet.close();
            return coupons;
        } catch (Exception e) {
            throw new CouponSystemException("Problem with get all coupon method : " + e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * This method allow the customer to get all the coupons that he bought ,
     * you need to insert customer object.
     */
    public ArrayList<Coupon> getCustomerCoupon(Customer customer) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        ArrayList<Coupon> coupons = new ArrayList<Coupon>();
        String sql = "select * from coupons where id = (select coupon_id from customers_vs_coupons where customer_id = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customer.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                coupons.add(new Coupon(resultSet.getInt(1), resultSet.getInt(2),
                        Category.valueOf(resultSet.getString(3)),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7),
                        resultSet.getInt(8), resultSet.getDouble(9), resultSet.getString(10)));
            }
            resultSet.close();
            preparedStatement.close();
            return coupons;
        } catch (Exception e) {
            throw new CouponSystemException("Problem with get customer coupon method : " + e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    /**
     * This method allow the customer to get all the coupons that he bought ,
     * you need to insert customerId .
     */
    public ArrayList<Coupon> getCustomerCoupon(int customerId) throws CouponSystemException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        ArrayList<Coupon> coupons = new ArrayList<Coupon>();
        String sql = "select * from coupons where id = (select coupon_id from customers_vs_coupons where customer_id = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                coupons.add(new Coupon(resultSet.getInt(1), resultSet.getInt(2),
                        Category.valueOf(resultSet.getString(3)),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7),
                        resultSet.getInt(8), resultSet.getDouble(9), resultSet.getString(10)));
            }
            resultSet.close();
            preparedStatement.close();
            return coupons;
        } catch (Exception e) {
            throw new CouponSystemException("Problem with get customer coupon method : " + e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

}
