package TestBase;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentReportManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TestNG Listener: Captures screenshot on test failure
 * Saves screenshots to: test-output/screenshots/
 * Also logs results to ExtentReports
 */
public class ScreenshotListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(ScreenshotListener.class);
    private static final String SCREENSHOT_DIR = "test-output/screenshots";

    // ThreadLocal to support parallel execution safely
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription() != null
                ? result.getMethod().getDescription() : "";

        // Create ExtentReports test entry
        ExtentTest test = ExtentReportManager.createTest(testName, description);
        extentTest.set(test);

        logger.info("Test started: {}", testName);
        System.out.println("Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("✓ Test passed: {}", testName);
        System.out.println("✓ Test passed: " + testName);

        // Log pass in ExtentReports
        ExtentTest test = extentTest.get();
        if (test != null) {
            test.log(Status.PASS, "Test passed: " + testName);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.error("✗ Test failed: {}", testName);
        System.out.println("✗ Test failed: " + testName);

        String screenshotPath = null;

        // Try to capture screenshot
        try {
            Object testInstance = result.getInstance();
            if (testInstance instanceof BaseClass) {
                BaseClass baseTest = (BaseClass) testInstance;
                WebDriver driver = baseTest.driver;

                if (driver != null) {
                    screenshotPath = captureScreenshot(driver, testName);
                }
            }
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }

        // Log failure in ExtentReports with screenshot
        ExtentTest test = extentTest.get();
        if (test != null) {
            test.log(Status.FAIL, "Test failed: " + testName);
            test.log(Status.FAIL, "Cause: " + result.getThrowable());

            if (screenshotPath != null) {
                try {
                    test.fail("Screenshot on failure",
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    logger.info("Screenshot attached to ExtentReport for: {}", testName);
                } catch (Exception e) {
                    logger.error("Failed to attach screenshot to ExtentReport: {}", e.getMessage());
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.warn("⊘ Test skipped: {}", testName);
        System.out.println("⊘ Test skipped: " + testName);

        // Log skip in ExtentReports
        ExtentTest test = extentTest.get();
        if (test != null) {
            test.log(Status.SKIP, "Test skipped: " + testName);
            if (result.getThrowable() != null) {
                test.log(Status.SKIP, "Reason: " + result.getThrowable());
            }
        }
    }

    /**
     * Captures screenshot and saves to disk.
     * Returns the absolute path of the saved screenshot (for attaching to reports).
     */
    private String captureScreenshot(WebDriver driver, String testName) {
        try {
            // Ensure screenshot directory exists
            Files.createDirectories(Paths.get(SCREENSHOT_DIR));

            // Generate filename with timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String filename = SCREENSHOT_DIR + File.separator + testName + "_" + timestamp + ".png";

            // Capture screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(filename);
            Files.copy(srcFile.toPath(), destFile.toPath());

            logger.info("Screenshot saved: {}", filename);
            System.out.println("Screenshot saved: " + filename);

            // Return absolute path for ExtentReports attachment
            return destFile.getAbsolutePath();
        } catch (IOException e) {
            logger.error("Error saving screenshot: {}", e.getMessage());
            System.out.println("Error saving screenshot: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(org.testng.ITestContext context) {
        logger.info("Test context started: {}", context.getName());
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        logger.info("Test context finished: {}", context.getName());
        // Flush ExtentReports to write the HTML report
        ExtentReportManager.flush();
    }
}
