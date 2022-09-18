package DAO;

import java.sql.Connection;

import Connection.ConnectionPool;

public class Test {
    public static void main(String[] args) {
        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        try (Connection connection = ConnectionPool.getInstance().getConnection();) {
            System.out.println(companiesDBDAO.getAllCompanies());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
