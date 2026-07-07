package TestCases;

import PageObjects.*;

import TestBase.BaseClass;
import mapper.Role;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS005 extends BaseClass{

    CustomerDashboardPage customerDashboard;
    CustomerProfilePage customerProfile;

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

            Assert.assertTrue(
                    customerDashboard.isWelcomeMessageDisplayed(),
                    "Login failed."
            );

            customerDashboard.clickCustomerProfile();

            customerProfile = new CustomerProfilePage(driver);

            Assert.assertTrue(
                    customerProfile.isProfilePageDisplayed(),
                    "Profile page should be displayed."
            );

            logger.info("Customer login successful.");

        } catch (Exception e) {

            logger.error("Login setup failed.", e);
            Assert.fail("Login setup failed : " + e.getMessage());
        }
    }


    /**
     * TC_024 - Verify the text "Profile & Notifications" and the logo is visible on the page
     * Prerequisites: User logged in as customer
     * Expected: "Profile & Notifications" text and logo should be displayed
     */
    @Test(priority = 1)
    public void verifyProfileHeaderAndLogo(){
        try{
            logger.info("Executing TC_024");

            // Verify Profile & Notifications text
            Assert.assertTrue(customerProfile.isProfilePageDisplayed(),
                    "Profile & Notifications text should be displayed");

            // Verify logo
            Assert.assertTrue(customerProfile.isLogoDisplayed(),
                    "Logo should be displayed on profile page");

            logger.info("TC_024 completed successfully.");


        }catch(Exception e){
            logger.error("TC_024 FAILED: {}", e.getMessage());
            Assert.fail("TC_024 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_025 - Verify the customer section and account details has valid details and sign out button
     * Prerequisites: User is logged in as a customer
     * Expected: Account details should display email, mobile, city, and sign out button
     */
    @Test(priority = 2)
    public void verifyAccountDetailsSection(){
        try{
            logger.info("Executing TC_025");


            // Verify Account Details heading
            Assert.assertTrue(customerProfile.isAccountDetailsHeadingDisplayed(),
                    "Account Details heading should be displayed");

            // Verify email is displayed
            Assert.assertTrue(customerProfile.isCustomerEmailDisplayed(),
                    "Customer email should be displayed");
            String email = customerProfile.getCustomerEmail();
            Assert.assertEquals(
                    email,
                    properties.getProperty("testCustomerEmail"),
                    "Customer email does not match."
            );

            // Verify mobile is displayed
            Assert.assertTrue(customerProfile.isCustomerMobileDisplayed(),
                    "Customer mobile should be displayed");
            String mobile = customerProfile.getCustomerMobile();
            Assert.assertFalse(mobile.trim().isEmpty(), "Mobile should not be empty");

            // Verify city is displayed
            Assert.assertTrue(customerProfile.isCustomerCityDisplayed(),
                    "Customer city should be displayed");
            String city = customerProfile.getCustomerCity();
            Assert.assertFalse(city.trim().isEmpty(), "City should not be empty");

            // Verify Sign Out button
            Assert.assertTrue(customerProfile.isSignOutButtonDisplayed(),
                    "Sign out button should be displayed");

            logger.info("TC_025 completed successfully.");

        }catch(Exception e){
            logger.error("TC_025 FAILED: {}", e.getMessage());
            Assert.fail("TC_025 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_026 - Verify "Requirements, offers and OTP updates" section properly
     * Prerequisites: User is logged in as a customer
     * Expected: Notifications section should display items and unread counts, or "No notifications" message
     */
    @Test(priority = 3)
    public void verifyNotificationsSection(){
        try{
            logger.info("Executing TC_026");


            // Verify notifications heading
            Assert.assertTrue(customerProfile.isNotificationsHeadingDisplayed(),
                    "Notifications section heading should be displayed");

            // Check if there are notifications or empty state
            if(customerProfile.isNoNotificationsMessageDisplayed()){
                String noNotifMsg = customerProfile.getNoNotificationsMessage();
                Assert.assertTrue(noNotifMsg.contains("No customer notifications"),
                        "Should show 'No customer notifications' message when empty");
                logger.info("TC_026 PASSED: Notifications section shows empty state");

            }else{
                // Verify items count is displayed
                Assert.assertTrue(customerProfile.isItemsCountDisplayed() ||
                                customerProfile.isUnreadCountDisplayed(),
                        "Either items or unread count should be displayed");
                logger.info("TC_026 completed successfully.");

            }

        }catch(Exception e){
            logger.error("TC_026 FAILED: {}", e.getMessage());
            Assert.fail("TC_026 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_027 - Verify the customer has an option to go back to the dashboard page
     * Prerequisites: User is logged in as a customer
     * Expected: Customer should be able to navigate back to dashboard
     */
//    @Test(priority = 4)
//    public void verifyBackToDashboardNavigation(){
//        try{
//            customerProfile = new CustomerProfilePage(driver);
//
//            // Click back to dashboard
//            customerProfile.clickBackToDashboard();
//            Thread.sleep(2000);
//
//            customerDashboard = new CustomerDashboardPage(driver);
//
//            // Verify we are back on dashboard
//            Assert.assertTrue(customerDashboard.isWelcomeMessageDisplayed(),
//                "Should navigate back to customer dashboard");
//

//
//        }catch(Exception e){
//            Assert.fail("TC_027 FAILED: " + e.getMessage());
//        }
//    }

    /**
     * TC_028 - Verify the sign out button in the customer profile page is working
     * Prerequisites: User is logged in as a customer
     * Expected: Clicking sign out should successfully logout the customer
     */
    @Test(priority = 5)
    public void verifySignOutFunctionality(){
        try{
            logger.info("Executing TC_028");


            // Click sign out
            Assert.assertTrue(
                    logoutUser(),
                    "Customer logout failed."
            );
            logger.info("TC_028 completed successfully.");

        }catch(Exception e){
            logger.error("TC_028 FAILED: {}", e.getMessage());
            Assert.fail("TC_028 FAILED: " + e.getMessage());
        }
    }

    @AfterMethod
    public void logout() {

        try {
            if (!driver.getCurrentUrl().contains("/auth")) {
                logoutUser();
            }
        } catch (Exception e) {
            logger.warn("Logout skipped", e);
        }
    }
}
