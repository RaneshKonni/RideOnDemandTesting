package TestCases;

import PageObjects.AuthPage;
import PageObjects.VendorDashboardPage;
import PageObjects.VendorProfilePage;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TS011 extends BaseClass {
    AuthPage auth;
    VendorDashboardPage vendorDashboardPage;

    private static final String VENDOR_EMAIL = "vm@gmail.com";
    private static final String VENDOR_PASSWORD = "12345678";

    @BeforeMethod
    public void classSetup() throws InterruptedException {
        auth = new AuthPage(driver);
        loginUser("Vendor", VENDOR_EMAIL, VENDOR_PASSWORD);
        vendorDashboardPage = new VendorDashboardPage(driver);

        if (!vendorDashboardPage.vendorDashboardMessage()) {
            loginUser("Vendor", VENDOR_EMAIL, VENDOR_PASSWORD);
        }
    }

    @Test
    public void TC_043_verifyProfileDetails() {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC_043_verifyProfileDetails");
        logger.info("=========================================================");

        try {
            vendorDashboardPage.navigateToProfile();

            VendorProfilePage profilePage = new VendorProfilePage(driver);
            profilePage.waitForProfileLoad();

            String expectedName = "vivek mishra";
            String expectedEmail = "vm@gmail.com";
            String expectedMobile = "9999999999";
            String expectedCity = "bhopal";
            String expectedShopName = "kuch bhi electronics";

            Assert.assertEquals(profilePage.getFullName(), expectedName, "Full name mismatch!");
            Assert.assertEquals(profilePage.getEmail(), expectedEmail, "Email mismatch!");
            Assert.assertEquals(profilePage.getMobile(), expectedMobile, "Mobile mismatch!");
            Assert.assertEquals(profilePage.getCity(), expectedCity, "City mismatch!");
            Assert.assertEquals(profilePage.getShopName(), expectedShopName, "Shop name mismatch!");

            logger.info("SUCCESS: TC_043_verifyProfileDetails passed successfully!");

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC_043 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}