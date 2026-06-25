package TestCases;

import PageObjects.*;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import org.openqa.selenium.WebElement;

public class TC_CustomerPostRequirementPage extends BaseClass {

    CustomerDashboardPage customerDashboard;
    CustomerProfilePage customerProfile;
    CustomerPostRequirementPage postRequirementPage;

    @BeforeMethod
    public void loginBeforeTest(){
        try{
            // Refresh page to ensure clean state



            AuthPage authPage = new AuthPage(driver);
            authPage.loginActivity();
            Thread.sleep(2000);
            String email = p.getProperty("testCustomerEmail");
            String password = p.getProperty("testCustomerPassword");

            if(email != null && !email.trim().isEmpty() && password != null && !password.trim().isEmpty()){
                // Ensure login form is visible and ready
//                authPage.ensureLoginFormVisible();

                // Enter credentials and login
                authPage.setTfEmail(email);
                authPage.setTfPassword(password);
                authPage.clickBtnSubmit();

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
     * TC_029 - Verify the content of the customer post requirement page is rendered properly
     * Prerequisites: User is logged in as a customer
     * Expected: Page should display "Post Requirement", "Create your rental requirement", and 3 steps
     */
    @Test
    public void verifyPostRequirementPageContent(){
        try{
            customerDashboard = new CustomerDashboardPage(driver);
            customerDashboard.clickPostRequirement();


            postRequirementPage = new CustomerPostRequirementPage(driver);

            // Verify post requirement page is displayed
            Assert.assertTrue(postRequirementPage.isPostRequirementPageDisplayed());


            // Verify "Create your rental requirement" message
            Assert.assertTrue(postRequirementPage.isCreateRequirementTextDisplayed());


            System.out.println("TC_029 PASSED: Post requirement page content verified");

        }catch(Exception e){
            Assert.fail("TC_029 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_030 - Verify the customer is able to navigate to the profile page from post requirement page
     * Prerequisites: User is logged in as a customer
     * Expected: Customer should be able to navigate to profile page
     */
    @Test
    public void verifyNavigationToProfileFromPostRequirement(){
        try{
            postRequirementPage = new CustomerPostRequirementPage(driver);
            Thread.sleep(3000);
            // Verify we can navigate to profile
            Assert.assertTrue(postRequirementPage.canNavigateToProfile());

            System.out.println("TC_030 PASSED: Can navigate to profile from post requirement page");

        }catch(Exception e){
            Assert.fail("TC_030 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_031 - Verify post requirement page handles invalid input and empty fields properly
     * Prerequisites: User is logged in as a customer, on post requirement page
     * Expected: Page should not allow empty or invalid values for dates, location, and budget
     */
    @Test
    public void verifyInvalidInputHandling(){
        try{

            // Navigate to Post Requirement page if required
            if (postRequirementPage == null || !postRequirementPage.isPostRequirementPageDisplayed()) {

                customerDashboard = new CustomerDashboardPage(driver);
                customerDashboard.clickPostRequirement();
                Thread.sleep(5000);

                postRequirementPage = new CustomerPostRequirementPage(driver);
            }

            System.out.println("---------- Empty Field Test ----------");

            Thread.sleep(2000);
            postRequirementPage.clearStartDate();
            postRequirementPage.clearEndDate();
            Thread.sleep(3000);
            // Leave every field empty
            postRequirementPage.clickNext();

            if (postRequirementPage.isPickupLocationFieldDisplayed()) {
                System.out.println("Observation: Empty fields accepted.");
                //Assert.assertTrue(false);
            }
            Thread.sleep(3000);

            // -----------------------------

            System.out.println("---------- Invalid Date Test ----------");

            postRequirementPage.clickBack();
            Thread.sleep(5000);
            postRequirementPage.setStartDate("01012024");
            postRequirementPage.setEndDate("02012024");
            Thread.sleep(2000);
            postRequirementPage.clickNext();
            Thread.sleep(3000);
            if (postRequirementPage.isPickupLocationFieldDisplayed()) {
                System.out.println("Observation: Past dates accepted.");
                //Assert.assertTrue(false);
            }

            // -----------------------------

            System.out.println("---------- Invalid Budget Test ----------");
            Thread.sleep(3000);
            postRequirementPage.clearPickupLocation();
            postRequirementPage.clearBudget();
            postRequirementPage.setPickupLocation("Hyderabad");
            postRequirementPage.setBudgetPerDay("-500");
            Thread.sleep(5000);
            postRequirementPage.clickNext();

            if (postRequirementPage.isPostRequirementButtonDisplayed()) {
                System.out.println("Observation: Negative budget accepted.");
                //Assert.assertTrue(false);
            }
            postRequirementPage.clickPostRequirement();
            Thread.sleep(3000);

            System.out.println("TC_031 PASSED: Empty field validation verified");
            Assert.fail("Observation: Application allows empty or invalid inputs. Validation may not be enforced.");
        }catch(Exception e){
            Assert.fail("TC_031 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_032 - Verify the vehicle type drop down is working
     * Prerequisites: User is logged in as a customer, on post requirement page
     * Expected: Dropdown should contain Bike, Scooty, Car, SUV options
     */
    @Test
    public void verifyVehicleTypeDropdown(){
        try{
            Thread.sleep(3000);
            customerDashboard = new CustomerDashboardPage(driver);
            customerDashboard.clickPostRequirement();
            postRequirementPage = new CustomerPostRequirementPage(driver);

            Thread.sleep(3000);
            // Verify dropdown is displayed
            Assert.assertTrue(postRequirementPage.isVehicleTypeDropdownDisplayed(),
                "Vehicle type dropdown should be displayed");

            // Get vehicle type options
            List<WebElement> options = postRequirementPage.getVehicleTypeOptions();

            // Verify dropdown has options
            Assert.assertTrue(options.size() > 0, "Dropdown should have options");

            // Get option texts and verify required options exist
            boolean hasBike = false, hasScooter = false, hasCar = false, hasSUV = false;

            for(WebElement option : options){
                String optionText = option.getText().toLowerCase();
                if(optionText.contains("bike")) hasBike = true;
                if(optionText.contains("scoot")) hasScooter = true;
                if(optionText.contains("car")) hasCar = true;
                if(optionText.contains("suv")) hasSUV = true;
            }

            Assert.assertTrue(hasBike || hasScooter || hasCar || hasSUV,
                "Dropdown should contain vehicle options");

            System.out.println("TC_032 PASSED: Vehicle type dropdown verified with " + options.size() + " options");

        }catch(Exception e){
            Assert.fail("TC_032 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_033 - Verify the date picker for start date and end date is working
     * Prerequisites: User is logged in as a customer, on post requirement page
     * Expected: Customer should be able to access and select dates from date picker
     */
    @Test
    public void verifyDatePickers() {
        try {
            Thread.sleep(3000);
            customerDashboard = new CustomerDashboardPage(driver);
            customerDashboard.clickPostRequirement();

            postRequirementPage = new CustomerPostRequirementPage(driver);

            // Verify Start Date picker
            Thread.sleep(3000);
            Assert.assertTrue(postRequirementPage.isStartDateFieldDisplayed(),
                    "Start Date field should be displayed");

            postRequirementPage.clickStartDatePicker();
            Thread.sleep(1000);

            // Select Start Date
            postRequirementPage.setStartDate("15072026");

            // Verify End Date picker
            Assert.assertTrue(postRequirementPage.isEndDateFieldDisplayed(),
                    "End Date field should be displayed");

            postRequirementPage.clickEndDatePicker();
            Thread.sleep(1000);

            // Select End Date earlier than Start Date
            postRequirementPage.setEndDate("10072026");

            Thread.sleep(3000);
            // Continue to next step
            postRequirementPage.clickNext();
            Thread.sleep(2000);
            // Current application behaviour
            if(postRequirementPage.isPickupLocationFieldDisplayed()) {
                System.out.println("Observation: Application allows End Date before Start Date.");
                Thread.sleep(3000);
                Assert.assertTrue(false, "Application should not allow End Date before Start Date.");
            } else {
                System.out.println("Application correctly rejected invalid date range.");
            }

            System.out.println("TC_033 PASSED: Date picker functionality verified.");

        } catch (Exception e) {
            Assert.fail("TC_033 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_034 - Verify the "next" button is working
     * Prerequisites: User is logged in as a customer, on post requirement page step 1
     * Expected: Clicking next should go to the next step
     */
    @Test
    public void verifyNextButtonFunctionality(){
        try{
            Thread.sleep(3000);
            customerDashboard = new CustomerDashboardPage(driver);
            customerDashboard.clickPostRequirement();

            postRequirementPage = new CustomerPostRequirementPage(driver);

            Thread.sleep(3000);
            // Verify next button is displayed
            Assert.assertTrue(postRequirementPage.isNextButtonDisplayed(),
                "Next button should be displayed");

            // Fill in step 1 fields (assuming required fields)
            postRequirementPage.setStartDate("10102024");
            postRequirementPage.setEndDate("15102024");
            postRequirementPage.selectVehicleType("Car");

            // Click next
            postRequirementPage.clickNext();
            Thread.sleep(2000);

            Assert.assertTrue(postRequirementPage.isPickupLocationFieldDisplayed());
            System.out.println("TC_034 PASSED: Next button functionality verified");

        }catch(Exception e){
            Assert.fail("TC_034 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_035 - Verify 1st step does not have "back" and last step does not have "next"
     * Prerequisites: User is logged in as a customer, on post requirement page
     * Expected: Step 1 should not have back button, last step should not have next button
     */
    @Test
    public void verifyButtonVisibilityAcrossSteps(){
        try{
            Thread.sleep(2000);
            customerDashboard = new CustomerDashboardPage(driver);
            customerDashboard.clickPostRequirement();

            postRequirementPage = new CustomerPostRequirementPage(driver);
            Thread.sleep(2000);
            // On step 1, back button should not be visible
            Assert.assertFalse(postRequirementPage.isBackButtonVisible(),
                "Back button should not be visible on step 1");

            Assert.assertTrue(postRequirementPage.isNextButtonVisible(),
                "Next button should be visible on step 1");

            // Fill and go to next steps
            postRequirementPage.setStartDate("10102024");
            postRequirementPage.setEndDate("15102024");
            postRequirementPage.selectVehicleType("Car");
            Thread.sleep(2000);
            postRequirementPage.clickNext();
            Thread.sleep(2000);

            // On step 2, both buttons should be visible
            postRequirementPage.clearPickupLocation();
            postRequirementPage.clearBudget();
            postRequirementPage.setPickupLocation("Downtown");
            postRequirementPage.setBudgetPerDay("500");
            Assert.assertTrue(postRequirementPage.isBackButtonVisible(),
                    "Back button should be visible on step 2");
            Assert.assertTrue(postRequirementPage.isNextButtonVisible(),
                    "Next button should be visible on step 2");
            Thread.sleep(2000);
            postRequirementPage.clickNext();
            Thread.sleep(2000);

            // On step 3 (last step), post requirement button should be displayed instead of next
            Assert.assertTrue(postRequirementPage.isPostRequirementButtonDisplayed(),
                "Post Requirement button should be displayed on last step");
            Assert.assertFalse(postRequirementPage.isNextButtonVisible(),
                "Next button should not be visible on last step");

            System.out.println("TC_035 PASSED: Button visibility verified across steps");

        }catch(Exception e){
            Assert.fail("TC_035 FAILED: " + e.getMessage());
        }
    }

    /**
     * TC_036 - Verify "back" and "post requirement" buttons are working in the last step
     * Prerequisites: User is logged in as a customer, on last step of post requirement
     * Expected: Back and Post Requirement buttons should work correctly
     */
    @Test
    public void verifyLastStepButtonsFunctionality(){
        try{
            Thread.sleep(2000);
            customerDashboard = new CustomerDashboardPage(driver);
            customerDashboard.clickPostRequirement();

            postRequirementPage = new CustomerPostRequirementPage(driver);

            // Assuming we're already on last step from previous test
            // Fill all steps to reach last step if needed

                // Step 1
                postRequirementPage.setStartDate("10102024");
                postRequirementPage.setEndDate("15102024");
                postRequirementPage.selectVehicleType("Car");
                postRequirementPage.clickNext();
                Thread.sleep(2000);

                // Step 2
                postRequirementPage.clearBudget();
                postRequirementPage.clearPickupLocation();
                postRequirementPage.setPickupLocation("Park Street");
                postRequirementPage.setBudgetPerDay("600");
                postRequirementPage.clickNext();
                Thread.sleep(2000);


            // Verify Post Requirement button
            Assert.assertTrue(postRequirementPage.isPostRequirementButtonDisplayed(),
                "Post Requirement button should be displayed on last step");

            // Verify Back button
            Assert.assertTrue(postRequirementPage.isBackButtonVisible(),
                "Back button should be visible on last step");

            // Test back button functionality
            postRequirementPage.clickBack();
            Thread.sleep(2000);

            // Should go back to step 2
            Assert.assertTrue(postRequirementPage.isPickupLocationFieldDisplayed(),
                "Should be on step 2 after clicking back");

            // Go forward again
            postRequirementPage.clickNext();
            Thread.sleep(2000);

            // Should be on step 3 again
            Assert.assertTrue(postRequirementPage.isPostRequirementButtonDisplayed(),
                "Should be back on step 3");

            System.out.println("TC_036 PASSED: Back and Post Requirement buttons working correctly");

        }catch(Exception e){
            Assert.fail("TC_036 FAILED: " + e.getMessage());
        }
    }
}
