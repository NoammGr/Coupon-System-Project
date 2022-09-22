package DAO;

import java.util.ArrayList;

import Core.Company;

public interface CompaniesDAO {
    public boolean isCompanyExists(String email, String password);

    public void addCompany(Company company);

    public void updateCompany(Company company);

    public void deleteCompany(int companyId);

    public ArrayList<Company> getAllCompanies();

    public Company getOneCompany(int companyId);
}
