package DAO;

import java.sql.Connection;

import Connection.ConnectionPool;
import Core.Company;

public class Test {
    public static void main(String[] args) {
        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        for (int i = 0; i < 20; i++) {
            Company company = new Company(0, "name" + i, "email" + i, "password" + i);
            try (Connection connection = ConnectionPool.getInstance().getConnection();) {
                companiesDBDAO.addCompany(company);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
