package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Core.CouponSystemException;

public class ConnectionPool {

    private Set<Connection> connections = new HashSet<>();
    public static final int MAX_CONNECTIONS = 5;
    private boolean isActive;
    private String url = "jdbc:mysql://localhost:3306/CouponSystem";
    private String user = "root";
    private String password = "12345678";

    private static ConnectionPool instance;

    private ConnectionPool() throws SQLException {
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            this.connections.add(DriverManager.getConnection(url, user, password));

        }
        isActive = true;
    }

    public static ConnectionPool getInstance() throws CouponSystemException {
        if (instance == null) {
            try {
                instance = new ConnectionPool();
            } catch (SQLException e) {
                throw new CouponSystemException("Connection Pool load failed !", e);
            }
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        if (!isActive) {
            throw new ConcurrentModificationException("Connection pool is not active !");
        }
        while (this.connections.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Iterator<Connection> iterator = this.connections.iterator();
        Connection con = iterator.next();
        iterator.remove();
        return con;
    }

    public synchronized void restoreConnection(Connection connection) {
        this.connections.add(connection);
        notify();
    }

    public synchronized void closeAllConnections() {
        this.isActive = false;
        while (this.connections.size() < MAX_CONNECTIONS) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Connection connection : connections) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new CouponSystemException("Close all connection faliure", e);
                }
            }
        }
    }

    public synchronized void closeConnection() throws CouponSystemException {
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (Exception e) {
                throw new CouponSystemException("Closeing error !");
            }
        }
    }
}
