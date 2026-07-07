package TestCases;


import PageObjects.CustomerDashboardPage;
import PageObjects.CustomerProfilePage;
import TestBase.BaseClass;
import mapper.Role;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class TS004 extends BaseClass {


    CustomerDashboardPage customerDashboard;
    CustomerProfilePage customerProfile;

    @BeforeMethod
    public void loginBeforeTest(Method method) {

        try {

            logger.info("Logging in as customer.");

            String email;
            String password;

            if (method.getName().equals("verifyDashboardForNewUser")) {

                logger.info("Logging in as new customer.");

                email = properties.getProperty("newCustomerEmail");
                password = properties.getProperty("newCustomerPassword");

            } else {

                logger.info("Logging in as existing customer.");

                email = properties.getProperty("testCustomerEmail");
                password = properties.getProperty("testCustomerPassword");
            }

            Assert.assertTrue(
                    loginUser(Role.CUSTOMER, email, password),
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
     * TC_020 - Verify dashboard displays correct content
     */
    @Test(priority = 2)
    public void verifyDashboardDisplayCorrectContent() {

        logger.info("Executing TC_020 - Verify Customer Dashboard");

        try {

            Assert.assertTrue(customerDashboard.isWelcomeMessageDisplayed(),
                    "Welcome message should be displayed.");

            Assert.assertTrue(customerDashboard.isActiveRequirementsHeadingDisplayed(),
                    "Active Requirements heading should be displayed.");

            Assert.assertTrue(customerDashboard.isVehicleRequirementTextDisplayed(),
                    "Vehicle requirement text should be displayed.");

            Assert.assertTrue(customerDashboard.getWelcomeMessage().contains("Welcome"),
                    "Invalid welcome message.");

            Assert.assertTrue(customerDashboard.getVehicleRequirementText().contains("Need a vehicle"),
                    "Invalid dashboard text.");

            logger.info("TC_020 completed successfully.");

        } catch (Exception e) {

            logger.error("TC_020 failed.", e);
            Assert.fail("TC_020 failed : " + e.getMessage());
        }
    }

    /**
     * TC_021 - Verify dashboard for a new customer
     */
    @Test(priority = 1)
    public void verifyDashboardForNewUser() {

        logger.info("Executing TC_021 - Verify Dashboard For New User");

        try {

            Assert.assertTrue(customerDashboard.isWelcomeMessageDisplayed(),
                    "Welcome message should be displayed.");

            Assert.assertEquals(customerDashboard.getActiveRequirementsCount(), 0,
                    "Active Requirements should be zero for a new customer.");

            Assert.assertTrue(customerDashboard.isMyRequirementsSectionEmpty(),
                    "My Requirements section should be empty.");

            customerDashboard.clickCustomerProfile();

            Thread.sleep(3000);

            customerProfile = new CustomerProfilePage(driver);

            Assert.assertTrue(
                    customerProfile.isProfilePageDisplayed(),
                    "Profile page did not open."
            );

            Assert.assertTrue(
                    logoutUser(),
                    "Customer logout failed."
            );

            logger.info("TC_021 completed successfully.");

        } catch (Exception e) {

            logger.error("TC_021 failed.", e);
            Assert.fail("TC_021 failed : " + e.getMessage());
        }
    }
}