package TestCases;

import PageObjects.AdminDashboardPage;
import PageObjects.AdminProfilePage;
import TestBase.BaseClass;
import mapper.Role;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS016 extends BaseClass {

    private AdminDashboardPage adminDashboardPage;
    private AdminProfilePage adminProfilePage;

    private static final String ADMIN_EMAIL = "t1@gmail.com";
    private static final String ADMIN_PASSWORD = "11111111";

    @BeforeMethod
    public void classSetup() {
        logger.info("Initializing TS016: Admin Profile Management & Session Termination");

        // 1. Log in and hard fail if the precondition is not met
        boolean isLoggedIn = loginUser(Role.ADMIN, ADMIN_EMAIL, ADMIN_PASSWORD);
        Assert.assertTrue(isLoggedIn, "Precondition Failed: Admin user could not log in.");

        adminDashboardPage = new AdminDashboardPage(driver);
        adminProfilePage = new AdminProfilePage(driver);

        // 2. Navigate to Admin Profile Page
        logger.info("Navigating to Admin Profile Page");
        adminDashboardPage.clickAdminProfile();

        Assert.assertTrue(adminProfilePage.isProfilePageDisplayed(), "Failed to navigate to Admin Profile Page.");
        adminProfilePage.waitForProfileLoad();
    }

    @Test(priority = 1)
    public void TC_054_VerifyProfileInformationDisplayDetails() {
        logger.info("Starting TC_054: Verifying profile visual elements and data boxes");

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

        // Additional validation
        Assert.assertEquals(email, ADMIN_EMAIL, "Profile email does not match the logged-in admin email.");
    }

    @Test(priority = 2, dependsOnMethods = {"TC_054_VerifyProfileInformationDisplayDetails"})
    public void TC_055_VerifyProfileViewBrowserRefreshBehavior() {
        logger.info("Starting TC_055: Verifying browser refresh behavior on Profile Page");

        // 1. Capture data before refresh
        String preRefreshEmail = adminProfilePage.getProfileFieldValue("Email");

        // 2. Trigger hard refresh using BasePage utility
        adminProfilePage.refreshPage();
        adminProfilePage.waitForProfileLoad();

        Assert.assertTrue(adminProfilePage.isProfilePageDisplayed(), "Admin Profile failed to load after browser refresh.");

        // 3. Verify data persistence and session retention
        String postRefreshEmail = adminProfilePage.getProfileFieldValue("Email");
        Assert.assertFalse(postRefreshEmail.isEmpty(), "Profile data reset to empty after refresh.");
        Assert.assertEquals(postRefreshEmail, preRefreshEmail, "Profile data was lost or corrupted after browser refresh.");
    }

    @Test(priority = 3, dependsOnMethods = {"TC_055_VerifyProfileViewBrowserRefreshBehavior"})
    public void TC_056_VerifyAccountSignOutFunctionTermination() {
        logger.info("Starting TC_056: Verifying account sign out functionality");

        adminProfilePage.clickSignOut();

        // Use the inherited waitForUrlToContain method from BasePage to handle the Angular routing delay
        boolean redirectedToAuth = adminProfilePage.waitForUrlToContain("auth") || adminProfilePage.waitForUrlToContain("login");

        Assert.assertTrue(redirectedToAuth, "User was not redirected to the authentication page after sign out.");
    }
}