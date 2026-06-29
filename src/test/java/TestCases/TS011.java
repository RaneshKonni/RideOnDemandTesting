package TestCases;

import PageObjects.AuthPage;
import PageObjects.VendorDashboardPage;
import PageObjects.VendorProfilePage;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TS011 extends BaseClass {
    AuthPage auth;
    VendorDashboardPage vendorDashboardPage;

    private static final String VENDOR_EMAIL = "vm@gmail.com";
    private static final String VENDOR_PASSWORD = "12345678";

    @BeforeClass
    public void classSetup() throws InterruptedException {
        auth = new AuthPage(driver);
        auth.loginAsVendor(VENDOR_EMAIL, VENDOR_PASSWORD);
        vendorDashboardPage = new VendorDashboardPage(driver);

        // Initial dashboard verification
        if (!vendorDashboardPage.vendorDashboardMessage()) {
            auth.loginAsVendor(VENDOR_EMAIL, VENDOR_PASSWORD);
        }
    }

    @Test
    public void TC_043_verifyProfileDetails() {
        // 1. Navigate to Profile
        vendorDashboardPage.navigateToProfile();

        // 2. Initialize and Wait for page to render
        VendorProfilePage profilePage = new VendorProfilePage(driver);
        profilePage.waitForProfileLoad();

        // 3. Expected Data
        String expectedName = "vivek mishra";
        String expectedEmail = "vm@gmail.com";
        String expectedMobile = "9999999999";
        String expectedCity = "bhopal";
        String expectedShopName = "kuch bhi electronics";

        // 4. Assertions
        Assert.assertEquals(profilePage.getFullName(), expectedName, "Full name mismatch!");
        Assert.assertEquals(profilePage.getEmail(), expectedEmail, "Email mismatch!");
        Assert.assertEquals(profilePage.getMobile(), expectedMobile, "Mobile mismatch!");
        Assert.assertEquals(profilePage.getCity(), expectedCity, "City mismatch!");
        Assert.assertEquals(profilePage.getShopName(), expectedShopName, "Shop name mismatch!");

        System.out.println("✅ All profile details verified successfully.");
    }
}