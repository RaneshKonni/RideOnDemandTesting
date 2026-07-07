package TestCases;

import PageObjects.VendorDashboardPage;
import TestBase.BaseClass;
import mapper.Role;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS008 extends BaseClass {

    @BeforeMethod
    public void setup() {
        loginUser(Role.VENDOR, "vm@gmail.com", "12345678");
    }

    @Test
    public void verifyDashboardMetricsDisplay() {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: verifyDashboardMetricsDisplay");
        logger.info("=========================================================");

        try {
            //  Initialize page object
            VendorDashboardPage vendorDashboard = new VendorDashboardPage(driver);

            // Verify dashboard message is displayed
            Assert.assertTrue(vendorDashboard.vendorDashboardMessage(),
                    "Vendor Dashboard welcome message should be displayed");

            //verify profile button is displayed
            Assert.assertTrue(vendorDashboard.isProfileButtonDisplayed(),
                    "Vendor profile button should be displayed");

            // Verify all 3 metric cards are visible
            Assert.assertTrue(vendorDashboard.isMetricsVisible(),
                    "All metric cards (Live requirements, My offers, Selected requirement) should be visible");

            logger.info("SUCCESS: verifyDashboardMetricsDisplay passed successfully! All dashboard metrics are displayed correctly.");

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch(Exception e) {
            logger.error("FAILURE: Exception encountered during verifyDashboardMetricsDisplay execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}