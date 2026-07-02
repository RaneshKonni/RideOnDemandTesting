package TestCases;

import PageObjects.AuthPage;
import PageObjects.VendorDashboardPage;
import TestBase.BaseClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class TS010 extends BaseClass {
    AuthPage auth;
    VendorDashboardPage vendor;

    private static final String VENDOR_EMAIL = "vm@gmail.com";
    private static final String VENDOR_PASSWORD = "12345678";

    @BeforeMethod
    public void classSetup() throws InterruptedException {
        auth = new AuthPage(driver);
        loginUser("Vendor", VENDOR_EMAIL, VENDOR_PASSWORD);
        Thread.sleep(3000);

        vendor = new VendorDashboardPage(driver);
        if (!vendor.vendorDashboardMessage()) {
            logger.warn("Initial login check failed. Attempting re-login...");
            loginUser("Vendor", VENDOR_EMAIL, VENDOR_PASSWORD);
            Thread.sleep(3000);
            vendor = new VendorDashboardPage(driver);
        }
    }

    @Test(description = "TC_042: Verify withdrawing an offer")
    public void TC_042_verifyWithdrawOffer() throws InterruptedException {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC_042_verifyWithdrawOffer");
        logger.info("=========================================================");

        try {
            vendor = new VendorDashboardPage(driver);

            // --- PRE-CONDITION CHECK ---
            Assert.assertTrue(vendor.vendorDashboardMessage(), "❌ BUG DETECTED: Vendor Dashboard failed to load.");
            logger.info("Vendor Dashboard loaded successfully");

            // --- STEP 1: Navigate to 'My offers' section ---
            vendor.scrollToMyOffers();

            int initialOfferCount = vendor.getOfferItemsCount();
            Assert.assertTrue(initialOfferCount > 0, "❌ Pre-condition Failed: There are no offers available to withdraw. Count is 0.");
            logger.info("Baseline state -> Total Active Offers: " + initialOfferCount);

            // Ensure at least one offer has a Withdraw button
            Assert.assertTrue(vendor.isWithdrawButtonAvailable(), "❌ Pre-condition Failed: 'Withdraw' button not found on any active offers.");

            // --- STEP 2: Click 'Withdraw' on an existing offer ---
            logger.info("Clicking 'Withdraw' on the first available active offer...");

            // 1. Trigger the withdrawal and handle the JavaScript alert
            vendor.clickWithdrawAndAcceptAlert();

            // 2. Fetch the updated status (waiting for it to become "Rejected")
            String expectedStatus = "Rejected";
            String currentStatus = vendor.waitForAndGetOfferStatus(expectedStatus);

            logger.info("Offer status successfully updated to: " + currentStatus);

            // 3. Assert that the status successfully updated
            Assert.assertTrue(currentStatus.equalsIgnoreCase(expectedStatus),
                    "Expected offer status to be '" + expectedStatus + "' but found: " + currentStatus);

            logger.info("Offer successfully withdrawn and removed from the active list!");
            logger.info("SUCCESS: TC_042_verifyWithdrawOffer passed successfully!");

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC_042 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}