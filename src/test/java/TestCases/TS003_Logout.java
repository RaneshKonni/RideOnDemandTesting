//package TestCases;
//
//import DataProviders.LogoutDataProvider;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import TestBase.BaseClass;
//
//public class TS003_Logout extends BaseClass {
//    @Test(priority = 1, dataProvider = "LogoutData", dataProviderClass = LogoutDataProvider.class)
//    public void TC005_verifyLogout(String role, String email, String password, String expectedResult) {
////        logger.info("=========================================================");
////        logger.info("STARTING TEST CASE: TC005_verifyLogoutAsCustomer");
////        logger.info("=========================================================");
//
//        try {
//
//            Assert.assertTrue(loginUser(role, email, password));
//            Assert.assertTrue(logoutUser());
////            logger.info("SUCCESS: TC005_verifyLogoutAsCustomer passed successfully!");
//        } catch (AssertionError ae) {
////            logger.error("ASSERTION FAILED: " + ae.getMessage());
//            throw ae;
//        } catch (Exception e) {
////            logger.error("FAILURE: Exception encountered during TC005 execution!");
////            logger.error("Exception Message: " + e.getMessage());
//            Assert.fail("Test failed due to an exception: " + e.getMessage());
//        }
//    }
//}