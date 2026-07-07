package TestCases;

import PageObjects.*;
import TestBase.BaseClass;
import mapper.Role;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS004 extends BaseClass {

    CustomerDashboardPage customerDashboard;
    CustomerProfilePage customerProfile;
    CustomerPostRequirementPage postRequirementPage;
    CustomerOffersReceivedPage offersReceivedPage;

    @BeforeMethod
    public void loginBeforeTest() {

        try {
            logger.info("Logging in as customer.");
            Assert.assertTrue(
                    loginUser(
                            Role.CUSTOMER,
                            properties.getProperty("testCustomerEmail"),
                            properties.getProperty("testCustomerPassword")
                    ),
                    "Customer login failed."
            );

            customerDashboard = new CustomerDashboardPage(driver);

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



            Assert.assertTrue(customerDashboard.isProfileButtonDisplayed(),
                    "Profile button should be displayed on dashboard");


            customerDashboard.clickCustomerProfile();



            customerProfile = new CustomerProfilePage(driver);


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


            // Verify post requirement button is displayed
            Assert.assertTrue(customerDashboard.isPostRequirementButtonDisplayed(),
                    "Post Requirement button should be displayed on dashboard");


            // Click post requirement button
            customerDashboard.clickPostRequirement();



            postRequirementPage = new CustomerPostRequirementPage(driver);

            // Verify we are on post requirement page
            Assert.assertTrue(postRequirementPage.isPostRequirementPageDisplayed(),
                    "Should navigate to post requirement page");

            postRequirementPage.clickBackToProfile();
            customerProfile = new CustomerProfilePage(driver);

            Assert.assertTrue(
                    customerProfile.isProfilePageDisplayed(),
                    "Should navigate back to profile page."
            );

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


            // Verify Accepted requirement is displayed
            Assert.assertTrue(
                    customerDashboard.isAcceptedRequirementDisplayed(),
                    "Accepted requirement should be displayed on dashboard"
            );



            // Click Accepted requirement
            customerDashboard.clickAcceptedRequirement();

            // Verify navigation
            Assert.assertTrue(
                    driver.getCurrentUrl().contains("/customer/offers/"),
                    "Should navigate to Offers Received page"
            );

            offersReceivedPage = new CustomerOffersReceivedPage(driver);

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
            logoutUser();
        } catch (Exception e) {
            logger.warn("Logout skipped",e);
        }

    }


}
