package TestCases;

import PageObjects.AuthPage;
import PageObjects.VendorDashboardPage;
import TestBase.BaseClass;
import mapper.Role;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TS008 extends BaseClass  {

    VendorDashboardPage vendorDashboard;

    @BeforeMethod
    public void setup() {
        loginUser(Role.VENDOR, "vm@gmail.com", "12345678");
        vendorDashboard = new VendorDashboardPage(driver);
    }

    /**
     * TC_038 - Verify Demand Feed list
     * Prerequisites: User is logged in as a Vendor
     * Steps:
     * 1. Navigate to Vendor Dashboard (done in BeforeMethod)
     * 2. Scroll to "Live requirements" section
     * 3. Verify a list of requirements is displayed
     * Expected: List should show requirements with location, price, dates, and notes
     */
    @Test
    public void TC_038_verifyDemandFeedList() throws InterruptedException {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC_038_verifyDemandFeedList");
        logger.info("=========================================================");

        try {
            // Step 1: Verify dashboard is loaded
            Assert.assertTrue(vendorDashboard.vendorDashboardMessage(),
                    "Vendor Dashboard should be displayed");
            logger.info("Vendor Dashboard loaded successfully");

            // Step 2: Verify Live Requirements heading is displayed
            Assert.assertTrue(vendorDashboard.isLiveRequirementsHeadingDisplayed(),
                    "Live requirements heading should be displayed");
            logger.info("Live requirements heading is visible");

            // Step 3: Scroll to Live Requirements section
            vendorDashboard.scrollToLiveRequirements();
            logger.info("Scrolled to Live requirements section");

            Thread.sleep(3000); // Wait for 3 seconds to ensure the section is in view

            // Step 4: Verify demand feed list is displayed
            Assert.assertTrue(vendorDashboard.isDemandFeedListDisplayed(),
                    "Demand feed list should be displayed");
            logger.info("Demand feed list container is visible");

            // Step 5: Verify list has requirements
            int itemCount = vendorDashboard.getRequirementItemsCount();
            Assert.assertTrue(itemCount > 0,
                    "At least one requirement item should be displayed in the list");
            logger.info("Found " + itemCount + " requirement items in the list");

            // Step 6: Verify all requirements have all details (Car, Bike, location, price, dates, notes)
            if (itemCount > 0) {
                // Verify each requirement has all required details
                for (int i = 0; i < itemCount; i++) {
                    logger.info("Verifying Requirement #" + (i + 1));

                    String vehicleType = vendorDashboard.getRequirementVehicleType(i);
                    String location = vendorDashboard.getRequirementLocation(i);
                    String price = vendorDashboard.getRequirementPrice(i);
                    String dates = vendorDashboard.getRequirementDates(i);
                    String notes = vendorDashboard.getRequirementNotes(i);

                    // Verify each detail is not empty
                    Assert.assertFalse(vehicleType.isEmpty(),
                            "Vehicle type should be displayed for requirement #" + (i + 1) + " (e.g., Car, Bike)");
                    logger.info("Vehicle Type: " + vehicleType);

                    Assert.assertFalse(location.isEmpty(),
                            "Location should be displayed in requirement #" + (i + 1));
                    logger.info("Location: " + location);

                    Assert.assertFalse(price.isEmpty(),
                            "Price should be displayed in requirement #" + (i + 1));
                    logger.info("Price: " + price);

                    Assert.assertFalse(dates.isEmpty(),
                            "Dates should be displayed in requirement #" + (i + 1));
                    logger.info("Dates: " + dates);

                    // Notes may be optional, but verify it can be retrieved
                    if (!notes.isEmpty()) {
                        logger.info("Notes: " + notes);
                    } else {
                        logger.info("Notes: Not provided for requirement #" + (i + 1));
                    }

                    // Verify complete requirement details
                    Assert.assertTrue(vendorDashboard.hasRequirementDetails(i),
                            "Requirement #" + (i + 1) + " should have all details (vehicle, location, price, dates)");
                    logger.info("All required details are present in requirement #" + (i + 1));
                }
            }

            logger.info("SUCCESS: TC_038_verifyDemandFeedList passed successfully!");

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC_038 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }


    @Test
    public void TC_039_verifySendOfferButtonFunctionality() {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC_039_verifySendOfferButtonFunctionality");
        logger.info("=========================================================");

        try {
            // Step 1: Verify dashboard is loaded
            Assert.assertTrue(vendorDashboard.vendorDashboardMessage(),
                    "Vendor Dashboard should be displayed");
            logger.info("Vendor Dashboard loaded successfully");

            // Step 2: Scroll to Live Requirements section
            vendorDashboard.scrollToLiveRequirements();
            logger.info("Scrolled to Live requirements section");

            Thread.sleep(1000); // Wait for 1 second to ensure the section is in view

            // Step 3: Click on the first "Send Offer" button
            vendorDashboard.clickFirstSendOfferButton();
            logger.info("Clicked on the first 'Send Offer' button");

            Thread.sleep(2000);

            // Step 4: Verify that the offer form/modal is displayed
            Assert.assertTrue(vendorDashboard.isOfferFormDisplayed(),
                    "Offer form/modal should be displayed after clicking 'Send Offer'");
            logger.info("Offer form/modal is displayed successfully");

            logger.info("SUCCESS: TC_039_verifySendOfferButtonFunctionality passed successfully!");

        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC_039 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}