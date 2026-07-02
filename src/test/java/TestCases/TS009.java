package TestCases;

import PageObjects.AuthPage;
import PageObjects.VendorDashboardPage;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TS009 extends BaseClass {
    AuthPage auth;
    VendorDashboardPage vendor;

    private static final String OFFER_PRICE = "1000";
    private static final String OFFER_ITEM = "bike";
    private static final String ITEM_REGISTRATION = "TN 39 123334";
    private static final String OFFER_DESCRIPTION = "the bike will be new";
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

    @Test(description = "Verify vendor can submit an offer for a live requirement")
    public void TC_040_verifySubmitOffer() throws InterruptedException {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC_040_verifySubmitOffer");
        logger.info("=========================================================");

        try {
            vendor = new VendorDashboardPage(driver);

            Assert.assertTrue(vendor.vendorDashboardMessage(), "❌ BUG DETECTED: Vendor Dashboard failed to load after login.");
            logger.info("Vendor Dashboard loaded successfully");

            vendor.scrollToLiveRequirements();
            Thread.sleep(3000);
            int initialRequirementCount = vendor.getRequirementItemsCount();
            Assert.assertTrue(initialRequirementCount > 0, "❌ BUG DETECTED: 'Live Requirements' count pill shows 0 items available to pitch.");

            String requirementId = vendor.getFirstRequirementId();
            Assert.assertNotNull(requirementId, "❌ BUG DETECTED: The first requirement card exists but does not have a valid Requirement ID string.");
            logger.info("Targeting Requirement ID: " + requirementId);

            vendor.clickFirstSendOfferButton();

            Assert.assertTrue(vendor.isOfferFormDisplayed(), "❌ BUG DETECTED: Offer dialog overlay failed to open after clicking 'Send Offer'.");
            Thread.sleep(2000);

            int initialOfferCount = vendor.getOfferItemsCount();
            logger.info(String.format("Baseline State -> Requirements Left: %d | Total Offers Placed: %d", initialRequirementCount, initialOfferCount));

            vendor.enterOfferDetails(OFFER_PRICE, OFFER_ITEM, ITEM_REGISTRATION, OFFER_DESCRIPTION);
            vendor.clickSubmitOfferButton();
            logger.info("Offer form submitted. Waiting for state synchronization...");
            Thread.sleep(3000);

            int updatedRequirementCount = vendor.getRequirementItemsCount();
            int updatedOfferCount = vendor.getOfferItemsCount();

            logger.info(String.format("Post-Submission State -> Requirements Left: %d | Total Offers Placed: %d", updatedRequirementCount, updatedOfferCount));

            Assert.assertEquals(
                    updatedOfferCount,
                    initialOfferCount + 1,
                    String.format("❌ BUG DETECTED: My Offers panel count did not increment or took too long to increase ! Expected: %d, but got: %d", (initialOfferCount + 1), updatedOfferCount)
            );

            String submittedOfferId = vendor.getRecentlySubmittedOfferId();

            logger.info("Recently submitted offer ID: " + submittedOfferId);
            logger.info("Offer count increased from " + initialOfferCount + " to " + updatedOfferCount);
            logger.info("SUCCESS: TC_040_verifySubmitOffer passed successfully!");

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC_040 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(description = "Verify that clicking Clear button clears the offer form fields")
    public void TC_041_verifyClearOfferForm() throws InterruptedException {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC_041_verifyClearOfferForm");
        logger.info("=========================================================");

        try {
            vendor = new VendorDashboardPage(driver);

            // --- PRE-CONDITION CHECK ---
            Assert.assertTrue(vendor.vendorDashboardMessage(), "❌ BUG DETECTED: Vendor Dashboard failed to load.");
            logger.info("Vendor Dashboard loaded successfully");

            // --- STEP 1: Navigate to Live Requirements and Select Requirement ---
            Thread.sleep(3000);
            vendor.scrollToLiveRequirements();
            int initialRequirementCount = vendor.getRequirementItemsCount();
            Assert.assertTrue(initialRequirementCount > 0, "❌ BUG DETECTED: No live requirements available.");
            logger.info("Found " + initialRequirementCount + " live requirements");

            // --- STEP 2: Click Send Offer Button ---
            vendor.clickFirstSendOfferButton();
            Thread.sleep(2000);

            // --- STEP 3: Verify Offer Form is Displayed ---
            Assert.assertTrue(vendor.isOfferFormDisplayed(), "❌ BUG DETECTED: Offer form did not open.");
            logger.info("Offer form displayed");

            // --- STEP 4: Enter Text into Offer Form Fields ---
            logger.info("Entering data into form fields...");
            vendor.enterOfferDetails("2500", "car", "TN 12 AB 1234", "brand new car in excellent condition");
            logger.info("All data entered into form fields");

            // --- STEP 5: Verify Data Was Entered Before Clearing ---
            Thread.sleep(1000);
            String priceBeforeClear = vendor.getPriceFieldValue();
            logger.info("Form field values BEFORE Clear -> Price: " + priceBeforeClear);

            Assert.assertFalse(priceBeforeClear.isEmpty(), "❌ BUG: Price field should have value before clearing");

            // --- STEP 6: Click Clear Button ---
            logger.info("Clicking Clear button...");
            vendor.clickClearOfferButton();
            Thread.sleep(1500);

            // --- STEP 7: Verify the Form Fields are Cleared ---
            logger.info("Checking if form fields successfully cleared...");
            Assert.assertTrue(vendor.isOfferFormDisplayed(), "❌ BUG DETECTED: The offer form closed after clicking Clear. Expected it to remain open with cleared fields.");

            Assert.assertTrue(vendor.getPriceFieldValue().isEmpty(), "❌ BUG DETECTED: Price field was not cleared.");
            Assert.assertTrue(vendor.getVehicleFieldValue().isEmpty(), "❌ BUG DETECTED: Vehicle model field was not cleared.");
            Assert.assertTrue(vendor.getRegistrationFieldValue().isEmpty(), "❌ BUG DETECTED: Registration number field was not cleared.");
            Assert.assertTrue(vendor.getNotesFieldValue().isEmpty(), "❌ BUG DETECTED: Notes field was not cleared.");

            logger.info("Offer form fields successfully cleared!");
            logger.info("SUCCESS: TC_041_verifyClearOfferForm passed successfully!");

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC_041 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}