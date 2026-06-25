package TestCases;

import PageObjects.*;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_CustomerDashboardNavigation extends BaseClass {

    AuthPage authPage;
    CustomerDashboardPage customerDashboard;
    CustomerProfilePage customerProfile;
    CustomerPostRequirementPage postRequirementPage;

    @BeforeMethod
    public void loginBeforeTest(){
        try{
            // Refresh page to ensure clean state
//            driver.navigate().refresh();
//            Thread.sleep(2000);
            
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
                authPage.loginActivity();
                
                // Wait for dashboard to be loaded
                Thread.sleep(3000);
                customerDashboard = new CustomerDashboardPage(driver);
                boolean dashboardLoaded = customerDashboard.isWelcomeMessageDisplayed();
                
                if(!dashboardLoaded){
                    System.out.println("WARNING: Dashboard not visible after login. Test may fail.");
                }else{
                    System.out.println("Login successful. Dashboard is visible.");
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
     * TC_022 - Verify profile button is navigating to the expected page when clicked
     * Prerequisites: Website is opened, Customer is authenticated, Customer is on dashboard
     * Expected: Customer should be navigated to the profile page
     */
    @Test
    public void verifyProfileButtonNavigationFromDashboard(){
        try{
            customerDashboard = new CustomerDashboardPage(driver);
            
            // Verify profile button is displayed
            Assert.assertTrue(customerDashboard.isProfileButtonDisplayed(), 
                "Profile button should be displayed on dashboard");
            
            // Click profile button
            customerDashboard.clickCustomerProfile();
            
            // Wait for profile page to load
            Thread.sleep(2000);
            customerProfile = new CustomerProfilePage(driver);
            
            // Verify we are on profile page
            Assert.assertTrue(customerProfile.isProfilePageDisplayed(), 
                "Should navigate to profile page");
            
            System.out.println("TC_022 PASSED: Profile button navigates to profile page");
            
        }catch(Exception e){
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
            customerDashboard = new CustomerDashboardPage(driver);
            
            // Verify post requirement button is displayed
            Assert.assertTrue(customerDashboard.isPostRequirementButtonDisplayed(), 
                "Post Requirement button should be displayed on dashboard");
            
            // Click post requirement button
            customerDashboard.clickPostRequirement();
            
            // Wait for post requirement page to load
            Thread.sleep(2000);
            postRequirementPage = new CustomerPostRequirementPage(driver);
            
            // Verify we are on post requirement page
            Assert.assertTrue(postRequirementPage.isPostRequirementPageDisplayed(), 
                "Should navigate to post requirement page");
            
            System.out.println("TC_023 PASSED: Post Requirement button navigates to post requirement page");
            
        }catch(Exception e){
            Assert.fail("TC_023 FAILED: " + e.getMessage());
        }
    }
}
