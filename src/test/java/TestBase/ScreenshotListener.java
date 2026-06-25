package TestBase;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TestNG Listener: Captures screenshot on test failure
 * Saves screenshots to: test-output/screenshots/
 */
public class ScreenshotListener implements ITestListener {

    private static final String SCREENSHOT_DIR = "test-output/screenshots";

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✓ Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("✗ Test failed: " + result.getMethod().getMethodName());
        
        // Try to capture screenshot
        try {
            Object testInstance = result.getInstance();
            if (testInstance instanceof BaseClass) {
                BaseClass baseTest = (BaseClass) testInstance;
                WebDriver driver = baseTest.driver;
                
                if (driver != null) {
                    captureScreenshot(driver, result.getMethod().getMethodName());
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⊘ Test skipped: " + result.getMethod().getMethodName());
    }

    /**
     * Captures screenshot and saves to disk
     */
    private void captureScreenshot(WebDriver driver, String testName) {
        try {
            // Ensure screenshot directory exists
            Files.createDirectories(Paths.get(SCREENSHOT_DIR));
            
            // Generate filename with timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String filename = SCREENSHOT_DIR + File.separator + testName + "_" + timestamp + ".png";
            
            // Capture screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), Paths.get(filename));
            
            System.out.println("Screenshot saved: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(org.testng.ITestContext context) {
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
    }
}

