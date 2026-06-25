# RideOnDemand Customer Module Testing Documentation

## Overview
This document describes the comprehensive test suite created for the RideOnDemand application's Customer Module. The test suite covers all customer-related functionalities including Dashboard, Profile, and Post Requirement features.

## Project Structure

### Test Framework Stack
- **Language**: Java 21
- **Testing Framework**: TestNG 7.12.0
- **Selenium WebDriver**: 4.44.0
- **Design Pattern**: Page Object Model (POM)
- **Browser Support**: Chrome, Edge
- **Build Tool**: Maven

### Folder Structure
```
RideOnDemandTest/
├── src/
│   └── test/
│       ├── java/
│       │   ├── DataProviders/          # Test data providers
│       │   ├── PageObjects/            # Page object classes
│       │   ├── TestBase/               # Base classes for test setup
│       │   ├── TestCases/              # Actual test classes
│       │   ├── TestData/               # Test data files (Excel)
│       │   └── utilities/              # Utility classes
│       └── resources/
│           └── config.properties       # Configuration file
├── pom.xml                             # Maven dependencies
├── testng.xml                          # TestNG configuration
└── test-output/                        # Test reports (generated)
```

## Test Cases Implemented

### TS_003: Customer Dashboard Tests
Tests verifying the customer dashboard displays correct content and handles new users properly.

#### TC_020: Verify Dashboard Display Correct Content
- **Status**: ✓ Implemented
- **Method**: `verifyDashboardDisplayCorrectContent()`
- **Assertions**:
  - Dashboard should be displayed
  - Welcome message should contain "Welcome"
  - Active Requirements heading should be visible
  - Vehicle requirement text should be displayed

#### TC_021: Verify Dashboard for New User
- **Status**: ✓ Implemented
- **Method**: `verifyDashboardForNewUser()`
- **Assertions**:
  - Welcome message should be displayed
  - Active Requirements count should be 0
  - My Requirements section should be empty

**File**: `TestCases/TC_CustomerDashboard.java`

---

### TS_004: Customer Dashboard Navigation Tests
Tests verifying navigation from dashboard to other pages.

#### TC_022: Verify Profile Button Navigation
- **Status**: ✓ Implemented
- **Method**: `verifyProfileButtonNavigationFromDashboard()`
- **Assertions**:
  - Profile button should be displayed
  - Clicking profile button navigates to profile page
  - Profile page should be displayed

#### TC_023: Verify Post Requirement Button Navigation
- **Status**: ✓ Implemented
- **Method**: `verifyPostRequirementButtonNavigation()`
- **Assertions**:
  - Post Requirement button should be displayed
  - Clicking post requirement button navigates to post requirement page
  - Post requirement page should be displayed

**File**: `TestCases/TC_CustomerDashboardNavigation.java`

---

### TS_005: Customer Profile Page Tests
Tests verifying all functionalities on the customer profile page.

#### TC_024: Verify Profile Header and Logo
- **Status**: ✓ Implemented
- **Method**: `verifyProfileHeaderAndLogo()`
- **Assertions**:
  - "Profile & Notifications" text should be displayed
  - Logo should be displayed

#### TC_025: Verify Account Details Section
- **Status**: ✓ Implemented
- **Method**: `verifyAccountDetailsSection()`
- **Assertions**:
  - Account Details heading should be displayed
  - Customer email should be displayed and not empty
  - Customer mobile should be displayed and not empty
  - Customer city should be displayed and not empty
  - Sign out button should be displayed

#### TC_026: Verify Notifications Section
- **Status**: ✓ Implemented
- **Method**: `verifyNotificationsSection()`
- **Assertions**:
  - Notifications heading should be displayed
  - Either "No notifications" message or items/unread counts should be displayed

#### TC_027: Verify Back to Dashboard Navigation
- **Status**: ✓ Implemented
- **Method**: `verifyBackToDashboardNavigation()`
- **Assertions**:
  - Should be able to navigate back to dashboard from profile
  - Dashboard should be displayed after navigating back

#### TC_028: Verify Sign Out Functionality
- **Status**: ✓ Implemented
- **Method**: `verifySignOutFunctionality()`
- **Assertions**:
  - Clicking sign out should redirect to auth page
  - URL should contain "auth" or "login"

**File**: `TestCases/TC_CustomerProfilePage.java`

---

### TS_006: Customer Post Requirement Page Tests
Tests verifying all functionalities on the post requirement page.

#### TC_029: Verify Post Requirement Page Content
- **Status**: ✓ Implemented
- **Method**: `verifyPostRequirementPageContent()`
- **Assertions**:
  - Post Requirement page should be displayed
  - "Post Requirement" text should be visible in nav
  - "Create your rental requirement" message should be displayed
  - Step indicator with 3 steps should be displayed

#### TC_030: Verify Navigation to Profile
- **Status**: ✓ Implemented
- **Method**: `verifyNavigationToProfileFromPostRequirement()`
- **Assertions**:
  - Should have option to navigate to profile
  - Profile page should be displayed after navigation

#### TC_031: Verify Invalid Input Handling
- **Status**: ✓ Implemented
- **Method**: `verifyInvalidInputHandling()`
- **Assertions**:
  - Start date field should be displayed
  - End date field should be displayed
  - Pickup location field should be displayed
  - Budget field should be displayed

#### TC_032: Verify Vehicle Type Dropdown
- **Status**: ✓ Implemented
- **Method**: `verifyVehicleTypeDropdown()`
- **Assertions**:
  - Dropdown should be displayed
  - Dropdown should have options
  - Dropdown should contain vehicle options (Bike, Scooter, Car, SUV)

#### TC_033: Verify Date Pickers
- **Status**: ✓ Implemented
- **Method**: `verifyDatePickers()`
- **Assertions**:
  - Start date field should be accessible
  - End date field should be accessible
  - Date pickers should be clickable

#### TC_034: Verify Next Button Functionality
- **Status**: ✓ Implemented
- **Method**: `verifyNextButtonFunctionality()`
- **Assertions**:
  - Next button should be displayed
  - Filling step 1 and clicking next should advance to next step

#### TC_035: Verify Button Visibility Across Steps
- **Status**: ✓ Implemented
- **Method**: `verifyButtonVisibilityAcrossSteps()`
- **Assertions**:
  - Step 1: Back button should NOT be visible, Next button should be visible
  - Step 2: Both buttons should be visible
  - Step 3: Next button should NOT be visible, Post Requirement button should be displayed

#### TC_036: Verify Last Step Buttons Functionality
- **Status**: ✓ Implemented
- **Method**: `verifyLastStepButtonsFunctionality()`
- **Assertions**:
  - Post Requirement button should be displayed on last step
  - Back button should be visible on last step
  - Back button should navigate to previous step
  - Next button should navigate forward again

**File**: `TestCases/TC_CustomerPostRequirementPage.java`

---

## Page Object Classes

### BasePage
- **File**: `PageObjects/BasePage.java`
- **Description**: Base class for all page objects
- **Key Features**:
  - WebDriver initialization
  - WebDriverWait setup (10-second timeout)
  - PageFactory initialization for FindBy annotations

### CustomerDashboardPage
- **File**: `PageObjects/CustomerDashboardPage.java`
- **Description**: Page object for customer dashboard
- **Key Methods**:
  - `isCustomerDashboardDisplayed()` - Verify dashboard presence
  - `getWelcomeMessage()` - Get welcome message text
  - `isActiveRequirementsHeadingDisplayed()` - Verify heading
  - `isVehicleRequirementTextDisplayed()` - Verify requirement text
  - `getActiveRequirementsCount()` - Get number of active requirements
  - `getRequirementItemsCount()` - Get requirement items count
  - `isMyRequirementsSectionEmpty()` - Check if section is empty
  - `clickCustomerProfile()` - Navigate to profile
  - `clickPostRequirement()` - Navigate to post requirement page

### CustomerProfilePage
- **File**: `PageObjects/CustomerProfilePage.java`
- **Description**: Page object for customer profile page
- **Key Methods**:
  - `isProfilePageDisplayed()` - Verify profile page presence
  - `isProfileAndNotificationsTextDisplayed()` - Verify header text
  - `isLogoDisplayed()` - Verify logo presence
  - `isAccountDetailsHeadingDisplayed()` - Verify account section
  - `getCustomerEmail()` - Get customer email
  - `getCustomerMobile()` - Get customer mobile
  - `getCustomerCity()` - Get customer city
  - `isSignOutButtonDisplayed()` - Verify logout button
  - `isNotificationsHeadingDisplayed()` - Verify notifications section
  - `isNoNotificationsMessageDisplayed()` - Check for empty state
  - `clickSignout()` - Perform logout
  - `clickBackToDashboard()` - Navigate back to dashboard

### CustomerPostRequirementPage
- **File**: `PageObjects/CustomerPostRequirementPage.java`
- **Description**: Page object for customer post requirement page
- **Key Methods**:
  - `isPostRequirementPageDisplayed()` - Verify page presence
  - `isNavTextDisplayed()` - Verify nav text
  - `isCreateRequirementTextDisplayed()` - Verify header text
  - `isStepIndicatorDisplayed()` - Verify step indicator
  - `getTotalSteps()` - Get number of steps
  - `setStartDate(String date)` - Set start date
  - `setEndDate(String date)` - Set end date
  - `selectVehicleType(String vehicleType)` - Select vehicle
  - `getVehicleTypeOptions()` - Get dropdown options
  - `setPickupLocation(String location)` - Set pickup location
  - `setBudgetPerDay(String budget)` - Set daily budget
  - `clickNext()` - Go to next step
  - `clickBack()` - Go to previous step
  - `clickPostRequirement()` - Submit requirement
  - `isNextButtonVisible()` - Check next button visibility
  - `isBackButtonVisible()` - Check back button visibility
  - `clickBackToProfile()` - Navigate back to profile
  - `canNavigateToProfile()` - Check if profile navigation available

---

## Configuration

### config.properties
```properties
appUrl = https://frontend-johnprakashbalireddys-projects.vercel.app/auth
```

### BaseClass Configuration
- **BeforeClass**: Initializes WebDriver based on parameter
- **Parameters**: 
  - `browser`: "chrome" or "edge"
- **WebDriver Timeout**: 10 seconds
- **Window Management**: Maximizes browser window
- **AfterClass**: Closes browser and cleans up resources

---

## Running the Tests

### Prerequisites
1. Java 21 or higher installed
2. Chrome browser or Edge browser installed
3. Maven installed
4. Internet connection (for accessing the web application)

### Via Maven
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=TC_CustomerDashboard

# Run specific test method
mvn test -Dtest=TC_CustomerDashboard#verifyDashboardDisplayCorrectContent

# Run with specific browser
mvn test -Dbrowser=edge
```

### Via IDE (IntelliJ/Eclipse)
1. Open the project
2. Right-click on `testng.xml`
3. Select "Run 'testng.xml'" or "Run 'testng.xml' with browser=chrome"

### Via TestNG Directly
```bash
java -cp "target/test-classes:target/classes:path/to/all/dependencies" 
     org.testng.TestNG testng.xml -parameters browser=chrome
```

---

## Test Execution Flow

### Complete Test Suite Flow
```
1. Customer Dashboard Tests (TC_020, TC_021)
   ↓
2. Customer Dashboard Navigation Tests (TC_022, TC_023)
   ↓
3. Customer Profile Page Tests (TC_024-TC_028)
   ↓
4. Customer Post Requirement Page Tests (TC_029-TC_036)
   ↓
5. User Registration Tests (TC_001)
```

### Expected User Journey Tested
```
User Registration → Login → Customer Dashboard 
   → View Profile/Post Requirement 
   → Manage Requirements 
   → Logout
```

---

## Key Features of Test Suite

### 1. Page Object Model (POM)
- Separates test logic from UI interactions
- Easy maintenance of locators
- Reusable methods across test classes

### 2. Comprehensive Logging
- Each test logs its status (PASSED/FAILED)
- Error messages include exception details
- Console output shows test progress

### 3. Wait Strategy
- Implicit waits: 10 seconds
- Explicit waits for element visibility and clickability
- Thread.sleep() for page transitions

### 4. Cross-Browser Support
- Supports Chrome and Edge browsers
- Configurable via TestNG parameters
- Easy to add more browser support

### 5. Data Validation
- Content verification (text, visibility)
- Element presence checks
- State validation (buttons, fields)

---

## Locator Strategy

### XPath Locators Used
All locators are carefully crafted XPath expressions:
- Text-based: `//button[text()='Sign out']`
- Attribute-based: `//input[@formcontrolname='email']`
- Contains: `//h1[contains(text(), 'Welcome')]`
- Following-sibling: `//div[contains(text(), 'Email')]//following::span[1]`

### Benefits
- More flexible than ID/Class selectors
- Survives minor UI changes
- Readable and maintainable

---

## Error Handling

### Exception Handling in Tests
- All test methods wrapped in try-catch
- Failures logged with descriptive messages
- Stack traces captured for debugging
- `Assert.fail()` used to mark test failures

### Retry Logic
- Built into ExpectedConditions waits
- Automatic retry for 10 seconds
- Throws TimeoutException if element not found

---

## Test Data

### Current Test Approach
- Uses dynamic login/registration within tests
- Can be extended to use Excel-based data providers
- Supports parameterized testing via TestNG

### DataProviders Available
- `RegDataProvider`: For registration test data
- Can be extended for other test modules

---

## Reporting

### TestNG Reports Generated
- HTML reports in `test-output/` directory
- XML reports for CI/CD integration
- Summary of all test results
- Failed test details and stacktraces

### Report Locations
- `test-output/index.html` - Main report
- `test-output/emailable-report.html` - Email-friendly report
- `test-output/testng-results.xml` - XML report

---

## Troubleshooting

### Common Issues

#### 1. Element Not Found Exception
**Cause**: XPath doesn't match actual element
**Solution**: 
- Inspect element in browser
- Update XPath in page object
- Verify page has loaded completely

#### 2. Timeout Exception
**Cause**: Element not visible within 10 seconds
**Solution**:
- Increase wait time in BasePage
- Check network connectivity
- Verify server is responsive
- Check for JavaScript errors

#### 3. StaleElementReferenceException
**Cause**: DOM refreshed after element was located
**Solution**:
- Re-locate element after wait
- Use explicit waits instead of storing references

#### 4. ClassCastException
**Cause**: Element type mismatch
**Solution**:
- Verify correct locator is used
- Check element is actually visible
- Inspect element attributes

---

## Best Practices Followed

1. **DRY Principle**: Reusable methods in page objects
2. **Single Responsibility**: Each method does one thing
3. **Clear Naming**: Method names describe what they test
4. **Assertion Messages**: Descriptive failure messages
5. **Wait Strategy**: Proper explicit waits for stability
6. **No Hard Waits**: Thread.sleep() only for transitions
7. **Exception Handling**: Graceful error handling
8. **Code Comments**: Detailed documentation
9. **Consistent Formatting**: Follow POM conventions
10. **Modular Tests**: Each test is independent

---

## Future Enhancements

### Planned Improvements
1. **Database Validation**: Verify data persistence
2. **API Testing**: Backend validation
3. **Performance Testing**: Load and stress testing
4. **Visual Testing**: Screenshot comparisons
5. **Mobile Testing**: Mobile device support
6. **CI/CD Integration**: Automated test execution
7. **Test Reporting**: Advanced analytics and trends
8. **Parallel Execution**: Run tests in parallel for speed

### Adding New Tests
1. Create new test class extending BaseClass
2. Implement page objects for new pages
3. Add test methods with @Test annotation
4. Update testng.xml with new test class
5. Follow existing naming conventions

---

## Dependencies

### Maven Dependencies
```xml
- Selenium WebDriver 4.44.0
- TestNG 7.12.0
- Log4j 2.26.0
- Apache POI 5.5.1
```

### Browser Drivers
- ChromeDriver (auto-managed by WebDriver)
- EdgeDriver (auto-managed by WebDriver)

---

## Support and Maintenance

### For Issues
1. Check TestNG reports in `test-output/`
2. Review browser console for JavaScript errors
3. Verify test prerequisites are met
4. Update page object XPaths if UI changes
5. Check internet connectivity

### Maintenance Tasks
- Review and update XPaths quarterly
- Update dependencies regularly
- Monitor test execution times
- Archive old test reports
- Document any UI changes

---

## Document Metadata

- **Created**: June 25, 2026
- **Version**: 1.0
- **Framework**: Page Object Model
- **Test Framework**: TestNG
- **Total Test Cases**: 17
- **Total Scenarios**: 4
- **Supported Browsers**: Chrome, Edge
- **Java Version**: 21+

---

**End of Documentation**

For questions or improvements, please refer to the test code comments and existing test implementations.

