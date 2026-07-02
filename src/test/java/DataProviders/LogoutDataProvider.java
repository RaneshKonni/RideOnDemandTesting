package DataProviders;

import org.testng.annotations.DataProvider;

public class LogoutDataProvider {
    @DataProvider(name = "LogoutData")
    public Object[][] LogoutData(){
        return new Object[][]{
//                role, email, password, expectedResult
                {"Customer", "customer2@pss.com", "12345678", "valid"},
                {"Vendor", "vendor1@pss.com", "12345678", "valid"},
                {"Admin", "admin1@pss.com", "12345678", "valid"}
        };
    }
}
