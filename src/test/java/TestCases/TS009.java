package TestCases;

import PageObjects.AuthPage;
import PageObjects.VendorDashboardPage;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TS009 extends BaseClass {
    AuthPage auth;
    VendorDashboardPage vendor;

    private static final String OFFER_PRICE = "1000";
    private static final String OFFER_ITEM = "bike";
    private static final String ITEM_REGISTRATION = "TN 39 123334";
    private static final String OFFER_DESCRIPTION = "the bike will be new";
    private static final String VENDOR_EMAIL = "vm@gmail.com";
    private static final String VENDOR_PASSWORD = "12345678";

    @BeforeClass
    public void classSetup() throws InterruptedException {
        auth = new AuthPage(driver);
        auth.loginAsVendor(VENDOR_EMAIL, VENDOR_PASSWORD);
        Thread.sleep(3000);
        vendor = new VendorDashboardPage(driver);
        if (!vendor.vendorDashboardMessage()) {
            System.out.println("⚠️ Initial login check failed. Attempting re-login...");
            auth.loginAsVendor(VENDOR_EMAIL, VENDOR_PASSWORD);
            Thread.sleep(3000);
            vendor = new VendorDashboardPage(driver);
        }
    }

    @Test(description = "Verify vendor can submit an offer for a live requirement")
    public void TC_040_verifySubmitOffer() throws InterruptedException {
        vendor = new VendorDashboardPage(driver);

        Assert.assertTrue(vendor.vendorDashboardMessage(), "❌ BUG DETECTED: Vendor Dashboard failed to load after login.");
        System.out.println("✓ Vendor Dashboard loaded successfully");

        vendor.scrollToLiveRequirements();

        int initialRequirementCount = vendor.getRequirementItemsCount();
        Assert.assertTrue(initialRequirementCount > 0, "❌ BUG DETECTED: 'Live Requirements' count pill shows 0 items available to pitch.");

        String requirementId = vendor.getFirstRequirementId();
        Assert.assertNotNull(requirementId, "❌ BUG DETECTED: The first requirement card exists but does not have a valid Requirement ID string.");
        System.out.println("ℹ️ Targeting Requirement ID: " + requirementId);

        vendor.clickFirstSendOfferButton();

        Assert.assertTrue(vendor.isOfferFormDisplayed(), "❌ BUG DETECTED: Offer dialog overlay failed to open after clicking 'Send Offer'.");
        Thread.sleep(2000);

        int initialOfferCount = vendor.getOfferItemsCount();
        System.out.println(String.format("📊 Baseline State -> Requirements Left: %d | Total Offers Placed: %d", initialRequirementCount, initialOfferCount));

        vendor.enterOfferDetails(OFFER_PRICE, OFFER_ITEM, ITEM_REGISTRATION, OFFER_DESCRIPTION);
        vendor.clickSubmitOfferButton();
        System.out.println("📨 Offer form submitted. Waiting for state synchronization...");
        Thread.sleep(3000);

        int updatedRequirementCount = vendor.getRequirementItemsCount();
        int updatedOfferCount = vendor.getOfferItemsCount();

        System.out.println(String.format("📊 Post-Submission State -> Requirements Left: %d | Total Offers Placed: %d", updatedRequirementCount, updatedOfferCount));

        Assert.assertEquals(
                updatedOfferCount,
                initialOfferCount + 1,
                String.format("❌ BUG DETECTED: My Offers panel count did not increment! Expected: %d, but got: %d", (initialOfferCount + 1), updatedOfferCount)
        );

        String submittedOfferId = vendor.getRecentlySubmittedOfferId();

        System.out.println("✓ Recently submitted offer ID: " + submittedOfferId);
        System.out.println("✓ Test complete: Offer successfully submitted!");
        System.out.println("  - Offer count increased from " + initialOfferCount + " to " + updatedOfferCount);
        System.out.println("  - Status: PASS ✓");
    }


    @Test(description = "Verify that clicking Clear button closes the offer form")
    public void TC_041_verifyClearOfferForm() throws InterruptedException {
        vendor = new VendorDashboardPage(driver);

        // --- PRE-CONDITION CHECK ---
        Assert.assertTrue(vendor.vendorDashboardMessage(), "❌ BUG DETECTED: Vendor Dashboard failed to load.");
        System.out.println("✓ Vendor Dashboard loaded successfully");

        // --- STEP 1: Navigate to Live Requirements and Select Requirement ---
        vendor.scrollToLiveRequirements();
        int initialRequirementCount = vendor.getRequirementItemsCount();
        Assert.assertTrue(initialRequirementCount > 0, "❌ BUG DETECTED: No live requirements available.");
        System.out.println("ℹ️ Found " + initialRequirementCount + " live requirements");

        // --- STEP 2: Click Send Offer Button ---
        vendor.clickFirstSendOfferButton();
        Thread.sleep(2000);

        // --- STEP 3: Verify Offer Form is Displayed ---
        Assert.assertTrue(vendor.isOfferFormDisplayed(), "❌ BUG DETECTED: Offer form did not open.");
        System.out.println("✓ Offer form displayed");

        // --- STEP 4: Enter Text into Offer Form Fields ---
        System.out.println("\n--- Entering data into form fields ---");
        vendor.enterOfferDetails("2500", "car", "TN 12 AB 1234", "brand new car in excellent condition");
        System.out.println("✓ All data entered into form fields");

        // --- STEP 5: Verify Data Was Entered Before Clearing ---
        Thread.sleep(1000);
        String priceBeforeClear = vendor.getPriceFieldValue();
        System.out.println("\n--- Form field values BEFORE Clear ---");
        System.out.println("  Price: " + priceBeforeClear);

        Assert.assertFalse(priceBeforeClear.isEmpty(), "❌ BUG: Price field should have value before clearing");

        // --- STEP 6: Click Clear Button ---
        System.out.println("\n--- Clicking Clear button ---");
        vendor.clickClearOfferButton();

        // Wait for the UI animation to remove the form from the DOM
        Thread.sleep(1500);

        // --- STEP 7: Verify the Form is Closed ---
        System.out.println("Checking if form successfully closed...");

        // Note: Because isOfferFormDisplayed() uses an explicit wait, it may pause here
        // for a few seconds until the timeout is reached, which safely returns false.
        boolean isFormStillVisible = vendor.isOfferFormDisplayed();

        Assert.assertFalse(
                isFormStillVisible,
                "❌ BUG DETECTED: The offer form remained open after clicking Clear. Expected it to close."
        );

        System.out.println("\n✓ Offer form successfully closed and removed from view!");
        System.out.println("🎉 Test complete: Clear button functionality verified!");
        System.out.println("  - Status: PASS ✓");
    }
}