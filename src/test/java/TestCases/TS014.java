package TestCases;

import PageObjects.AdminDashboardPage;
import PageObjects.AdminProfilePage;
import PageObjects.AuthPage;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TS014 extends BaseClass {

    AuthPage auth;
    AdminDashboardPage adminDashboardPage;

    private static final String ADMIN_EMAIL = "t1@gmail.com";
    private static final String ADMIN_PASSWORD = "11111111";

    @BeforeClass
    public void classSetup() throws InterruptedException {
        auth = new AuthPage(driver);
        auth.loginAsAdmin(ADMIN_EMAIL, ADMIN_PASSWORD);
        adminDashboardPage = new AdminDashboardPage(driver);
    }

    @Test
    public void TC_046_VerifyMainHeaderAndLayoutRendering() {
        Assert.assertTrue(adminDashboardPage.adminDashboardMessage(), "Admin Dashboard header is not displayed.");
        Assert.assertTrue(adminDashboardPage.isLogoVisible(), "Corporate logo is missing.");
        Assert.assertTrue(adminDashboardPage.isPerformanceOverviewTitleDisplayed(), "Performance Overview title is missing.");
    }

    @Test
    public void TC_047_VerifyDashboardBrowserRefreshBehavior() {
        driver.navigate().refresh();
        Assert.assertTrue(adminDashboardPage.adminDashboardMessage(), "Dashboard header failed to render after refresh.");
        Assert.assertTrue(adminDashboardPage.isDayFilterActive(), "Dashboard did not reset or maintain default 'Day' filter.");
    }

    @Test
    public void TC_048_VerifyDefaultActiveTimeFilter() {
        Assert.assertTrue(adminDashboardPage.areAllFiltersVisible(), "All filters (Day, Month, Year) should be visible.");
        Assert.assertTrue(adminDashboardPage.isDayFilterActive(), "'Day' filter should be active by default.");
    }

    @Test
    public void TC_049_VerifyMonthYearFilterInteraction() {
        // Test Month Filter
        adminDashboardPage.clickMonthFilter();
        Assert.assertTrue(adminDashboardPage.isMonthFilterActive(), "Month filter failed to activate.");

        // Test Year Filter
        adminDashboardPage.clickYearFilter();
        Assert.assertTrue(adminDashboardPage.isYearFilterActive(), "Year filter failed to activate.");
    }

    @Test
    public void TC_050_VerifyProfileButtonFunctionality() {
        adminDashboardPage.clickAdminProfile();
        AdminProfilePage profilePage = new AdminProfilePage(driver);
        Assert.assertTrue(profilePage.isProfilePageDisplayed(), "Failed to navigate to Admin Profile Page.");
    }
}