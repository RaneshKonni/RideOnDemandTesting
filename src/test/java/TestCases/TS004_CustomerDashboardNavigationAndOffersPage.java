package TestCases;

import PageObjects.*;
import TestBase.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS004_CustomerDashboardNavigationAndOffersPage extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TS004_CustomerDashboardNavigationAndOffersPage.class);

    AuthPage authPage;
    CustomerDashboardPage customerDashboard;
    CustomerProfilePage customerProfile;
    CustomerPostRequirementPage postRequirementPage;
    CustomerOffersReceivedPage offersReceivedPage;

    @BeforeMethod
    public void loginBeforeTest(){
        try{
//             Refresh page to ensure clean state

            Thread.sleep(3000);


            authPage = new AuthPage(driver);
            authPage.loginActivity();
            String email = p.getProperty("testCustomerEmail");
            String password = p.getProperty("testCustomerPassword");
            
            if(email != null && !email.trim().isEmpty() && password != null && !password.trim().isEmpty()){
                // Ensure login form is visible and ready
//                authPage.ensureLoginFormVisible();
                
                // Enter credentials and login
                authPage.setTfEmail(email);
                authPage.setTfPassword(password);
                authPage.clickBtnSubmit();
                
                // Wait for dashboard to be loaded
                Thread.sleep(3000);
                customerDashboard = new CustomerDashboardPage(driver);
                boolean dashboardLoaded = customerDashboard.isWelcomeMessageDisplayed();
                
                if(!dashboardLoaded){
                    logger.warn("Dashboard not visible after login. Test may fail.");
                    System.out.println("WARNING: Dashboard not visible after login. Test may fail.");
                }else{
                    logger.info("Login successful. Dashboard is visible.");
                    System.out.println("Login successful. Dashboard is visible.");
                }
            }else{
                logger.error("No test credentials found in config.properties. Tests will fail.");
                System.out.println("CRITICAL: No test credentials found in config.properties. Tests will fail.");
            }
        }catch(Exception e){
            logger.error("Login setup failed: {}", e.getMessage());
            System.out.println("Login setup failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * TC_022 - Verify profile button is navigating to the expected page when clicked
     * Prerequisites: Website is opened, Customer is authenticated, Customer is on dashboard
     * Expected: Customer should be navigated to the profile page
     */
    @Test
    public void verifyProfileButtonNavigationFromDashboard(){
        try{
            customerDashboard = new CustomerDashboardPage(driver);
            Thread.sleep(5000);
            // Verify profile button is displayed
            Assert.assertTrue(customerDashboard.isProfileButtonDisplayed(), 
                "Profile button should be displayed on dashboard");
            Thread.sleep(2000);
            // Click profile button
            customerDashboard.clickCustomerProfile();
            
            // Wait for profile page to load
            Thread.sleep(2000);
            customerProfile = new CustomerProfilePage(driver);
            
            // Verify we are on profile page
            Assert.assertTrue(customerProfile.isProfilePageDisplayed(), 
                "Should navigate to profile page");

            customerProfile.clickSignout();
            logger.info("TC_022 PASSED: Profile button navigates to profile page");
            System.out.println("TC_022 PASSED: Profile button navigates to profile page");
            
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
    @Test
    public void verifyPostRequirementButtonNavigation(){
        try{

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


            logger.info("TC_023 PASSED: Post Requirement button navigates to post requirement page");
            System.out.println("TC_023 PASSED: Post Requirement button navigates to post requirement page");
            
        }catch(Exception e){
            logger.error("TC_023 FAILED: {}", e.getMessage());
            Assert.fail("TC_023 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC - Verify accepted requirement is navigating to the Offers Received page
     * Prerequisites: Website is opened, Customer is authenticated, Customer is on dashboard,
     * at least one accepted requirement is available
     * Expected: Customer should be navigated to the Offers Received page and
     * essential offer details should be displayed
     */
    @Test
    public void verifyAcceptedRequirementNavigation() {

        try {
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
                    offersReceivedPage.getRequirementId().isEmpty(),
                    "Requirement ID should be displayed"
            );

            // Verify total offers
            Assert.assertFalse(
                    offersReceivedPage.getTotalOffers().isEmpty(),
                    "Total offers should be displayed"
            );

            // Verify at least one offer exists
            Assert.assertTrue(
                    offersReceivedPage.getOffersCount() > 0,
                    "At least one offer should be displayed"
            );

            // Verify price
            Assert.assertFalse(
                    offersReceivedPage.getPrice().isEmpty(),
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

            logger.info("TC PASSED: Accepted requirement navigates to Offers Received page");
            System.out.println("TC PASSED: Accepted requirement navigates to Offers Received page");

        }
        catch (Exception e) {

            logger.error("TC FAILED: {}", e.getMessage());
            Assert.fail("TC FAILED: " + e.getMessage());

        }
    }


}
