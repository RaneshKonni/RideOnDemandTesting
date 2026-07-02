package DataProviders;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider(name = "LoginData")
    public Object[][] LoginData(){
        return new Object[][]{
//                role, email, password, expectedResult
                {"Customer", "customer2@pss.com", "12345678", "valid"},
                {"Vendor", "vendor1@pss.com", "12345678", "valid"},
                {"Admin", "admin1@pss.com", "12345678", "valid"}

//                {"Customer", "", "", "Enter a valid email address to continue.", "invalid"},
//                {"Customer", "one@pss.com", "", "Enter your password to continue.", "invalid"},
//                {"Customer", "customer1@pss.com", "123", "Invalid email or password", "invalid"},
//                {"Customer", "one@pss.com", "12345678", "Invalid email or password", "invalid"},
//                {"Vendor", "customer2@pss.com", "12345678", "Invalid email or password", "invalid"},
//                {"Admin", "customer2@pss.com", "12345678", "Invalid email or password", "invalid"},
//                {"Customer", "vendor2@pss.com", "12345678", "Invalid email or password", "invalid"},
//                {"Admin", "vendor2@pss.com", "12345678", "Invalid email or password", "invalid"},
//                {"Customer", "admin2@pss.com", "12345678", "Invalid email or password", "invalid"},
//                {"Vendor", "admin2@pss.com", "12345678", "Invalid email or password", "invalid"}
        };
    }

    @DataProvider(name = "LoginDataNeg")
    public Object[][] LoginDataNeg(){
        return new Object[][]{
                {"Customer", "", "", "Enter a valid email address to continue."},
                {"Customer", "one@pss.com", "", "Enter your password to continue."},
                {"Customer", "customer1@pss.com", "123", "Invalid email or password"},
                {"Customer", "one@pss.com", "12345678", "Invalid email or password"},
                {"Vendor", "customer2@pss.com", "12345678", "Invalid email or password"},
                {"Admin", "customer2@pss.com", "12345678", "Invalid email or password"},
                {"Customer", "vendor2@pss.com", "12345678", "Invalid email or password"},
                {"Admin", "vendor2@pss.com", "12345678", "Invalid email or password"},
                {"Customer", "admin2@pss.com", "12345678", "Invalid email or password"},
                {"Vendor", "admin2@pss.com", "12345678", "Invalid email or password"}
        };
    }
}
