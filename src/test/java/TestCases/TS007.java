package TestCases;

import PageObjects.AuthPage;
import PageObjects.VendorDashboardPage;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TS007 extends BaseClass {

    @BeforeMethod
    public void setup() {
        loginUser("Vendor", "vm@gmail.com", "12345678");
    }

    @Test
    public void verifyDashboardMetricsDisplay() {

        try{
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

            System.out.println("All dashboard metrics are displayed correctly for the vendor.");

        }catch(Exception e){
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}

