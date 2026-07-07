package TestCases;

import PageObjects.AuthPage;
import PageObjects.VendorDashboardPage;
import PageObjects.VendorProfilePage;
import TestBase.BaseClass;
import mapper.Role;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TS013 extends BaseClass {

    AuthPage auth;
    VendorDashboardPage vendorDashboardPage;

    private static final String VENDOR_EMAIL = "vm@gmail.com";
    private static final String VENDOR_PASSWORD = "12345678";

    @BeforeMethod
    public void classSetup() throws InterruptedException {
        auth = new AuthPage(driver);
        loginUser(Role.VENDOR, VENDOR_EMAIL, VENDOR_PASSWORD);
        vendorDashboardPage = new VendorDashboardPage(driver);

        // Initial dashboard verification
        if (!vendorDashboardPage.vendorDashboardMessage()) {
            loginUser(Role.VENDOR, VENDOR_EMAIL, VENDOR_PASSWORD);
        }
    }

    @Test
    public void TC_045_verifySignOut() {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC_045_verifySignOut");
        logger.info("=========================================================");

        try {
            VendorDashboardPage dashboard = new VendorDashboardPage(driver);
            dashboard.navigateToProfile();

            VendorProfilePage profilePage = new VendorProfilePage(driver);
            profilePage.waitForProfileLoad();

            // 1. Perform Sign Out
            profilePage.clickSignOut();

            // 2. Verification
            boolean isRedirected = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("auth"));

            Assert.assertTrue(isRedirected, "Sign out failed: User was not redirected to the login page.");

            logger.info("SUCCESS: TC_045_verifySignOut passed successfully!");

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC_045 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}