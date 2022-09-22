package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Connection.ConnectionPool;
import Core.Company;

public class CompaniesDBDAO implements CompaniesDAO {

    @Override
    public boolean isCompanyExists(String email, String password) {
        boolean isExists = false;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
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
            e.printStackTrace();
        }
        return isExists;
    }

    @Override
    public void addCompany(Company company) {
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
            e.printStackTrace();
        } finally {
            connectionPool.restoreConnection(connection);
        }
        System.out.println("Disconnected from the server ");
    }

    @Override
    public void updateCompany(Company company) {
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
            e.printStackTrace();
        } finally {
            connectionPool.restoreConnection(connection);
        }
        System.out.println("Disconnected from the server ");
    }

    @Override
    public void deleteCompany(Company company) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "delete from Companies where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, company.getId());
            statement.executeUpdate();
            System.out.println("Company has been deleted !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionPool.restoreConnection(connection);
        }
        System.out.println("Disconnected from the server ");
    }

    @Override
    public ArrayList<Company> getAllCompanies() {
        ArrayList<Company> companies = new ArrayList<Company>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String sql = "select * from Companies";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                companies.add(new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4)));
            }
            System.out.println("Done !");
            resultSet.close();
            statement.close();
            return companies;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionPool.restoreConnection(connection);
        }
        return companies;
    }

    @Override
    public Company getOneCompany(int companyId) {
        Company company = new Company();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
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
            System.out.println("Done !");
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionPool.restoreConnection(connection);
        }
        return company;
    }

}
