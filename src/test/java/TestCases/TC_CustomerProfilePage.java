package TestCases;

import PageObjects.*;

import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_CustomerProfilePage extends BaseClass {

    CustomerDashboardPage customerDashboard;
    CustomerProfilePage customerProfile;

    @BeforeMethod
    public void loginBeforeTest(){
        try{
            // Refresh page to ensure clean state
            driver.navigate().refresh();
            Thread.sleep(2000);
            
            AuthPage authPage = new AuthPage(driver);
            String email = p.getProperty("testCustomerEmail");
            String password = p.getProperty("testCustomerPassword");
            
            if(email != null && !email.trim().isEmpty() && password != null && !password.trim().isEmpty()){
                // Ensure login form is visible and ready
//                authPage.ensureLoginFormVisible();
                
                // Enter credentials and login
                authPage.setTfEmail(email);
                authPage.setTfPassword(password);
                authPage.loginActivity();
                
                // Wait for dashboard to be loaded, then navigate to profile
                Thread.sleep(3000);
                customerDashboard = new CustomerDashboardPage(driver);
                boolean dashboardLoaded = customerDashboard.isWelcomeMessageDisplayed();
                
                if(dashboardLoaded){
                    // Navigate to profile for profile-specific tests
                    customerDashboard.clickCustomerProfile();
                    Thread.sleep(2000);
                    System.out.println("Login successful. Navigated to profile page.");
                }else{
                    System.out.println("WARNING: Dashboard not visible after login. Test may fail.");
                }
            }else{
                System.out.println("CRITICAL: No test credentials found in config.properties. Tests will fail.");
            }
        }catch(Exception e){
            System.out.println("Login setup failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * TC_024 - Verify the text "Profile & Notifications" and the logo is visible on the page
     * Prerequisites: User logged in as customer
     * Expected: "Profile & Notifications" text and logo should be displayed
     */
    @Test
    public void verifyProfileHeaderAndLogo(){
        try{
            customerDashboard = new CustomerDashboardPage(driver);
            customerDashboard.clickCustomerProfile();
            Thread.sleep(2000);
            
            customerProfile = new CustomerProfilePage(driver);
            
            // Verify Profile & Notifications text
            Assert.assertTrue(customerProfile.isProfileAndNotificationsTextDisplayed(), 
                "Profile & Notifications text should be displayed");
            
            // Verify logo
            Assert.assertTrue(customerProfile.isLogoDisplayed(), 
                "Logo should be displayed on profile page");
            
            System.out.println("TC_024 PASSED: Profile header and logo are visible");
            
        }catch(Exception e){
            Assert.fail("TC_024 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_025 - Verify the customer section and account details has valid details and sign out button
     * Prerequisites: User is logged in as a customer
     * Expected: Account details should display email, mobile, city, and sign out button
     */
    @Test
    public void verifyAccountDetailsSection(){
        try{
            customerProfile = new CustomerProfilePage(driver);
            
            // Verify Account Details heading
            Assert.assertTrue(customerProfile.isAccountDetailsHeadingDisplayed(), 
                "Account Details heading should be displayed");
            
            // Verify email is displayed
            Assert.assertTrue(customerProfile.isCustomerEmailDisplayed(), 
                "Customer email should be displayed");
            String email = customerProfile.getCustomerEmail();
            Assert.assertFalse(email.isEmpty(), "Email should not be empty");
            
            // Verify mobile is displayed
            Assert.assertTrue(customerProfile.isCustomerMobileDisplayed(), 
                "Customer mobile should be displayed");
            String mobile = customerProfile.getCustomerMobile();
            Assert.assertFalse(mobile.isEmpty(), "Mobile should not be empty");
            
            // Verify city is displayed
            Assert.assertTrue(customerProfile.isCustomerCityDisplayed(), 
                "Customer city should be displayed");
            String city = customerProfile.getCustomerCity();
            Assert.assertFalse(city.isEmpty(), "City should not be empty");
            
            // Verify Sign Out button
            Assert.assertTrue(customerProfile.isSignOutButtonDisplayed(), 
                "Sign out button should be displayed");
            
            System.out.println("TC_025 PASSED: Account details section verified with email: " + email + 
                             ", mobile: " + mobile + ", city: " + city);
            
        }catch(Exception e){
            Assert.fail("TC_025 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_026 - Verify "Requirements, offers and OTP updates" section properly
     * Prerequisites: User is logged in as a customer
     * Expected: Notifications section should display items and unread counts, or "No notifications" message
     */
    @Test
    public void verifyNotificationsSection(){
        try{
            customerProfile = new CustomerProfilePage(driver);
            
            // Verify notifications heading
            Assert.assertTrue(customerProfile.isNotificationsHeadingDisplayed(), 
                "Notifications section heading should be displayed");
            
            // Check if there are notifications or empty state
            if(customerProfile.isNoNotificationsMessageDisplayed()){
                String noNotifMsg = customerProfile.getNoNotificationsMessage();
                Assert.assertTrue(noNotifMsg.contains("No customer notifications"), 
                    "Should show 'No customer notifications' message when empty");
                System.out.println("TC_026 PASSED: Notifications section shows empty state");
            }else{
                // Verify items count is displayed
                Assert.assertTrue(customerProfile.isItemsCountDisplayed() || 
                                 customerProfile.isUnreadCountDisplayed(), 
                    "Either items or unread count should be displayed");
                System.out.println("TC_026 PASSED: Notifications section with items displayed");
            }
            
        }catch(Exception e){
            Assert.fail("TC_026 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_027 - Verify the customer has an option to go back to the dashboard page
     * Prerequisites: User is logged in as a customer
     * Expected: Customer should be able to navigate back to dashboard
     */
    @Test
    public void verifyBackToDashboardNavigation(){
        try{
            customerProfile = new CustomerProfilePage(driver);
            
            // Click back to dashboard
            customerProfile.clickBackToDashboard();
            Thread.sleep(2000);
            
            customerDashboard = new CustomerDashboardPage(driver);
            
            // Verify we are back on dashboard
            Assert.assertTrue(customerDashboard.isWelcomeMessageDisplayed(),
                "Should navigate back to customer dashboard");
            
            System.out.println("TC_027 PASSED: Can navigate back to dashboard from profile");
            
        }catch(Exception e){
            Assert.fail("TC_027 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_028 - Verify the sign out button in the customer profile page is working
     * Prerequisites: User is logged in as a customer
     * Expected: Clicking sign out should successfully logout the customer
     */
    @Test
    public void verifySignOutFunctionality(){
        try{
            customerProfile = new CustomerProfilePage(driver);
            
            // Click sign out
            customerProfile.clickSignout();
            Thread.sleep(2000);
            
            // User should be redirected to login/auth page
            // Verify we are on auth page by checking if login/register buttons are visible
            String currentUrl = driver.getCurrentUrl();
            Assert.assertNotNull(currentUrl, "Current URL should not be null");
            Assert.assertTrue(currentUrl.contains("auth") || currentUrl.contains("login"), 
                "Should be redirected to auth page after sign out");
            
            System.out.println("TC_028 PASSED: Sign out functionality works correctly");
            
        }catch(Exception e){
            Assert.fail("TC_028 FAILED: " + e.getMessage());
        }
    }
}
