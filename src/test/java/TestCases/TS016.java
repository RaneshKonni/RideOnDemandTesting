package TestCases;

import PageObjects.AdminDashboardPage;
import PageObjects.AdminProfilePage;
import PageObjects.AuthPage;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TS016 extends BaseClass {

    AuthPage auth;
    AdminDashboardPage adminDashboardPage;
    AdminProfilePage adminProfilePage;

    private static final String ADMIN_EMAIL = "t1@gmail.com";
    private static final String ADMIN_PASSWORD = "11111111";

    @BeforeClass
    public void classSetup() throws InterruptedException {
        auth = new AuthPage(driver);
        adminDashboardPage = new AdminDashboardPage(driver);
        adminProfilePage = new AdminProfilePage(driver);

        // 1. Log in as admin
        auth.loginAsAdmin(ADMIN_EMAIL, ADMIN_PASSWORD);
        Assert.assertTrue(adminDashboardPage.adminDashboardMessage(), "Admin failed to log in.");

        // 2. Navigate to Admin Profile Page
        adminDashboardPage.clickAdminProfile();
        Assert.assertTrue(adminProfilePage.isProfilePageDisplayed(), "Failed to navigate to Admin Profile Page.");
        adminProfilePage.waitForProfileLoad();
    }

    @Test
    public void TC_054_VerifyProfileInformationDisplayDetails() {
        System.out.println("[TEST] Verifying profile visual elements and data boxes...");

        // 1. Verify Avatar and Pills
        Assert.assertTrue(adminProfilePage.isAvatarDisplayed(), "Circular avatar is not displayed on the profile page.");
        Assert.assertTrue(adminProfilePage.areStatusPillsDisplayed(), "Account status info pills are missing below the avatar.");

        // 2. Verify Data Boxes are populated
        String fullName = adminProfilePage.getProfileFieldValue("Full name");
        String email = adminProfilePage.getProfileFieldValue("Email");
        String mobile = adminProfilePage.getProfileFieldValue("Mobile");
        String city = adminProfilePage.getProfileFieldValue("City");
        String role = adminProfilePage.getProfileFieldValue("Role");

        Assert.assertFalse(fullName.isEmpty(), "Full name field is empty.");
        Assert.assertFalse(email.isEmpty(), "Email field is empty.");
        Assert.assertFalse(mobile.isEmpty(), "Mobile field is empty.");
        Assert.assertFalse(city.isEmpty(), "City field is empty.");
        Assert.assertFalse(role.isEmpty(), "Role field is empty.");

        // Additional validation: Ensure the loaded email matches the authenticated user
        Assert.assertEquals(email, ADMIN_EMAIL, "Profile email does not match the logged-in admin email.");

        System.out.println("✅ TC_054 Passed: Profile details rendered correctly.");
    }

    @Test(dependsOnMethods = {"TC_054_VerifyProfileInformationDisplayDetails"})
    public void TC_055_VerifyProfileViewBrowserRefreshBehavior() {
        System.out.println("[TEST] Executing browser refresh on Admin Profile Page...");

        // 1. Capture data before refresh
        String preRefreshEmail = adminProfilePage.getProfileFieldValue("Email");

        // 2. Trigger hard refresh
        driver.navigate().refresh();

        // Wait for DOM to rebuild
        Assert.assertTrue(adminProfilePage.isProfilePageDisplayed(), "Admin Profile failed to load after browser refresh.");
        adminProfilePage.waitForProfileLoad();

        // 3. Verify data persistence and session retention
        String postRefreshEmail = adminProfilePage.getProfileFieldValue("Email");
        Assert.assertFalse(postRefreshEmail.isEmpty(), "Profile data reset to empty after refresh.");
        Assert.assertEquals(postRefreshEmail, preRefreshEmail, "Profile data was lost or corrupted after browser refresh.");

        System.out.println("✅ TC_055 Passed: Profile state persisted post-refresh.");
    }

    @Test(dependsOnMethods = {"TC_055_VerifyProfileViewBrowserRefreshBehavior"})
    public void TC_056_VerifyAccountSignOutFunctionTermination() {
        System.out.println("[TEST] Verifying Sign Out functionality...");

        // 1. Click Sign out
        adminProfilePage.clickSignOut();

        // 2. Verify redirect back to Auth/Login layout
        // Assuming your AuthPage has a method like isLoginScreenVisible() or we can check the URL.
        // Replace this wait with your AuthPage's specific verification method if needed.
        boolean redirected = false;
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            // Checks if URL contains 'login' or 'auth' indicating session termination
            redirected = wait.until(d -> d.getCurrentUrl().toLowerCase().contains("login") || d.getCurrentUrl().toLowerCase().contains("auth"));
        } catch (Exception e) {
            redirected = false;
        }

        Assert.assertTrue(redirected, "User was not redirected to the login/auth page after signing out. Current URL: " + driver.getCurrentUrl());

        // 3. Optional Strict check: Verify trying to go back to dashboard fails (verifies token is actually cleared)
        driver.navigate().back();
        Assert.assertFalse(driver.getCurrentUrl().toLowerCase().contains("dashboard"), "Session token was not completely cleared. User could navigate back to protected route.");

        System.out.println("✅ TC_056 Passed: Session terminated and user redirected.");
    }
}