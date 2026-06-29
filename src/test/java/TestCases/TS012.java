package TestCases;

import PageObjects.AuthPage;
import PageObjects.VendorDashboardPage;
import PageObjects.VendorProfilePage;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TS012 extends BaseClass {

    AuthPage auth;
    VendorDashboardPage vendorDashboardPage;

    private static final String VENDOR_EMAIL = "vm@gmail.com";
    private static final String VENDOR_PASSWORD = "12345678";

    @BeforeClass
    public void classSetup() throws InterruptedException {
        auth = new AuthPage(driver);
        auth.loginAsVendor(VENDOR_EMAIL, VENDOR_PASSWORD);
        vendorDashboardPage = new VendorDashboardPage(driver);

        // Initial dashboard verification
        if (!vendorDashboardPage.vendorDashboardMessage()) {
            auth.loginAsVendor(VENDOR_EMAIL, VENDOR_PASSWORD);
        }
    }


    @Test
    public void TC_044_verifyMailMetrics() {
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

        System.out.println("✅ Mail metrics verified: " + expectedTotal + " items, " + expectedUnread + " unread.");
    }
}