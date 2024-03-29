package ConnectionSystem;

import Core.CouponSystemException;
import Facades.AdminFacade;
import Facades.ClientFacade;
import Facades.CompanyFacade;
import Facades.CustomersFacade;

/**
 * This class gives the option to login system .
 */
public class LoginManager {

    public LoginManager() {

    }

    private static LoginManager instance;

    public static LoginManager getInstance() throws CouponSystemException {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public ClientFacade login(String name, String password, ClientType client) throws CouponSystemException {

        if (client == (ClientType.Administrator)) {
            AdminFacade adminFacade = new AdminFacade();
            adminFacade.login(name, password);
            return adminFacade;
        }

        if (client == (ClientType.Company)) {
            CompanyFacade companyFacade = new CompanyFacade();
            companyFacade.login(name, password);
            return companyFacade;
        }

        if (client == (ClientType.Customer)) {
            CustomersFacade customersFacade = new CustomersFacade();
            customersFacade.login(name, password);
            return customersFacade;
        }
        throw new CouponSystemException("Wrong email or password please try again !");
    }

}
