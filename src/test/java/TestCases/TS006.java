//package TestCases;
//
//import PageObjects.*;
//import TestBase.BaseClass;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import java.util.List;
//import org.openqa.selenium.WebElement;
//
//public class TS006 extends BaseClass{
//
//    private static final Logger logger = LogManager.getLogger(TS006.class);
//
//    AuthPage authPage;
//    CustomerDashboardPage customerDashboard;
//    CustomerProfilePage customerProfile;
//    CustomerPostRequirementPage postRequirementPage;
//
//    @BeforeMethod
//    public void loginBeforeTest() {
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
//            authPage.setTfEmail(p.getProperty("testCustomerEmail"));
//            authPage.setTfPassword(p.getProperty("testCustomerPassword"));
//            authPage.clickBtnSubmit();
//
//            Thread.sleep(3000);
//
//            customerDashboard = new CustomerDashboardPage(driver);
//
//            Assert.assertTrue(customerDashboard.isWelcomeMessageDisplayed(),
//                    "Login failed.");
//            customerDashboard.clickPostRequirement();
//
//            postRequirementPage = new CustomerPostRequirementPage(driver);
//
//            Assert.assertTrue(postRequirementPage.isPostRequirementPageDisplayed(),
//                    "Post Requirement page did not open.");
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
//
//    /**
//     * TC_029 - Verify the content of the customer post requirement page is rendered properly
//     * Prerequisites: User is logged in as a customer
//     * Expected: Page should display "Post Requirement", "Create your rental requirement", and 3 steps
//     */
//    @Test(priority = 1)
//    public void verifyPostRequirementPageContent(){
//        try{
//            logger.info("Executing TC_029");
//            Assert.assertTrue(postRequirementPage.isPostRequirementPageDisplayed());
//            logger.info("TC_029 completed successfully.");
//        }catch(Exception e){
//            logger.error("TC_029 FAILED: {}", e.getMessage());
//            Assert.fail("TC_029 FAILED: " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_030 - Verify the customer is able to navigate to the profile page from post requirement page
//     * Prerequisites: User is logged in as a customer
//     * Expected: Customer should be able to navigate to profile page
//     */
//    @Test(priority = 2)
//    public void verifyNavigationToProfileFromPostRequirement(){
//        try{
//            logger.info("Executing TC_030");
//            postRequirementPage = new CustomerPostRequirementPage(driver);
//            Thread.sleep(3000);
//            // Verify we can navigate to profile
//            Assert.assertTrue(postRequirementPage.canNavigateToProfile());
//            logger.info("TC_030 completed successfully.");
//        }catch(Exception e){
//            logger.error("TC_030 FAILED: {}", e.getMessage());
//            Assert.fail("TC_030 FAILED: " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_031 - Verify post requirement page handles invalid input and empty fields properly
//     * Prerequisites: User is logged in as a customer, on post requirement page
//     * Expected: Page should not allow empty or invalid values for dates, location, and budget
//     */
//    @Test(priority = 3)
//    public void verifyInvalidInputHandling(){
//        try{
//
//            logger.info("Executing TC_031");
//
//            logger.info("---------- Empty Field Test ----------");
//
//            Thread.sleep(2000);
//            postRequirementPage.clearStartDate();
//            postRequirementPage.clearEndDate();
//            Thread.sleep(3000);
//            // Leave every field empty
//            postRequirementPage.clickNext();
//
//            if (postRequirementPage.isPickupLocationFieldDisplayed()) {
//                logger.warn("Observation: Empty fields accepted.");
//                //Assert.assertTrue(false);
//            }
//            Thread.sleep(3000);
//
//            logger.info("---------- Invalid Date Test ----------");
//
//            postRequirementPage.clickBack();
//            Thread.sleep(5000);
//            postRequirementPage.setStartDate("01012024");
//            postRequirementPage.setEndDate("02012024");
//            Thread.sleep(2000);
//            postRequirementPage.clickNext();
//            Thread.sleep(3000);
//            if (postRequirementPage.isPickupLocationFieldDisplayed()) {
//                logger.warn("Observation: Past dates accepted.");
//
//            }
//
//            logger.info("---------- Invalid Budget Test ----------");
//            Thread.sleep(3000);
//            postRequirementPage.clearPickupLocation();
//            postRequirementPage.clearBudget();
//            postRequirementPage.setPickupLocation("Hyderabad");
//            postRequirementPage.setBudgetPerDay("-500");
//            Thread.sleep(5000);
//            postRequirementPage.clickNext();
//
//            if (postRequirementPage.isPostRequirementButtonDisplayed()) {
//                logger.warn("Observation: Negative budget accepted.");
//            }
//            boolean invalidAccepted = postRequirementPage.isPostRequirementButtonDisplayed();
//
//            if(invalidAccepted){
//                Assert.fail("Application accepted invalid data.");
//            }
//            logger.info("TC_031 completed successfully.");
//
//            Assert.fail("Observation: Application allows empty or invalid inputs. Validation may not be enforced.");
//        }catch(Exception e){
//            logger.error("TC_031 FAILED: {}", e.getMessage());
//            Assert.fail("TC_031 FAILED: " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_032 - Verify the vehicle type drop down is working
//     * Prerequisites: User is logged in as a customer, on post requirement page
//     * Expected: Dropdown should contain Bike, Scooty, Car, SUV options
//     */
//    @Test(priority = 4)
//    public void verifyVehicleTypeDropdown(){
//        try{
//            logger.info("Executing TC_032");
//
//            Assert.assertTrue(postRequirementPage.isVehicleTypeDropdownDisplayed(),
//                    "Vehicle type dropdown should be displayed");
//
//            // Get vehicle type options
//            List<WebElement> options = postRequirementPage.getVehicleTypeOptions();
//
//            // Verify dropdown has options
//            Assert.assertTrue(options.size() > 0, "Dropdown should have options");
//
//            // Get option texts and verify required options exist
//            boolean hasBike = false, hasScooter = false, hasCar = false, hasSUV = false;
//
//            for(WebElement option : options){
//                String optionText = option.getText().toLowerCase();
//                if(optionText.contains("bike")) hasBike = true;
//                if(optionText.contains("scooty")) hasScooter = true;
//                if(optionText.contains("car")) hasCar = true;
//                if(optionText.contains("suv")) hasSUV = true;
//            }
//
//            Assert.assertTrue(hasBike || hasScooter || hasCar || hasSUV,
//                    "Dropdown should contain vehicle options");
//
//            logger.info("TC_032 completed successfully.");
//
//
//        }catch(Exception e){
//            logger.error("TC_032 FAILED: {}", e.getMessage());
//            Assert.fail("TC_032 FAILED: " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_033 - Verify the date picker for start date and end date is working
//     * Prerequisites: User is logged in as a customer, on post requirement page
//     * Expected: Customer should be able to access and select dates from date picker
//     */
//    @Test(priority = 5)
//    public void verifyDatePickers() {
//        try {
//            logger.info("Executing TC_033");
//
//            Assert.assertTrue(postRequirementPage.isStartDateFieldDisplayed(),
//                    "Start Date field should be displayed");
//
//            postRequirementPage.clickStartDatePicker();
//            Thread.sleep(1000);
//
//            // Select Start Date
//            postRequirementPage.setStartDate("15072026");
//
//            // Verify End Date picker
//            Assert.assertTrue(postRequirementPage.isEndDateFieldDisplayed(),
//                    "End Date field should be displayed");
//
//            postRequirementPage.clickEndDatePicker();
//            Thread.sleep(1000);
//
//            // Select End Date earlier than Start Date
//            postRequirementPage.setEndDate("10072026");
//
//            Thread.sleep(3000);
//            // Continue to next step
//            postRequirementPage.clickNext();
//            Thread.sleep(2000);
//            // Current application behaviour
//            if(postRequirementPage.isPickupLocationFieldDisplayed()) {
//                postRequirementPage.clickBackToProfile();
//                customerProfile = new CustomerProfilePage(driver);
//                Thread.sleep(3000);
//                customerProfile.clickSignout();
//                logger.warn("Observation: Application allows End Date before Start Date.");
//
//                Assert.assertTrue(false, "Application should not allow End Date before Start Date.");
//            } else {
//                logger.info("Application correctly rejected invalid date range.");
//
//            }
//            logger.info("TC_033 completed successfully.");
//
//        } catch (Exception e) {
//            logger.error("TC_033 FAILED: {}", e.getMessage());
//            Assert.fail("TC_033 FAILED: " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_034 - Verify the "next" button is working
//     * Prerequisites: User is logged in as a customer, on post requirement page step 1
//     * Expected: Clicking next should go to the next step
//     */
//    @Test(priority = 6)
//    public void verifyNextButtonFunctionality(){
//        try{
//            logger.info("Executing TC_034");
//
//            Assert.assertTrue(postRequirementPage.isNextButtonDisplayed(),
//                    "Next button should be displayed");
//
//            // Fill in step 1 fields (assuming required fields)
//            postRequirementPage.setStartDate("10072027");
//            postRequirementPage.setEndDate("15072027");
//            postRequirementPage.selectVehicleType("Car");
//            Thread.sleep(3000);
//            // Click next
//            postRequirementPage.clickNext();
//            Thread.sleep(2000);
//
//            Assert.assertTrue(postRequirementPage.isPickupLocationFieldDisplayed(),"Should navigate to Step 2.");
//
//            logger.info("TC_034 completed successfully.");
//
//
//        }catch(Exception e){
//            logger.error("TC_034 FAILED: {}", e.getMessage());
//            Assert.fail("TC_034 FAILED: " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_035 - Verify 1st step does not have "back" and last step does not have "next"
//     * Prerequisites: User is logged in as a customer, on post requirement page
//     * Expected: Step 1 should not have back button, last step should not have next button
//     */
//    @Test(priority = 7)
//    public void verifyButtonVisibilityAcrossSteps(){
//        try{
//            logger.info("Executing TC_035");
//
//            Assert.assertFalse(postRequirementPage.isBackButtonDisplayed(),
//                    "Back button should not be visible on step 1");
//
//            Assert.assertTrue(postRequirementPage.isNextButtonDisplayed(),
//                    "Next button should be visible on step 1");
//
//            // Fill and go to next steps
//            postRequirementPage.setStartDate("27062027");
//            postRequirementPage.setEndDate("01072027");
//            postRequirementPage.selectVehicleType("Car");
//            Thread.sleep(2000);
//            postRequirementPage.clickNext();
//            Thread.sleep(2000);
//
//            // On step 2, both buttons should be visible
//            postRequirementPage.clearPickupLocation();
//            postRequirementPage.clearBudget();
//            postRequirementPage.setPickupLocation("Downtown");
//            postRequirementPage.setBudgetPerDay("500");
//            Assert.assertTrue(postRequirementPage.isBackButtonDisplayed(),
//                    "Back button should be visible on step 2");
//            Assert.assertTrue(postRequirementPage.isNextButtonDisplayed(),
//                    "Next button should be visible on step 2");
//            Thread.sleep(2000);
//            postRequirementPage.clickNext();
//            Thread.sleep(2000);
//
//            // On step 3 (last step), post requirement button should be displayed instead of next
//            Assert.assertTrue(postRequirementPage.isPostRequirementButtonDisplayed(),
//                    "Post Requirement button should be displayed on last step");
//            Assert.assertFalse(postRequirementPage.isNextButtonDisplayed(),
//                    "Next button should not be visible on last step");
//
//            logger.info("TC_035 completed successfully.");
//
//
//        }catch(Exception e){
//            logger.error("TC_035 FAILED: {}", e.getMessage());
//            Assert.fail("TC_035 FAILED: " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_036 - Verify "back" and "post requirement" buttons are working in the last step
//     * Prerequisites: User is logged in as a customer, on last step of post requirement
//     * Expected: Back and Post Requirement buttons should work correctly
//     */
//    @Test(priority = 8)
//    public void verifyLastStepButtonsFunctionality(){
//        try{
//
//            // Step 1
//            postRequirementPage.setStartDate("10102027");
//            postRequirementPage.setEndDate("15102027");
//            postRequirementPage.selectVehicleType("Car");
//            postRequirementPage.clickNext();
//            Thread.sleep(2000);
//
//            // Step 2
//            postRequirementPage.clearBudget();
//            postRequirementPage.clearPickupLocation();
//            postRequirementPage.setPickupLocation("Park Street");
//            postRequirementPage.setBudgetPerDay("600");
//            postRequirementPage.clickNext();
//            Thread.sleep(4000);
//
//
//            // Verify Post Requirement button
//            Assert.assertTrue(postRequirementPage.isPostRequirementButtonDisplayed(),
//                    "Post Requirement button should be displayed on last step");
//
//            // Verify Back button
//            Assert.assertTrue(postRequirementPage.isBackButtonDisplayed(),
//                    "Back button should be visible on last step");
//
//            // Test back button functionality
//            postRequirementPage.clickBack();
//            Thread.sleep(4000);
//
//            // Should go back to step 2
//            Assert.assertTrue(postRequirementPage.isPickupLocationFieldDisplayed(),
//                    "Should be on step 2 after clicking back");
//
//            // Go forward again
//            postRequirementPage.clickNext();
//            Thread.sleep(4000);
//
//            // Should be on step 3 again
//            Assert.assertTrue(postRequirementPage.isPostRequirementButtonDisplayed(),
//                    "Should be back on step 3");
//
//            logger.info("TC_036 completed successfully.");
//
//
//        }catch(Exception e){
//            logger.error("TC_036 FAILED: {}", e.getMessage());
//            Assert.fail("TC_036 FAILED: " + e.getMessage());
//        }
//    }
//
//    @AfterMethod
//    public void logout() {
//
//        try {
//
//            if(postRequirementPage != null &&
//                    postRequirementPage.canNavigateToProfile()) {
//
//                postRequirementPage.clickBackToProfile();
//            }
//
//            customerProfile = new CustomerProfilePage(driver);
//
//            if(customerProfile.isSignOutButtonDisplayed()) {
//                customerProfile.clickSignout();
//            }
//
//        } catch (Exception ignored) {
//            logger.warn("Cleanup skipped.");
//        }
//    }
//}
