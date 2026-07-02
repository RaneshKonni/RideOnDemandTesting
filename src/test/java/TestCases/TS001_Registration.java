package TestCases;

import DataProviders.RegisterDataProvider;
import mapper.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.BaseClass;
import utilities.RandomDataGeneratorUtil;

public class TS001_Registration extends BaseClass {


    @Test(priority = 1)
    public void TC001_verifyUserRegistrationAsCustomer(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC001_verifyUserRegistrationAsCustomer");
        logger.info("=========================================================");

        try {
            Assert.assertTrue(registerUser(RandomDataGeneratorUtil.getRandomUser("Customer")));

            logger.info("SUCCESS: TC001_verifyUserRegistrationAsCustomer passed successfully!");
        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC001 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void TC002_verifyUserRegistrationAsVendor(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC002_verifyUserRegistrationAsVendor");
        logger.info("=========================================================");

        try {
            Assert.assertTrue(registerUser(RandomDataGeneratorUtil.getRandomUser("Vendor")));
            logger.info("SUCCESS: TC002_verifyUserRegistrationAsVendor passed successfully!");
        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC002 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void TC003_verifyUserRegistrationAsAdmin(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC003_verifyUserRegistrationAsAdmin");
        logger.info("=========================================================");

        try {
            Assert.assertTrue(registerUser(RandomDataGeneratorUtil.getRandomUser("Admin")));
            logger.info("SUCCESS: TC003_verifyUserRegistrationAsAdmin passed successfully!");
        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC003 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void TC004_verifyUserRegistrationWithDuplicateEmail() {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC004_verifyUserRegistrationWithDuplicateEmail");
        logger.info("=========================================================");

        try {
            User user1 = RandomDataGeneratorUtil.getRandomUser("Customer");
            Assert.assertTrue(registerUser(user1));
            logoutUser();
            User user2 = RandomDataGeneratorUtil.getRandomUser("Customer");
            user2.setEmail(user1.getEmail());
            Assert.assertFalse(registerUser(user2));
            logger.info("SUCCESS: TC004_verifyUserRegistrationWithDuplicateEmail passed successfully!");
        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC004 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void TC005_verifyUserRegistrationWithDuplicateMobile(){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC005_verifyUserRegistrationWithDuplicateMobile");
        logger.info("=========================================================");

        try {
            User user1 = RandomDataGeneratorUtil.getRandomUser("Customer");
            Assert.assertTrue(registerUser(user1));
            logoutUser();
            User user2 = RandomDataGeneratorUtil.getRandomUser("Customer");
            user2.setMobile(user1.getMobile());
            Assert.assertFalse(registerUser(user2));
            logger.info("SUCCESS: TC005_verifyUserRegistrationWithDuplicateMobile passed successfully!");
        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC005 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }


    @Test(priority = 6, dataProvider = "RegisterData", dataProviderClass = RegisterDataProvider.class)
    public void TC006_verifyErrorMessagesWhileRegisteringUser(String role, String fullName, String email, String password, String mobile, String city, String shopName, String errorMsg, String expectedResult){
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC006_verifyErrorMessagesWhileRegisteringUser");
        logger.info("=========================================================");

        try {
            User user = new User(fullName, email,  password, mobile, city, role, shopName);
            verifyErrorMessagesWhileRegisteringUser(user, errorMsg);
            logger.info("SUCCESS: TC006_verifyErrorMessagesWhileRegisteringUser passed successfully!");
        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC006 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

}