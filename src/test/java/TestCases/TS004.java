package TestCases;

import PageObjects.*;
import TestBase.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS004 extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TS004.class);

    AuthPage authPage;
    CustomerDashboardPage customerDashboard;
    CustomerProfilePage customerProfile;
    CustomerPostRequirementPage postRequirementPage;
    CustomerOffersReceivedPage offersReceivedPage;

    @BeforeMethod
    public void loginBeforeTest() {

        try {

            driver.get(p.getProperty("appUrl"));
            Thread.sleep(2000);

            logger.info("Logging in as customer.");

            authPage = new AuthPage(driver);
            authPage.loginActivity();

            authPage.setTfEmail(p.getProperty("testCustomerEmail"));
            authPage.setTfPassword(p.getProperty("testCustomerPassword"));
            authPage.clickBtnSubmit();

            Thread.sleep(3000);

            customerDashboard = new CustomerDashboardPage(driver);

            Assert.assertTrue(customerDashboard.isWelcomeMessageDisplayed(),
                    "Login failed.");

            logger.info("Customer login successful.");

        } catch (Exception e) {

            logger.error("Login setup failed.", e);
            Assert.fail("Login setup failed : " + e.getMessage());
        }
    }

    /**
     * TC_022 - Verify profile button is navigating to the expected page when clicked
     * Prerequisites: Website is opened, Customer is authenticated, Customer is on dashboard
     * Expected: Customer should be navigated to the profile page
     */
    @Test(priority = 1)
    public void verifyProfileButtonNavigationFromDashboard(){
        try{

            logger.info("Executing TC_022");
            customerDashboard = new CustomerDashboardPage(driver);

            // Verify profile button is displayed
            Assert.assertTrue(customerDashboard.isProfileButtonDisplayed(),
                    "Profile button should be displayed on dashboard");

            // Click profile button
            customerDashboard.clickCustomerProfile();

            // Wait for profile page to load

            customerProfile = new CustomerProfilePage(driver);

            // Verify we are on profile page
            Assert.assertTrue(customerProfile.isProfilePageDisplayed(),
                    "Should navigate to profile page");


            logger.info("TC_022 completed successfully.");

        }catch(Exception e){
            logger.error("TC_022 FAILED: {}", e.getMessage());
            Assert.fail("TC_022 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_023 - Verify post requirement button is navigating to the expected page when clicked
     * Prerequisites: Website is opened, Customer is authenticated, Customer is on dashboard
     * Expected: Customer should be navigated to the post requirement page
     */
    @Test(priority = 2)
    public void verifyPostRequirementButtonNavigation(){
        try{

            logger.info("Executing TC_023");
            Thread.sleep(3000);
            customerDashboard = new CustomerDashboardPage(driver);

            // Verify post requirement button is displayed
            Assert.assertTrue(customerDashboard.isPostRequirementButtonDisplayed(),
                    "Post Requirement button should be displayed on dashboard");

            Thread.sleep(2000);
            // Click post requirement button
            customerDashboard.clickPostRequirement();

            // Wait for post requirement page to load
            Thread.sleep(2000);
            postRequirementPage = new CustomerPostRequirementPage(driver);

            // Verify we are on post requirement page
            Assert.assertTrue(postRequirementPage.isPostRequirementPageDisplayed(),
                    "Should navigate to post requirement page");

            postRequirementPage.clickBackToProfile();


            Thread.sleep(2000);
            customerProfile = new CustomerProfilePage(driver);
            Thread.sleep(3000);
            customerProfile.clickSignout();


            logger.info("TC_023 completed successfully.");


        }catch(Exception e){
            logger.error("TC_023 FAILED: {}", e.getMessage());
            Assert.fail("TC_023 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_024 - Verify accepted requirement is navigating to the Offers Received page
     * Prerequisites: Website is opened, Customer is authenticated, Customer is on dashboard,
     * at least one accepted requirement is available
     * Expected: Customer should be navigated to the Offers Received page and
     * essential offer details should be displayed
     */
    @Test(priority = 3)
    public void verifyAcceptedRequirementNavigation() {

        try {
            logger.info("Executing TC_024");
            customerDashboard = new CustomerDashboardPage(driver);
            Thread.sleep(5000);
            // Verify Accepted requirement is displayed
            Assert.assertTrue(
                    customerDashboard.isAcceptedRequirementDisplayed(),
                    "Accepted requirement should be displayed on dashboard"
            );

            Thread.sleep(2000);

            // Click Accepted requirement
            customerDashboard.clickAcceptedRequirement();

            Thread.sleep(3000);

            offersReceivedPage = new CustomerOffersReceivedPage(driver);

            // Verify navigation
            Assert.assertTrue(
                    driver.getCurrentUrl().contains("/customer/offers/"),
                    "Should navigate to Offers Received page"
            );

            // Verify page heading
            Assert.assertTrue(
                    offersReceivedPage.isOffersReceivedHeadingDisplayed(),
                    "Offers Received heading should be displayed"
            );

            // Verify logo
            Assert.assertTrue(
                    offersReceivedPage.isLogoDisplayed(),
                    "Logo should be displayed"
            );

            // Verify requirement id
            Assert.assertFalse(
                    offersReceivedPage.getRequirementId().trim().isEmpty(),
                    "Requirement ID should be displayed."
            );
            // Verify total offers
            Assert.assertFalse(
                    offersReceivedPage.getTotalOffers().trim().isEmpty(),
                    "Total offers should be displayed"
            );

            // Verify at least one offer exists
            Assert.assertTrue(
                    offersReceivedPage.getOffersCount() > 0,
                    "At least one offer should be displayed"
            );

            // Verify price
            Assert.assertFalse(
                    offersReceivedPage.getPrice().trim().isEmpty(),
                    "Price should be displayed"
            );

            // Verify offer status
            Assert.assertTrue(
                    offersReceivedPage.isStatusDisplayed(),
                    "Offer status should be displayed"
            );

            // Verify WhatsApp link
            Assert.assertTrue(
                    offersReceivedPage.isWhatsAppDisplayed(),
                    "WhatsApp link should be displayed"
            );

            // Verify Call link
            Assert.assertTrue(
                    offersReceivedPage.isCallDisplayed(),
                    "Call link should be displayed"
            );

            // Verify Accept/Reject button
            Assert.assertTrue(
                    offersReceivedPage.isAcceptRejectButtonDisplayed(),
                    "Accept/Reject button should be displayed"
            );

            logger.info("TC_024 completed successfully.");

        }
        catch (Exception e) {

            logger.error("TC_024 failed.", e);
            Assert.fail("TC_024 FAILED: " + e.getMessage());

        }
    }

    @AfterMethod
    public void logout() {

        try {

            customerProfile = new CustomerProfilePage(driver);

            if(customerProfile.isSignOutButtonDisplayed()) {
                customerProfile.clickSignout();
            }

        } catch (Exception ignored) {

        }

    }


}
