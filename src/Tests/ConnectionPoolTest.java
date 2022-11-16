package Tests;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionPool;
import Core.CouponSystemException;

public class ConnectionPoolTest {
    public static void main(String[] args) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            System.out.println(connection);
            ConnectionPool.getInstance().restoreConnection(connection);
        } catch (CouponSystemException | SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().closeAllConnections();
            System.out.println("All connections closed");
        }
    }
}
