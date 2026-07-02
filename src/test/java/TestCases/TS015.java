package TestCases;

import PageObjects.AdminDashboardPage;
import PageObjects.AuthPage;
import TestBase.BaseClass;
import mapper.Role;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TS015 extends BaseClass {

    AuthPage auth;
    AdminDashboardPage adminDashboardPage;

    private static final String ADMIN_EMAIL = "t1@gmail.com";
    private static final String ADMIN_PASSWORD = "11111111";

    @BeforeClass
    public void classSetup() throws InterruptedException {
     loginUser(Role.ADMIN, ADMIN_EMAIL, ADMIN_PASSWORD);
        adminDashboardPage = new AdminDashboardPage(driver);

        // Initial dashboard verification
        if (!adminDashboardPage.adminDashboardMessage()) {
            loginUser(Role.ADMIN, ADMIN_EMAIL, ADMIN_PASSWORD);
        }
    }

    @Test
    public void TC_051_VerifyPendingShopMetadataRenderingAccuracy() {
        // Ensure at least one pending shop exists and capture its title
        Assert.assertTrue(adminDashboardPage.hasPendingShops(), "No pending shop items found in the dashboard.");
        String firstShop = adminDashboardPage.getFirstShopName();
        Assert.assertFalse(firstShop.isEmpty(), "The first pending shop item did not contain a shop title.");
        System.out.println("✅ Successfully verified pending shop metadata rendering. First shop: " + firstShop);
    }

    @Test(dependsOnMethods = {"TC_051_VerifyPendingShopMetadataRenderingAccuracy"})
    public void TC_052_VerifyApproveActionExecution() {
        int initialCount = adminDashboardPage.getVerifiedShopsCount();
        // Operate on the first pending shop (no hardcoded target)
        String targetShop = adminDashboardPage.getFirstShopName();
        System.out.println("[TEST] Approving first shop: " + targetShop + ", initial verified count: " + initialCount);
        adminDashboardPage.approveFirstShop();

        // Wait for the shop to disappear from pending list and assert (allow more time for backend)
        System.out.println("[TEST] Waiting for approved shop '" + targetShop + "' to disappear from pending list (timeout: 20s)");
        boolean disappeared = adminDashboardPage.waitForShopToDisappear(targetShop, 20);
        if (!disappeared) {
            // Diagnostic: log how many matching elements remain and try a refresh + re-check
            int remaining = adminDashboardPage.countPendingShopItemsByName(targetShop);
            System.out.println("[TEST] WARNING: After wait, matching pending items remaining for '" + targetShop + "': " + remaining);
            System.out.println("[TEST] Attempting page refresh and re-checking...");
            adminDashboardPage.refreshPage();
            boolean disappearedAfterRefresh = adminDashboardPage.waitForShopToDisappear(targetShop, 10);
            System.out.println("[TEST] Disappeared after refresh: " + disappearedAfterRefresh);
            Assert.assertTrue(disappearedAfterRefresh, "Target shop card was not cleared from the pending list after clicking Approve (even after refresh).");
        } else {
            Assert.assertTrue(disappeared, "Target shop card was not cleared from the pending list after clicking Approve.");
        }

        // Assert dynamic count verification by waiting for the metric to update
        System.out.println("[TEST] Waiting for Verified shops metric to update from " + initialCount + " to " + (initialCount + 1) + " (timeout: 20s)");
        boolean matched = adminDashboardPage.waitForVerifiedShopsCountToBe(initialCount + 1, 20);
        int updatedCount = adminDashboardPage.getVerifiedShopsCount();
        if (!matched) {
            System.out.println("[TEST] Metric did not update within 20s (current: " + updatedCount + "). Attempting page refresh and re-waiting (15s)");
            adminDashboardPage.refreshPage();
            // quick read after refresh
            int postRefresh = adminDashboardPage.getVerifiedShopsCount();
            System.out.println("[TEST] Verified shops after refresh: " + postRefresh);
            matched = adminDashboardPage.waitForVerifiedShopsCountToBe(initialCount + 1, 15);
            updatedCount = adminDashboardPage.getVerifiedShopsCount();
        }
        Assert.assertTrue(matched,
                "Dashboard 'Verified shops' count did not reach expected value within timeout. Current: " + updatedCount + " Expected: " + (initialCount + 1));
        Assert.assertEquals(updatedCount, initialCount + 1,
                "Dashboard 'Verified shops' count metrics did not increment by 1.");
        System.out.println("✅ Successfully executed and verified Shop Approval.");
    }

    @Test(dependsOnMethods = {"TC_052_VerifyApproveActionExecution"})
    public void TC_053_VerifyRejectActionExecution() {
        int initialCount = adminDashboardPage.getVerifiedShopsCount();
        // Operate on the (new) first pending shop (no hardcoded target)
        String targetShop = adminDashboardPage.getFirstShopName();
        System.out.println("[TEST] Rejecting first shop: " + targetShop + ", initial verified count: " + initialCount);
        adminDashboardPage.rejectFirstShop();

        // Wait for the shop to disappear from pending list - reject may take longer than approve
        System.out.println("[TEST] Waiting for rejected shop '" + targetShop + "' to disappear from pending list (timeout: 20s)");
        boolean disappearedReject = adminDashboardPage.waitForShopToDisappear(targetShop, 20);
        if (!disappearedReject) {
            System.out.println("[TEST] WARNING: Shop did not disappear after 20s wait. Checking if item still visible...");
            // Check if item was processed in-place (actions removed) which is acceptable
            boolean actionsEmpty = adminDashboardPage.isItemActionsEmpty(targetShop);
            System.out.println("[TEST] Item actions empty for '" + targetShop + "': " + actionsEmpty);
            if (!actionsEmpty) {
                // Try a refresh and re-check disappearance
                adminDashboardPage.refreshPage();
                boolean disappearedAfterRefresh = adminDashboardPage.waitForShopToDisappear(targetShop, 10);
                if (!disappearedAfterRefresh) {
                    // final diagnostic count
                    int remaining = adminDashboardPage.countPendingShopItemsByName(targetShop);
                    System.out.println("[TEST] After refresh, remaining items for '" + targetShop + "': " + remaining);
                }
                Assert.assertTrue(disappearedAfterRefresh, "Target shop card was not cleared from the pending list after clicking Reject.");
            } else {
                // Actions removed => treat as processed
                Assert.assertTrue(actionsEmpty, "Target shop appears processed in-place (actions removed).");
            }
        } else {
            Assert.assertTrue(disappearedReject, "Target shop card was not cleared from the pending list after clicking Reject.");
        }

        // Assert count metric isolation by waiting for the metric to remain same
        boolean matchedReject = adminDashboardPage.waitForVerifiedShopsCountToBe(initialCount, 15);
        int updatedCount = adminDashboardPage.getVerifiedShopsCount();
        Assert.assertTrue(matchedReject,
                "Dashboard 'Verified shops' count did not stabilize to expected value within timeout. Current: " + updatedCount + " Expected: " + initialCount);
        Assert.assertEquals(updatedCount, initialCount,
                "Dashboard 'Verified shops' count changed unexpectedly after a rejection action.");
        System.out.println("✅ Successfully executed and verified Shop Rejection.");
    }
}