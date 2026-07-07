package TestCases;

import PageObjects.AuthPage;
import PageObjects.VendorDashboardPage;
import PageObjects.VendorProfilePage;
import TestBase.BaseClass;
import mapper.Role;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS013 extends BaseClass {

    AuthPage auth;
    VendorDashboardPage vendorDashboardPage;

    private static final String VENDOR_EMAIL = "vm@gmail.com";
    private static final String VENDOR_PASSWORD = "12345678";

    @BeforeMethod
    public void classSetup() throws InterruptedException {
        loginUser(Role.VENDOR, VENDOR_EMAIL, VENDOR_PASSWORD);
        vendorDashboardPage = new VendorDashboardPage(driver);

        // Initial dashboard verification
        if (!vendorDashboardPage.vendorDashboardMessage()) {
            loginUser(Role.VENDOR, VENDOR_EMAIL, VENDOR_PASSWORD);
        }
    }

    @Test
    public void TC_044_verifyMailMetrics() {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC_044_verifyMailMetrics");
        logger.info("=========================================================");

        try {
            VendorDashboardPage dashboard = new VendorDashboardPage(driver);
            dashboard.navigateToProfile();

            VendorProfilePage profilePage = new VendorProfilePage(driver);
            // Ensure the mail card is visible before reading metrics
            profilePage.waitForMailSection();

            // Expected values (Adjust these based on your test environment data)
            String expectedTotal = "0";
            String expectedUnread = "0";

            // Assertions
            Assert.assertEquals(profilePage.getTotalItemsCount(), expectedTotal, "Total items count mismatch!");
            Assert.assertEquals(profilePage.getUnreadCount(), expectedUnread, "Unread items count mismatch!");

            logger.info("SUCCESS: TC_044_verifyMailMetrics passed successfully!");

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC_044 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}