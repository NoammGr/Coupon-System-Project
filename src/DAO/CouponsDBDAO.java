package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Connection.ConnectionPool;
import Core.Category;
import Core.Coupon;
import Core.CouponSystemException;

public class CouponsDBDAO implements CouponsDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static Connection connection = connectionPool.getConnection();

    @Override
    public void addCoupon(Coupon coupon) {
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
            throw new CouponSystemException("Problem : ", e);

        } finally {
            connectionPool.restoreConnection(connection);
        }
        System.out.println("Disconnected from the server ");
    }

    @Override
    public void updateCoupon(Coupon coupon) {
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
            throw new CouponSystemException("Problem : ", e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
        System.out.println("Disconnected from the server ");
    }

    @Override
    public void deleteCoupon(int couponId) {
        String sql = "delete from Coupons where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, couponId);
            statement.executeUpdate();
            System.out.println("Coupon has been deleted !");
        } catch (Exception e) {
            throw new CouponSystemException("Problem :", e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
        System.out.println("Disconnected from the server ");
    }

    @Override
    public ArrayList<Coupon> getAllCoupon() {
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
            System.out.println("Done !");
            resultSet.close();
            statement.close();
            return coupons;
        } catch (Exception e) {
            throw new CouponSystemException("Problem : ", e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    @Override
    public Coupon getOneCoupon(int couponId) {
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
            System.out.println("Done !");
            resultSet.close();
            statement.close();
            return coupon;
        } catch (Exception e) {
            throw new CouponSystemException("There was a problem : ", e);
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    @Override
    public void addCouponPurchase(int customerId, int couponId) {

    }

    @Override
    public void deleteCouponPurchase(int customerId, int couponId) {

    }

}
