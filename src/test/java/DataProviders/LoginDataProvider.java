package DataProviders;

import mapper.Role;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider(name = "LoginData")
    public Object[][] LoginData(){
        return new Object[][]{
//                role, email, password, expectedResult
                {Role.CUSTOMER, "customer2@pss.com", "12345678", "valid"},
                {Role.VENDOR, "vendor1@pss.com", "12345678", "valid"},
                {Role.ADMIN, "admin1@pss.com", "12345678", "valid"}

//                {Role.CUSTOMER, "", "", "Enter a valid email address to continue.", "invalid"},
//                {Role.CUSTOMER, "one@pss.com", "", "Enter your password to continue.", "invalid"},
//                {Role.CUSTOMER, "customer1@pss.com", "123", "Invalid email or password", "invalid"},
//                {Role.CUSTOMER, "one@pss.com", "12345678", "Invalid email or password", "invalid"},
//                {Role.VENDOR, "customer2@pss.com", "12345678", "Invalid email or password", "invalid"},
//                {Role.ADMIN, "customer2@pss.com", "12345678", "Invalid email or password", "invalid"},
//                {Role.CUSTOMER, "vendor2@pss.com", "12345678", "Invalid email or password", "invalid"},
//                {Role.ADMIN, "vendor2@pss.com", "12345678", "Invalid email or password", "invalid"},
//                {Role.CUSTOMER, "admin2@pss.com", "12345678", "Invalid email or password", "invalid"},
//                {Role.VENDOR, "admin2@pss.com", "12345678", "Invalid email or password", "invalid"}
        };
    }

    @DataProvider(name = "LoginDataNeg")
    public Object[][] LoginDataNeg(){
        return new Object[][]{
                {Role.CUSTOMER, "", "", "Enter a valid email address to continue."},
                {Role.CUSTOMER, "one@pss.com", "", "Enter your password to continue."},
                {Role.CUSTOMER, "customer1@pss.com", "123", "Invalid email or password"},
                {Role.CUSTOMER, "one@pss.com", "12345678", "Invalid email or password"},
                {Role.VENDOR, "customer2@pss.com", "12345678", "Invalid email or password"},
                {Role.ADMIN, "customer2@pss.com", "12345678", "Invalid email or password"},
                {Role.CUSTOMER, "vendor2@pss.com", "12345678", "Invalid email or password"},
                {Role.ADMIN, "vendor2@pss.com", "12345678", "Invalid email or password"},
                {Role.CUSTOMER, "admin2@pss.com", "12345678", "Invalid email or password"},
                {Role.VENDOR, "admin2@pss.com", "12345678", "Invalid email or password"}
        };
    }
}
