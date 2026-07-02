package TestCases;

import PageObjects.AuthPage;
import PageObjects.VendorDashboardPage;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TS008 extends BaseClass  {

    VendorDashboardPage vendorDashboard;

    @BeforeMethod
    public void setup() {
        loginUser("Vendor", "vm@gmail.com", "12345678");
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
        try {
            System.out.println("========== TC_038: Verify Demand Feed List ==========");

            // Step 1: Verify dashboard is loaded
            Assert.assertTrue(vendorDashboard.vendorDashboardMessage(),
                    "Vendor Dashboard should be displayed");
            System.out.println("✓ Vendor Dashboard loaded successfully");

            // Step 2: Verify Live Requirements heading is displayed
            Assert.assertTrue(vendorDashboard.isLiveRequirementsHeadingDisplayed(),
                    "Live requirements heading should be displayed");
            System.out.println("✓ Live requirements heading is visible");

            // Step 3: Scroll to Live Requirements section
            vendorDashboard.scrollToLiveRequirements();
            System.out.println("✓ Scrolled to Live requirements section");

            Thread.sleep(3000); // Wait for 1 second to ensure the section is in view

            // Step 4: Verify demand feed list is displayed
            Assert.assertTrue(vendorDashboard.isDemandFeedListDisplayed(),
                    "Demand feed list should be displayed");
            System.out.println("✓ Demand feed list container is visible");

            // Step 5: Verify list has requirements
            int itemCount = vendorDashboard.getRequirementItemsCount();
            Assert.assertTrue(itemCount > 0,
                    "At least one requirement item should be displayed in the list");
            System.out.println("✓ Found " + itemCount + " requirement items in the list");

            // Step 6: Verify all requirements have all details (Car, Bike, location, price, dates, notes)
            if (itemCount > 0) {
                // Verify each requirement has all required details
                for (int i = 0; i < itemCount; i++) {
                    System.out.println("\n--- Verifying Requirement #" + (i + 1) + " ---");
                    
                    String vehicleType = vendorDashboard.getRequirementVehicleType(i);
                    String location = vendorDashboard.getRequirementLocation(i);
                    String price = vendorDashboard.getRequirementPrice(i);
                    String dates = vendorDashboard.getRequirementDates(i);
                    String notes = vendorDashboard.getRequirementNotes(i);

                    // Verify each detail is not empty
                    Assert.assertFalse(vehicleType.isEmpty(),
                            "Vehicle type should be displayed for requirement #" + (i + 1) + " (e.g., Car, Bike)");
                    System.out.println("✓ Vehicle Type: " + vehicleType);

                    Assert.assertFalse(location.isEmpty(),
                            "Location should be displayed in requirement #" + (i + 1));
                    System.out.println("✓ Location: " + location);

                    Assert.assertFalse(price.isEmpty(),
                            "Price should be displayed in requirement #" + (i + 1));
                    System.out.println("✓ Price: " + price);

                    Assert.assertFalse(dates.isEmpty(),
                            "Dates should be displayed in requirement #" + (i + 1));
                    System.out.println("✓ Dates: " + dates);

                    // Notes may be optional, but verify it can be retrieved
                    if (!notes.isEmpty()) {
                        System.out.println("✓ Notes: " + notes);
                    } else {
                        System.out.println("ℹ Notes: Not provided for requirement #" + (i + 1));
                    }

                    // Verify complete requirement details
                    Assert.assertTrue(vendorDashboard.hasRequirementDetails(i),
                            "Requirement #" + (i + 1) + " should have all details (vehicle, location, price, dates)");
                    System.out.println("✓ All required details are present in requirement #" + (i + 1));
                }
            }

            System.out.println("========== TC_038: Test PASSED ==========");

        } catch (Exception e) {
            System.out.println("❌ Test FAILED: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed: " + e.getMessage());
        }
    }



    @Test
    public  void TC_039_verifySendOfferButtonFunctionality() {

        System.out.println("moving to TC_039");
        try {
            System.out.println("========== TC_039: Verify Send Offer Button Functionality ==========");

            // Step 1: Verify dashboard is loaded
            Assert.assertTrue(vendorDashboard.vendorDashboardMessage(),
                    "Vendor Dashboard should be displayed");
            System.out.println("✓ Vendor Dashboard loaded successfully");
//
//            // Step 2: Scroll to Live Requirements section
            vendorDashboard.scrollToLiveRequirements();
            System.out.println("✓ Scrolled to Live requirements section");



            Thread.sleep(1000); // Wait for 3 seconds to ensure the section is in view

            // Step 3: Click on the first "Send Offer" button
            vendorDashboard.clickFirstSendOfferButton();
            System.out.println("✓ Clicked on the first 'Send Offer' button");

            Thread.sleep(2000);

            // Step 4: Verify that the offer form/modal is displayed
            Assert.assertTrue(vendorDashboard.isOfferFormDisplayed(),
                    "Offer form/modal should be displayed after clicking 'Send Offer'");
            System.out.println("✓ Offer form/modal is displayed successfully");

            System.out.println("========== TC_039: Test PASSED ==========");

        } catch (Exception e) {
            System.out.println("❌ Test FAILED: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}
