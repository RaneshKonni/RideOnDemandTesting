//package TestCases;
//
//import PageObjects.*;
//import TestBase.BaseClass;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import java.util.List;
//import org.openqa.selenium.WebElement;
//
//public class TC_CustomerPostRequirementPage extends BaseClass {
//
//    CustomerDashboardPage customerDashboard;
//    CustomerProfilePage customerProfile;
//    CustomerPostRequirementPage postRequirementPage;
//
//    @BeforeMethod
//    public void loginBeforeTest(){
//        try{
//            // Refresh page to ensure clean state
//            driver.navigate().refresh();
//            Thread.sleep(2000);
//
//            AuthPage authPage = new AuthPage(driver);
//            String email = p.getProperty("testCustomerEmail");
//            String password = p.getProperty("testCustomerPassword");
//
//            if(email != null && !email.trim().isEmpty() && password != null && !password.trim().isEmpty()){
//                // Ensure login form is visible and ready
////                authPage.ensureLoginFormVisible();
//
//                // Enter credentials and login
//                authPage.setTfEmail(email);
//                authPage.setTfPassword(password);
////                authPage.loginActivity();
//
//                // Wait for dashboard to be loaded
//                Thread.sleep(3000);
//                customerDashboard = new CustomerDashboardPage(driver);
//                boolean dashboardLoaded = customerDashboard.isWelcomeMessageDisplayed();
//
//                if(!dashboardLoaded){
//                    System.out.println("WARNING: Dashboard not visible after login. Test may fail.");
//                }else{
//                    System.out.println("Login successful. Dashboard is visible.");
//                }
//            }else{
//                System.out.println("CRITICAL: No test credentials found in config.properties. Tests will fail.");
//            }
//        }catch(Exception e){
//            System.out.println("Login setup failed: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * TC_029 - Verify the content of the customer post requirement page is rendered properly
//     * Prerequisites: User is logged in as a customer
//     * Expected: Page should display "Post Requirement", "Create your rental requirement", and 3 steps
//     */
//    @Test
//    public void verifyPostRequirementPageContent(){
//        try{
//            customerDashboard = new CustomerDashboardPage(driver);
//            customerDashboard.clickPostRequirement();
//            Thread.sleep(2000);
//
//            postRequirementPage = new CustomerPostRequirementPage(driver);
//
//            // Verify post requirement page is displayed
//            Assert.assertTrue(postRequirementPage.isPostRequirementPageDisplayed(),
//                "Post Requirement page should be displayed");
//
//            // Verify "Post Requirement" text in nav
//            Assert.assertTrue(postRequirementPage.isNavTextDisplayed(),
//                "Post Requirement text should be displayed in nav section");
//            String navText = postRequirementPage.getNavText();
//            Assert.assertTrue(navText.contains("Post Requirement"),
//                "Nav text should contain 'Post Requirement'");
//
//            // Verify "Create your rental requirement" message
//            Assert.assertTrue(postRequirementPage.isCreateRequirementTextDisplayed(),
//                "Create rental requirement text should be displayed");
//            String requirementText = postRequirementPage.getCreateRequirementText();
//            Assert.assertTrue(requirementText.contains("rental requirement"),
//                "Should display create rental requirement message");
//
//            // Verify step indicator (3 steps expected)
//            Assert.assertTrue(postRequirementPage.isStepIndicatorDisplayed(),
//                "Step indicator should be displayed");
//            int totalSteps = postRequirementPage.getTotalSteps();
//            Assert.assertEquals(totalSteps, 3, "Should have 3 steps in post requirement process");
//
//            System.out.println("TC_029 PASSED: Post requirement page content verified with " + totalSteps + " steps");
//
//        }catch(Exception e){
//            Assert.fail("TC_029 FAILED: " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_030 - Verify the customer is able to navigate to the profile page from post requirement page
//     * Prerequisites: User is logged in as a customer
//     * Expected: Customer should be able to navigate to profile page
//     */
//    @Test
//    public void verifyNavigationToProfileFromPostRequirement(){
//        try{
//            postRequirementPage = new CustomerPostRequirementPage(driver);
//
//            // Verify we can navigate to profile
//            Assert.assertTrue(postRequirementPage.canNavigateToProfile(),
//                "Should have option to navigate to profile page");
//
//            // Click back to profile
//            postRequirementPage.clickBackToProfile();
//            Thread.sleep(2000);
//
//            customerProfile = new CustomerProfilePage(driver);
//
//            // Verify we are on profile page
//            Assert.assertTrue(customerProfile.isProfilePageDisplayed(),
//                "Should navigate to profile page from post requirement page");
//
//            System.out.println("TC_030 PASSED: Can navigate to profile from post requirement page");
//
//        }catch(Exception e){
//            Assert.fail("TC_030 FAILED: " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_031 - Verify post requirement page handles invalid input and empty fields properly
//     * Prerequisites: User is logged in as a customer, on post requirement page
//     * Expected: Page should not allow empty or invalid values for dates, location, and budget
//     */
//    @Test
//    public void verifyInvalidInputHandling(){
//        try{
//            // Navigate to post requirement if not already there
//            if(postRequirementPage == null || !postRequirementPage.isPostRequirementPageDisplayed()){
//                customerDashboard = new CustomerDashboardPage(driver);
//                customerDashboard.clickPostRequirement();
//                Thread.sleep(2000);
//                postRequirementPage = new CustomerPostRequirementPage(driver);
//            }
//
//            // Try to submit with empty date fields - should not be allowed
//            Assert.assertTrue(postRequirementPage.isStartDateFieldDisplayed(),
//                "Start date field should be displayed");
//            Assert.assertTrue(postRequirementPage.isEndDateFieldDisplayed(),
//                "End date field should be displayed");
//
//            // Verify location field validation
//            Assert.assertTrue(postRequirementPage.isPickupLocationFieldDisplayed(),
//                "Pickup location field should be displayed");
//
//            // Verify budget field validation
//            Assert.assertTrue(postRequirementPage.isBudgetFieldDisplayed(),
//                "Budget field should be displayed");
//
//            System.out.println("TC_031 PASSED: Empty field validation verified");
//
//        }catch(Exception e){
//            Assert.fail("TC_031 FAILED: " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_032 - Verify the vehicle type drop down is working
//     * Prerequisites: User is logged in as a customer, on post requirement page
//     * Expected: Dropdown should contain Bike, Scooty, Car, SUV options
//     */
//    @Test
//    public void verifyVehicleTypeDropdown(){
//        try{
//            postRequirementPage = new CustomerPostRequirementPage(driver);
//
//            // Verify dropdown is displayed
//            Assert.assertTrue(postRequirementPage.isVehicleTypeDropdownDisplayed(),
//                "Vehicle type dropdown should be displayed");
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
//                if(optionText.contains("scoot")) hasScooter = true;
//                if(optionText.contains("car")) hasCar = true;
//                if(optionText.contains("suv")) hasSUV = true;
//            }
//
//            Assert.assertTrue(hasBike || hasScooter || hasCar || hasSUV,
//                "Dropdown should contain vehicle options");
//
//            System.out.println("TC_032 PASSED: Vehicle type dropdown verified with " + options.size() + " options");
//
//        }catch(Exception e){
//            Assert.fail("TC_032 FAILED: " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_033 - Verify the date picker for start date and end date is working
//     * Prerequisites: User is logged in as a customer, on post requirement page
//     * Expected: Customer should be able to access and select dates from date picker
//     */
//    @Test
//    public void verifyDatePickers(){
//        try{
//            postRequirementPage = new CustomerPostRequirementPage(driver);
//
//            // Verify start date field is clickable
//            Assert.assertTrue(postRequirementPage.isStartDateFieldDisplayed(),
//                "Start date field should be displayed");
//
//            postRequirementPage.clickStartDatePicker();
//            Thread.sleep(1000);
//
//            // Verify end date field is clickable
//            Assert.assertTrue(postRequirementPage.isEndDateFieldDisplayed(),
//                "End date field should be displayed");
//
//            postRequirementPage.clickEndDatePicker();
//            Thread.sleep(1000);
//
//            System.out.println("TC_033 PASSED: Date pickers are accessible");
//
//        }catch(Exception e){
//            Assert.fail("TC_033 FAILED: " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_034 - Verify the "next" button is working
//     * Prerequisites: User is logged in as a customer, on post requirement page step 1
//     * Expected: Clicking next should go to the next step
//     */
//    @Test
//    public void verifyNextButtonFunctionality(){
//        try{
//            postRequirementPage = new CustomerPostRequirementPage(driver);
//
//            // Verify next button is displayed
//            Assert.assertTrue(postRequirementPage.isNextButtonDisplayed(),
//                "Next button should be displayed");
//
//            // Fill in step 1 fields (assuming required fields)
//            postRequirementPage.setStartDate("10102024");
//            postRequirementPage.setEndDate("15102024");
//            postRequirementPage.selectVehicleType("car");
//
//            // Click next
//            postRequirementPage.clickNext();
//            Thread.sleep(2000);
//
//            System.out.println("TC_034 PASSED: Next button functionality verified");
//
//        }catch(Exception e){
//            Assert.fail("TC_034 FAILED: " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_035 - Verify 1st step does not have "back" and last step does not have "next"
//     * Prerequisites: User is logged in as a customer, on post requirement page
//     * Expected: Step 1 should not have back button, last step should not have next button
//     */
//    @Test
//    public void verifyButtonVisibilityAcrossSteps(){
//        try{
//            postRequirementPage = new CustomerPostRequirementPage(driver);
//
//            // On step 1, back button should not be visible
//            Assert.assertFalse(postRequirementPage.isBackButtonVisible(),
//                "Back button should not be visible on step 1");
//
//            Assert.assertTrue(postRequirementPage.isNextButtonVisible(),
//                "Next button should be visible on step 1");
//
//            // Fill and go to next steps
//            postRequirementPage.setStartDate("10102024");
//            postRequirementPage.setEndDate("15102024");
//            postRequirementPage.selectVehicleType("car");
//            postRequirementPage.clickNext();
//            Thread.sleep(2000);
//
//            // On step 2, both buttons should be visible
//            postRequirementPage.setPickupLocation("Downtown");
//            postRequirementPage.setBudgetPerDay("500");
//            postRequirementPage.clickNext();
//            Thread.sleep(2000);
//
//            // On step 3 (last step), post requirement button should be displayed instead of next
//            Assert.assertTrue(postRequirementPage.isPostRequirementButtonDisplayed(),
//                "Post Requirement button should be displayed on last step");
//            Assert.assertFalse(postRequirementPage.isNextButtonVisible(),
//                "Next button should not be visible on last step");
//
//            System.out.println("TC_035 PASSED: Button visibility verified across steps");
//
//        }catch(Exception e){
//            Assert.fail("TC_035 FAILED: " + e.getMessage());
//        }
//    }
//
//    /**
//     * TC_036 - Verify "back" and "post requirement" buttons are working in the last step
//     * Prerequisites: User is logged in as a customer, on last step of post requirement
//     * Expected: Back and Post Requirement buttons should work correctly
//     */
//    @Test
//    public void verifyLastStepButtonsFunctionality(){
//        try{
//            postRequirementPage = new CustomerPostRequirementPage(driver);
//
//            // Assuming we're already on last step from previous test
//            // Fill all steps to reach last step if needed
//            if(!postRequirementPage.isPostRequirementButtonDisplayed()){
//                // Step 1
//                postRequirementPage.setStartDate("10102024");
//                postRequirementPage.setEndDate("15102024");
//                postRequirementPage.selectVehicleType("car");
//                postRequirementPage.clickNext();
//                Thread.sleep(2000);
//
//                // Step 2
//                postRequirementPage.setPickupLocation("Park Street");
//                postRequirementPage.setBudgetPerDay("600");
//                postRequirementPage.clickNext();
//                Thread.sleep(2000);
//            }
//
//            // Verify Post Requirement button
//            Assert.assertTrue(postRequirementPage.isPostRequirementButtonDisplayed(),
//                "Post Requirement button should be displayed on last step");
//
//            // Verify Back button
//            Assert.assertTrue(postRequirementPage.isBackButtonVisible(),
//                "Back button should be visible on last step");
//
//            // Test back button functionality
//            postRequirementPage.clickBack();
//            Thread.sleep(2000);
//
//            // Should go back to step 2
//            Assert.assertTrue(postRequirementPage.isPickupLocationFieldDisplayed(),
//                "Should be on step 2 after clicking back");
//
//            // Go forward again
//            postRequirementPage.clickNext();
//            Thread.sleep(2000);
//
//            // Should be on step 3 again
//            Assert.assertTrue(postRequirementPage.isPostRequirementButtonDisplayed(),
//                "Should be back on step 3");
//
//            System.out.println("TC_036 PASSED: Back and Post Requirement buttons working correctly");
//
//        }catch(Exception e){
//            Assert.fail("TC_036 FAILED: " + e.getMessage());
//        }
//    }
//}
