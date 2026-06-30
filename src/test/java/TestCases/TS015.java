package TestCases;

import PageObjects.AdminDashboardPage;
import PageObjects.AuthPage;
import TestBase.BaseClass;
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
        auth = new AuthPage(driver);
        auth.loginAsAdmin(ADMIN_EMAIL, ADMIN_PASSWORD);
        adminDashboardPage = new AdminDashboardPage(driver);

        // Initial dashboard verification
        if (!adminDashboardPage.adminDashboardMessage()) {
            auth.loginAsAdmin(ADMIN_EMAIL, ADMIN_PASSWORD);
        }
    }

    @Test
    public void TC_051_VerifyPendingShopMetadataRenderingAccuracy() {
        // Read text context validation
        Assert.assertTrue(adminDashboardPage.isShopVisible("sKzUy"),
                "Pending item row card failed to render shop title 'sKzUy' or its metadata.");
        System.out.println("✅ Successfully verified pending shop metadata rendering.");
    }

    @Test(dependsOnMethods = {"TC_051_VerifyPendingShopMetadataRenderingAccuracy"})
    public void TC_052_VerifyApproveActionExecution() {
        int initialCount = adminDashboardPage.getVerifiedShopsCount();
        String targetShop = "sKzUy";

        // Execute Action
        adminDashboardPage.approveShop(targetShop);

        // Assert removal from pending view list
        Assert.assertFalse(adminDashboardPage.isShopVisible(targetShop),
                "Target shop card was not cleared from the pending list after clicking Approve.");

        // Assert dynamic count verification
        int updatedCount = adminDashboardPage.getVerifiedShopsCount();
        Assert.assertEquals(updatedCount, initialCount + 1,
                "Dashboard 'Verified shops' count metrics did not increment by 1.");
        System.out.println("✅ Successfully executed and verified Shop Approval.");
    }

    @Test(dependsOnMethods = {"TC_052_VerifyApproveActionExecution"})
    public void TC_053_VerifyRejectActionExecution() {
        int initialCount = adminDashboardPage.getVerifiedShopsCount();
        // Fallback to the next unique test element from your source payload
        String targetShop = "dohOK";

        // Execute Action
        adminDashboardPage.rejectShop(targetShop);

        // Assert removal from pending view list
        Assert.assertFalse(adminDashboardPage.isShopVisible(targetShop),
                "Target shop card was not cleared from the pending list after clicking Reject.");

        // Assert count metric isolation
        int updatedCount = adminDashboardPage.getVerifiedShopsCount();
        Assert.assertEquals(updatedCount, initialCount,
                "Dashboard 'Verified shops' count changed unexpectedly after a rejection action.");
        System.out.println("✅ Successfully executed and verified Shop Rejection.");
    }
}