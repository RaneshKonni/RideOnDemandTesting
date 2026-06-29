package TestCases;

import PageObjects.AuthPage;
import PageObjects.VendorDashboardPage;
import PageObjects.VendorProfilePage;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TS013 extends BaseClass {

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
    public void TC_045_verifySignOut() {
        VendorDashboardPage dashboard = new VendorDashboardPage(driver);
        dashboard.navigateToProfile();

        VendorProfilePage profilePage = new VendorProfilePage(driver);
        profilePage.waitForProfileLoad();

        // 1. Perform Sign Out
        profilePage.clickSignOut();

        // 2. Verification
        // Use a wait to ensure the URL has changed or the login form has appeared
        // Replace "login" with the actual keyword in your URL or page title
        boolean isRedirected = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("auth"));

        Assert.assertTrue(isRedirected, "Sign out failed: User was not redirected to the login page.");

        System.out.println("✅ Successfully signed out and redirected to login page.");
    }
}