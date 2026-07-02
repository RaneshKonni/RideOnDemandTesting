package DataProviders;

import org.testng.annotations.DataProvider;

public class RegisterDataProvider {
    @DataProvider(name = "RegisterData")
    public Object[][] RegisterData(){
        return new Object[][]{
//                role, fullName, email, password, mobile, city, shopName, errorMsg, expectedResult
                {"Customer", "", "", "", "", "", "", "Enter your full name with at least 2 characters.", "valid"},
                {"Customer", "one customer", "", "", "", "", "", "Enter a valid email address to continue.", "valid"},
                {"Customer", "one customer", "customer1@.com", "", "", "", "", "Enter a valid email address to continue.", "valid"},
                {"Customer", "one customer", "customerpss.com", "", "", "", "", "Enter a valid email address to continue.", "valid"},
                {"Customer", "one customer", "customer@pss", "", "", "", "", "Enter a valid email address to continue.", "valid"},
                {"Customer", "one customer", "customer1@pss.com", "", "", "", "", "Password must be at least 8 characters.", "valid"},
                {"Customer", "one customer", "customer1@pss.com", "1234567", "", "", "", "Password must be at least 8 characters.", "valid"},
                {"Customer", "one customer", "customer1@pss.com", "12345678", "", "", "", "Enter a valid 10-digit mobile number to continue.", "valid"},
                {"Customer", "one customer", "customer1@pss.com", "123456789", "", "", "", "Enter a valid 10-digit mobile number to continue.", "valid"},
                {"Customer", "one customer", "customer1@pss.com", "12345678", "123456789", "", "", "Enter a valid 10-digit mobile number to continue.", "valid"},
                {"Customer", "one customer", "customer1@pss.com", "12345678", "12345678912", "", "", "Enter a valid 10-digit mobile number to continue.", "valid"},
                {"Customer", "one customer", "customer1@pss.com", "12345678", "1234567891", "", "", "Enter your city to continue.", "valid"},
                {"Customer", "one customer", "customer1@pss.com", "12345678", "1234567892", "pune", "", "Email customer1@pss.com is already registered. Please login.", "valid"},
                {"Vendor", "one customer", "customer1@pss.com", "123456678", "1234567891", "pune", "", "Enter your shop name to continue.", "valid"},
        };
    }
}

