package TestCases;

import PageObjects.AdminDashboardPage;
import PageObjects.AdminProfilePage;
import TestBase.BaseClass;
import mapper.Role;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS015 extends BaseClass {

    private AdminDashboardPage adminDashboardPage;

    private static final String ADMIN_EMAIL = "t1@gmail.com";
    private static final String ADMIN_PASSWORD = "11111111";

    @BeforeMethod
    public void classSetup() {
        logger.info("Initializing TS015: Admin Dashboard Core Functionality");

        boolean isLoggedIn = loginUser(Role.ADMIN, ADMIN_EMAIL, ADMIN_PASSWORD);
        Assert.assertTrue(isLoggedIn, "Precondition Failed: Admin user could not log in.");

        adminDashboardPage = new AdminDashboardPage(driver);
    }

    @Test(priority = 1)
    public void TC_046_VerifyMainHeaderAndLayoutRendering() {
        logger.info("Starting TC_046: Verifying main header and layout rendering");

        Assert.assertTrue(adminDashboardPage.adminDashboardMessage(), "Admin Dashboard header is not displayed.");
        Assert.assertTrue(adminDashboardPage.isLogoVisible(), "Corporate logo is missing.");
        Assert.assertTrue(adminDashboardPage.isPerformanceOverviewTitleDisplayed(), "Performance Overview title is missing.");
    }

    @Test(priority = 2)
    public void TC_047_VerifyDashboardBrowserRefreshBehavior() {
        logger.info("Starting TC_047: Verifying dashboard browser refresh behavior");

        adminDashboardPage.refreshPage();

        Assert.assertTrue(adminDashboardPage.adminDashboardMessage(), "Dashboard header failed to render after refresh.");
        Assert.assertTrue(adminDashboardPage.isDayFilterActive(), "Dashboard did not reset or maintain default 'Day' filter.");
    }

    @Test(priority = 3)
    public void TC_048_VerifyDefaultActiveTimeFilter() {
        logger.info("Starting TC_048: Verifying default active time filter");

        Assert.assertTrue(adminDashboardPage.areAllFiltersVisible(), "All filters (Day, Month, Year) should be visible.");
        Assert.assertTrue(adminDashboardPage.isDayFilterActive(), "'Day' filter should be active by default.");
    }

    @Test(priority = 4)
    public void TC_049_VerifyMonthYearFilterInteraction() {
        logger.info("Starting TC_049: Verifying month and year filter interactions");

        // Test Month Filter
        adminDashboardPage.clickMonthFilter();
        Assert.assertTrue(adminDashboardPage.isMonthFilterActive(), "Month filter failed to activate.");

        // Test Year Filter
        adminDashboardPage.clickYearFilter();
        Assert.assertTrue(adminDashboardPage.isYearFilterActive(), "Year filter failed to activate.");
    }

    @Test(priority = 5)
    public void TC_050_VerifyProfileButtonFunctionality() {
        logger.info("Starting TC_050: Verifying profile button functionality and navigation");

        adminDashboardPage.clickAdminProfile();

        AdminProfilePage profilePage = new AdminProfilePage(driver);
        Assert.assertTrue(profilePage.isProfilePageDisplayed(), "Failed to navigate to Admin Profile Page.");
    }
}