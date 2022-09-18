package Connection;

import java.sql.Connection;
import java.sql.SQLException;

import Core.CouponSystemException;

public class Test {
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
