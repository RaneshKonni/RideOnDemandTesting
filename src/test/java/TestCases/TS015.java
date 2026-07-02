package TestCases;

import PageObjects.AdminDashboardPage;
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
        logger.info("Initializing TS015: Admin Dashboard - Shop Approvals Management");

        boolean isLoggedIn = loginUser(Role.ADMIN, ADMIN_EMAIL, ADMIN_PASSWORD);
        Assert.assertTrue(isLoggedIn, "Precondition Failed: Admin user could not log in.");

        adminDashboardPage = new AdminDashboardPage(driver);
    }

    @Test(priority = 1)
    public void TC_051_VerifyPendingShopMetadataRenderingAccuracy() {
        logger.info("Starting TC_051: Verifying pending shop metadata rendering");

        Assert.assertTrue(adminDashboardPage.hasPendingShops(), "No pending shop items found in the dashboard.");

        String firstShop = adminDashboardPage.getFirstShopName();
        Assert.assertFalse(firstShop.isEmpty(), "The first pending shop item did not contain a valid shop title.");
        logger.info("Successfully verified pending shop metadata rendering. Target first shop: " + firstShop);
    }

    @Test(priority = 2, dependsOnMethods = {"TC_051_VerifyPendingShopMetadataRenderingAccuracy"})
    public void TC_052_VerifyApproveActionExecution() {
        logger.info("Starting TC_052: Verifying 'Approve' action execution");

        int initialCount = adminDashboardPage.getVerifiedShopsCount();
        String targetShop = adminDashboardPage.getFirstShopName();

        logger.info("Approving shop: " + targetShop + " | Initial verified count: " + initialCount);
        adminDashboardPage.approveFirstShop();

        // 1. Verify UI State (Queue)
        boolean disappeared = adminDashboardPage.waitForShopToDisappear(targetShop, 20);
        Assert.assertTrue(disappeared, "Target shop '" + targetShop + "' was not cleared from the pending list after clicking Approve.");

        // Explicitly refresh the page to force the Angular frontend to fetch the latest metrics
        logger.info("Refreshing page to fetch updated dashboard metrics.");
        adminDashboardPage.refreshPage();

        // 2. Verify Metrics State (Graph/Counters)
        boolean matched = adminDashboardPage.waitForVerifiedShopsCountToBe(initialCount + 1, 20);
        int updatedCount = adminDashboardPage.getVerifiedShopsCount();

        Assert.assertTrue(matched, "Dashboard 'Verified shops' count did not reach expected value within timeout.");
        Assert.assertEquals(updatedCount, initialCount + 1, "Dashboard 'Verified shops' count metric did not increment by 1.");
    }

    @Test(priority = 3, dependsOnMethods = {"TC_052_VerifyApproveActionExecution"})
    public void TC_053_VerifyRejectActionExecution() {
        logger.info("Starting TC_053: Verifying 'Reject' action execution");

        Assert.assertTrue(adminDashboardPage.hasPendingShops(), "No pending shops remaining to test rejection.");

        int initialCount = adminDashboardPage.getVerifiedShopsCount();
        String targetShop = adminDashboardPage.getFirstShopName();

        logger.info("Rejecting shop: " + targetShop + " | Initial verified count: " + initialCount);
        adminDashboardPage.rejectFirstShop();

        // 1. Verify UI State (Queue)
        boolean disappearedReject = adminDashboardPage.waitForShopToDisappear(targetShop, 20);
        if (!disappearedReject) {
            boolean actionsEmpty = adminDashboardPage.isItemActionsEmpty(targetShop);
            Assert.assertTrue(actionsEmpty, "Target shop was not cleared and actions are still present after clicking Reject.");
        }

        // Explicitly refresh the page to ensure metrics sync
        logger.info("Refreshing page to confirm dashboard metrics remain stable.");
        adminDashboardPage.refreshPage();

        // 2. Verify Metrics State (Graph/Counters)
        boolean matchedReject = adminDashboardPage.waitForVerifiedShopsCountToBe(initialCount, 15);
        int updatedCount = adminDashboardPage.getVerifiedShopsCount();

        Assert.assertTrue(matchedReject, "Dashboard 'Verified shops' count did not stabilize within timeout.");
        Assert.assertEquals(updatedCount, initialCount, "Dashboard 'Verified shops' count changed unexpectedly after a rejection.");
    }
}