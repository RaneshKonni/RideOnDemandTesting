package TestCases;

import DataProviders.LoginDataProvider;
import mapper.Role;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.BaseClass;

public class TS002_Login extends BaseClass {

    @Test(priority = 7, dataProvider = "LoginData",dataProviderClass = LoginDataProvider.class)
    public void TC007_verifyLogin(Role role, String email, String password, String expectedResult) {
        logger.info("=========================================================");
        logger.info("STARTING TEST CASE: TC007_verifyLogin");
        logger.info("=========================================================");

        try {
            if(expectedResult.equals("valid")){
                Assert.assertTrue(loginUser(role, email, password));
            } else {
                Assert.assertFalse(loginUser(role, email, password));
            }

            logger.info("SUCCESS: TC007_verifyLogin passed successfully!");
        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC007 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }


    @Test(priority = 8, dataProvider = "LoginDataNeg", dataProviderClass = LoginDataProvider.class)
    public void TC008_verifyErrorMsgWhileUserLogin(Role role,String email,String password,String expectedError) {
//        logger.info("=========================================================");
//        logger.info("STARTING TEST CASE: TC008_verifyErrorMsgWhileUserLogin");
//        logger.info("=========================================================");

        try {
            verifyErrorMsgWhileUserLogin(role, email, password, expectedError);

            logger.info("SUCCESS: TC008_verifyErrorMsgWhileUserLogin passed successfully!");
        } catch (AssertionError ae) {
            logger.error("ASSERTION FAILED: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("FAILURE: Exception encountered during TC008 execution!");
            logger.error("Exception Message: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}
