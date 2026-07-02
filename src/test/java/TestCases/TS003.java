//package TestCases;
//
//import PageObjects.AuthPage;
//import PageObjects.CustomerDashboardPage;
//import PageObjects.CustomerProfilePage;
//import TestBase.BaseClass;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.lang.reflect.Method;
//
//public class TS003 extends BaseClass {
//
//    private static final Logger logger = LogManager.getLogger(TS003.class);
//
//    AuthPage authPage;
//    CustomerDashboardPage customerDashboard;
//    CustomerProfilePage customerProfile;
//
//    @BeforeMethod
//    public void loginBeforeTest(Method method) {
//
//        try {
//
//            driver.get(p.getProperty("appUrl"));
//            Thread.sleep(2000);
//
//            logger.info("Logging in as customer.");
//
//            authPage = new AuthPage(driver);
//            authPage.loginActivity();
//
//            String email;
//            String password;
//
//            if (method.getName().equals("verifyDashboardForNewUser")) {
//
//                logger.info("Logging in as new customer.");
//
//                email = p.getProperty("newCustomerEmail");
//                password = p.getProperty("newCustomerPassword");
//
//            } else {
//
//                logger.info("Logging in as existing customer.");
//
//                email = p.getProperty("testCustomerEmail");
//                password = p.getProperty("testCustomerPassword");
//            }
//
//            authPage.setTfEmail(email);
//            authPage.setTfPassword(password);
//            authPage.clickBtnSubmit();
//
//            Thread.sleep(3000);
//
//            customerDashboard = new CustomerDashboardPage(driver);
//
//            Assert.assertTrue(customerDashboard.isWelcomeMessageDisplayed(),
//                    "Login failed. Customer Dashboard is not displayed.");
//
//            logger.info("Customer login successful.");
//
//        } catch (Exception e) {
//
//            logger.error("Login setup failed.", e);
//            Assert.fail("Login setup failed : " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_020 - Verify dashboard displays correct content
//     */
//    @Test(priority = 2)
//    public void verifyDashboardDisplayCorrectContent() {
//
//        logger.info("Executing TC_020 - Verify Customer Dashboard");
//
//        try {
//
//            customerDashboard = new CustomerDashboardPage(driver);
//
//            Assert.assertTrue(customerDashboard.isWelcomeMessageDisplayed(),
//                    "Welcome message should be displayed.");
//
//            Assert.assertTrue(customerDashboard.isActiveRequirementsHeadingDisplayed(),
//                    "Active Requirements heading should be displayed.");
//
//            Assert.assertTrue(customerDashboard.isVehicleRequirementTextDisplayed(),
//                    "Vehicle requirement text should be displayed.");
//
//            Assert.assertTrue(customerDashboard.getWelcomeMessage().contains("Welcome"),
//                    "Invalid welcome message.");
//
//            Assert.assertTrue(customerDashboard.getVehicleRequirementText().contains("Need a vehicle"),
//                    "Invalid dashboard text.");
//
//            logger.info("TC_020 completed successfully.");
//
//        } catch (Exception e) {
//
//            logger.error("TC_020 failed.", e);
//            Assert.fail("TC_020 failed : " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_021 - Verify dashboard for a new customer
//     */
//    @Test(priority = 1)
//    public void verifyDashboardForNewUser() {
//
//        logger.info("Executing TC_021 - Verify Dashboard For New User");
//
//        try {
//
//            customerDashboard = new CustomerDashboardPage(driver);
//
//            Assert.assertTrue(customerDashboard.isWelcomeMessageDisplayed(),
//                    "Welcome message should be displayed.");
//
//            Assert.assertEquals(customerDashboard.getActiveRequirementsCount(), 0,
//                    "Active Requirements should be zero for a new customer.");
//
//            Assert.assertTrue(customerDashboard.isMyRequirementsSectionEmpty(),
//                    "My Requirements section should be empty.");
//
//            customerDashboard.clickCustomerProfile();
//
//            Thread.sleep(3000);
//
//            System.out.println(driver.getCurrentUrl());
//
//            customerProfile = new CustomerProfilePage(driver);
//
//            Assert.assertTrue(
//                    customerProfile.isProfilePageDisplayed(),
//                    "Profile page did not open."
//            );
//
//            customerProfile.clickSignout();
//
//            Thread.sleep(3000);
//
//            logger.info("TC_021 completed successfully.");
//
//        } catch (Exception e) {
//
//            logger.error("TC_021 failed.", e);
//            Assert.fail("TC_021 failed : " + e.getMessage());
//        }
//    }
//}