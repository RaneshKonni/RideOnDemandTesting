package DataProviders;

import mapper.Role;
import org.testng.annotations.DataProvider;

public class LogoutDataProvider {
    @DataProvider(name = "LogoutData")
    public Object[][] LogoutData(){
        return new Object[][]{
//                role, email, password, expectedResult
                {Role.CUSTOMER, "customer2@pss.com", "12345678", "valid"},
                {Role.VENDOR, "vendor1@pss.com", "12345678", "valid"},
                {Role.ADMIN, "admin1@pss.com", "12345678", "valid"}
        };
    }
}
