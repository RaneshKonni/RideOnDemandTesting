package TestCases;

import PageObjects.*;
import TestBase.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class TS003_CustomerDashboard extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TS003_CustomerDashboard.class);

    AuthPage authPage;
    CustomerDashboardPage customerDashboard;
    CustomerProfilePage customerProfile;

    @BeforeMethod
    public void loginBeforeTest(Method method){

        try{

            String email;
            String password;
            authPage = new AuthPage(driver);
            authPage.loginActivity();

            if(method.getName().equalsIgnoreCase("verifyDashboardForNewUser")) {
                // For this test, we need to register a new user
                email = "NewUser@gmail.com";
                password = "Test@123";
            }else {


                // After registration, the user should be logged in automatically
                email = p.getProperty("testCustomerEmail");
                password = p.getProperty("testCustomerPassword");
            }
                if (email != null && !email.trim().isEmpty() && password != null && !password.trim().isEmpty()) {

                    authPage.setTfEmail(email);
                    authPage.setTfPassword(password);
                    authPage.clickBtnSubmit();

                    // Wait for dashboard to be loaded (verify we're authenticated)
                    Thread.sleep(3000);
                    customerDashboard = new CustomerDashboardPage(driver);
                    boolean dashboardLoaded = customerDashboard.isWelcomeMessageDisplayed();

                    if (!dashboardLoaded) {
                        logger.warn("Dashboard not visible after login. Test may fail.");
                        System.out.println("WARNING: Dashboard not visible after login. Test may fail.");
                    } else {
                        logger.info("Login successful. Dashboard is visible.");
                        System.out.println("Login successful. Dashboard is visible.");
                    }
                } else {
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
     * TC_020 - Verify dashboard display correct content
     * Prerequisites: User is logged in as a customer
     * Expected: Dashboard should display Welcome message, Active Requirements, and vehicle requirement text
     */
    @Test(priority = 2)
    public void verifyDashboardDisplayCorrectContent(){
        try{
            // Navigate to auth page
            authPage = new AuthPage(driver);
            
            // Since we need a logged in user, we'll register/login a customer
            // For testing purposes, we assume the user is already logged in
            customerDashboard = new CustomerDashboardPage(driver);
            
            // Verify dashboard is displayed
            Assert.assertTrue(customerDashboard.isWelcomeMessageDisplayed(),
                "Customer dashboard should be displayed");
            
            // Verify Welcome message
            Assert.assertTrue(customerDashboard.isWelcomeMessageDisplayed(), 
                "Welcome message should be displayed");
            String welcomeMsg = customerDashboard.getWelcomeMessage();
            Assert.assertTrue(welcomeMsg.contains("Welcome"), 
                "Welcome message should contain 'Welcome' text");
            
            // Verify Active Requirements heading
            Assert.assertTrue(customerDashboard.isActiveRequirementsHeadingDisplayed(), 
                "Active Requirements heading should be displayed");
            
            // Verify vehicle requirement text
            Assert.assertTrue(customerDashboard.isVehicleRequirementTextDisplayed(), 
                "Vehicle requirement text should be displayed");
            String vehicleText = customerDashboard.getVehicleRequirementText();
            Assert.assertTrue(vehicleText.contains("Need a vehicle"), 
                "Should display vehicle requirement message");
            
            logger.info("TC_020 PASSED: Dashboard displays correct content");
            System.out.println("TC_020 PASSED: Dashboard displays correct content");
            
        }catch(Exception e){
            logger.error("TC_020 FAILED: {}", e.getMessage());
            Assert.fail("TC_020 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_021 - Verify the dashboard for a new user
     * Prerequisites: User is logged in as a customer (new user)
     * Expected: Dashboard should show Welcome message, Active Requirements as zero, and empty requirements
     */
    @Test(priority = 1)
    public void verifyDashboardForNewUser(){
        try{
            customerDashboard = new CustomerDashboardPage(driver);
            
            // Verify Welcome message
            Assert.assertTrue(customerDashboard.isWelcomeMessageDisplayed(), 
                "Welcome message should be displayed for new user");
            
            // Verify Active Requirements is zero
            int activeCount = customerDashboard.getActiveRequirementsCount();
            Assert.assertEquals(activeCount, 0, 
                "Active Requirements count should be zero for new user");
            
            // Verify My Requirements section is empty
            Assert.assertTrue(customerDashboard.isMyRequirementsSectionEmpty());

            logger.info("TC_021 PASSED: Dashboard for new user verified");
            System.out.println("TC_021 PASSED: Dashboard for new user verified");
            Thread.sleep(3000);
            customerDashboard.clickCustomerProfile();

            customerProfile = new CustomerProfilePage(driver);

            customerProfile.clickSignout();

            Thread.sleep(3000);
        }catch(Exception e){
            logger.error("TC_021 FAILED: {}", e.getMessage());
            Assert.fail("TC_021 FAILED: " + e.getMessage());
        }
    }
}
