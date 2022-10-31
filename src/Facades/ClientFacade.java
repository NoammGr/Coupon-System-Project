package Facades;

import DAO.CompaniesDAO;
import DAO.CompaniesDBDAO;
import DAO.CouponsDAO;
import DAO.CouponsDBDAO;
import DAO.CustomersDAO;
import DAO.CustomersDBDAO;

public abstract class ClientFacade {

    abstract boolean Login(String email, String password);

    CompaniesDAO companiesDAO = new CompaniesDBDAO();
    CustomersDAO customersDAO = new CustomersDBDAO();
    CouponsDAO couponsDAO = new CouponsDBDAO();

}
