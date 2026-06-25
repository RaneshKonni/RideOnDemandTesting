# RideOnDemand Customer Module Test Suite

## Quick Start Guide

### Overview
This is a comprehensive Selenium WebDriver test suite for testing the RideOnDemand application's Customer Module. It uses the Page Object Model (POM) design pattern with TestNG framework for robust and maintainable automation testing.

### What's Included
✓ 17 Test Cases (TC_020 to TC_036)
✓ 4 Test Scenarios covering Customer Module functionality
✓ 3 Enhanced Page Objects (CustomerDashboardPage, CustomerProfilePage, CustomerPostRequirementPage)
✓ Complete test documentation
✓ Cross-browser support (Chrome, Edge)
✓ Comprehensive error handling and reporting

### Test Coverage

#### Customer Dashboard (2 tests)
- TC_020: Verify dashboard displays correct content
- TC_021: Verify dashboard for new user

#### Customer Dashboard Navigation (2 tests)
- TC_022: Profile button navigation
- TC_023: Post requirement button navigation

#### Customer Profile Page (5 tests)
- TC_024: Profile header and logo visibility
- TC_025: Account details verification
- TC_026: Notifications section verification
- TC_027: Back to dashboard navigation
- TC_028: Sign out functionality

#### Customer Post Requirement Page (8 tests)
- TC_029: Page content and structure
- TC_030: Navigation to profile page
- TC_031: Invalid input handling
- TC_032: Vehicle type dropdown
- TC_033: Date picker functionality
- TC_034: Next button functionality
- TC_035: Button visibility across steps
- TC_036: Back and post requirement buttons

### Prerequisites

- **Java**: Version 21 or higher
- **Maven**: 3.6+
- **Browsers**: Chrome 118+ or Edge 118+
- **Internet**: Required to access the web application

### Installation

1. **Clone/Extract Project**
```bash
cd "RideOnDemandTest"
```

2. **Install Dependencies**
```bash
mvn clean install
```

3. **Verify Setup**
```bash
mvn --version
java -version
```

### Configuration

Edit `src/test/resources/config.properties`:
```properties
appUrl = https://frontend-johnprakashbalireddys-projects.vercel.app/auth
```

Update `testng.xml` to change browser parameter:
```xml
<parameter name="browser" value="chrome"></parameter>
<!-- OR -->
<parameter name="browser" value="edge"></parameter>
```

### Running Tests

#### Run All Tests
```bash
mvn test
```

#### Run Specific Test Class
```bash
mvn test -Dtest=TC_CustomerDashboard
mvn test -Dtest=TC_CustomerDashboardNavigation
mvn test -Dtest=TC_CustomerProfilePage
mvn test -Dtest=TC_CustomerPostRequirementPage
```

#### Run Specific Test Method
```bash
mvn test -Dtest=TC_CustomerDashboard#verifyDashboardDisplayCorrectContent
```

#### Run with Different Browser
```bash
mvn test -Dbrowser=edge
```

#### Run via TestNG XML
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Test Reports

After running tests, reports are generated in:
- **HTML Report**: `test-output/index.html`
- **Email Report**: `test-output/emailable-report.html`
- **XML Report**: `test-output/testng-results.xml`

Open `test-output/index.html` in browser to view detailed results.

### Project Structure

```
RideOnDemandTest/
├── src/test/java/
│   ├── TestCases/
│   │   ├── TC_CustomerDashboard.java
│   │   ├── TC_CustomerDashboardNavigation.java
│   │   ├── TC_CustomerProfilePage.java
│   │   ├── TC_CustomerPostRequirementPage.java
│   │   └── TC_001_UserRegistration.java
│   ├── PageObjects/
│   │   ├── BasePage.java
│   │   ├── AuthPage.java
│   │   ├── CustomerDashboardPage.java
│   │   ├── CustomerProfilePage.java
│   │   └── CustomerPostRequirementPage.java
│   ├── TestBase/
│   │   └── BaseClass.java
│   ├── DataProviders/
│   │   └── RegDataProvider.java
│   └── utilities/
│       └── ExcelUtility.java
├── src/test/resources/
│   └── config.properties
├── pom.xml
├── testng.xml
└── TEST_DOCUMENTATION.md
```

### Key Features

1. **Page Object Model**
   - Centralized element locators
   - Reusable page methods
   - Easy maintenance

2. **Robust Waits**
   - 10-second explicit waits
   - Element visibility checks
   - Clickability verification

3. **Comprehensive Assertions**
   - Element presence verification
   - Text content validation
   - Navigation verification

4. **Error Handling**
   - Try-catch blocks in all tests
   - Descriptive error messages
   - Exception logging

5. **Cross-Browser Support**
   - Chrome browser support
   - Edge browser support
   - Easy to add more

### Test Execution Example

```
$ mvn test

...
[INFO] Building RideOnDemandTest 1.0-SNAPSHOT
[INFO] ================================[ jar ]=================================
...
[INFO] Running TestSuite
...
TC_020 PASSED: Dashboard displays correct content
TC_021 PASSED: Dashboard for new user verified
TC_022 PASSED: Profile button navigates to profile page
TC_023 PASSED: Post Requirement button navigates to post requirement page
TC_024 PASSED: Profile header and logo are visible
TC_025 PASSED: Account details section verified
TC_026 PASSED: Notifications section with items displayed
TC_027 PASSED: Can navigate back to dashboard from profile
TC_028 PASSED: Sign out functionality works correctly
TC_029 PASSED: Post requirement page content verified with 3 steps
TC_030 PASSED: Can navigate to profile from post requirement page
TC_031 PASSED: Empty field validation verified
TC_032 PASSED: Vehicle type dropdown verified
TC_033 PASSED: Date pickers are accessible
TC_034 PASSED: Next button functionality verified
TC_035 PASSED: Button visibility verified across steps
TC_036 PASSED: Back and Post Requirement buttons working correctly
...
[INFO] Tests run: 17, Failures: 0, Errors: 0, Skipped: 0
[INFO] ========================================
[INFO] BUILD SUCCESS
```

### Troubleshooting

#### Test Fails with "Element not found"
- Verify URL is correct in config.properties
- Check browser is not blocked by proxy
- Ensure application is deployed and accessible
- Review updated XPath locators in page objects

#### Test Fails with "Timeout Exception"
- Increase wait time in BasePage (currently 10 seconds)
- Check application server is responding
- Verify network connectivity
- Check for JavaScript errors in browser console

#### Maven Build Fails
```bash
# Clean and rebuild
mvn clean install

# Update dependencies
mvn dependency:resolve

# Check Java version
java -version
```

### Maintenance

#### Adding New Tests
1. Create new test class extending BaseClass
2. Implement page objects for new pages
3. Add test methods with @Test annotation
4. Update testng.xml with new test

#### Updating Locators
1. Inspect element in browser
2. Update XPath in corresponding page object
3. Test updated locator
4. Commit changes

#### Updating Dependencies
```bash
mvn versions:display-dependency-updates
mvn dependency:update-check
```

### IDE Setup

#### IntelliJ IDEA
1. Open Project
2. Configure SDK: File → Project Structure → SDKs
3. Select Java 21
4. Run Tests: Right-click → Run or Debug

#### Eclipse
1. Import → Existing Maven Project
2. Select project folder
3. Right-click → Run As → Maven test

### CI/CD Integration

For GitHub Actions/Jenkins/GitLab CI, use:
```bash
mvn clean test -DsuiteXmlFile=testng.xml -Dbrowser=chrome
```

### Important Notes

⚠️ **Important Considerations:**
1. Tests assume user is already logged in as customer
2. Update XPaths if UI changes significantly
3. Network connectivity required for tests
4. Browser must support JavaScript
5. Cookies and cache may need clearing between runs
6. Some tests may take 30-60 seconds to complete

📝 **Best Practices:**
1. Run tests on consistent environment
2. Keep test data separate from test logic
3. Update documentation when adding tests
4. Review test reports after each execution
5. Verify actual vs. expected results carefully

### Support Resources

- **TestNG Documentation**: https://testng.org/doc/
- **Selenium Documentation**: https://selenium.dev/documentation/
- **Maven Documentation**: https://maven.apache.org/guides/

### Version Information

- Framework Version: 1.0
- Created: June 25, 2026
- Framework: Page Object Model (POM)
- Test Framework: TestNG 7.12.0
- Selenium: 4.44.0
- Java: 21

### Contact & Improvements

For improvements or issues:
1. Review TEST_DOCUMENTATION.md for detailed information
2. Check test logs in test-output directory
3. Verify prerequisites and configuration
4. Update page object XPaths as needed
5. Document and share improvements with team

---

**Happy Testing! 🚀**

