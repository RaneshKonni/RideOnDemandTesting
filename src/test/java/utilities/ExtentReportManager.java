package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Singleton manager for ExtentReports.
 * Generates rich HTML reports at: test-output/ExtentReport.html
 */
public class ExtentReportManager {

    private static ExtentReports extent;
    private static final String REPORT_PATH = "test-output/ExtentReport.html";

    /**
     * Returns the single ExtentReports instance, creating it on first call.
     */
    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
            sparkReporter.config().setDocumentTitle("RideOnDemand - Test Execution Report");
            sparkReporter.config().setReportName("Customer Module Test Results");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Project", "RideOnDemand");
            extent.setSystemInfo("Module", "Customer");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

    /**
     * Creates a new test entry in the report.
     */
    public static ExtentTest createTest(String testName, String description) {
        return getReporter().createTest(testName, description);
    }

    /**
     * Creates a new test entry in the report (without description).
     */
    public static ExtentTest createTest(String testName) {
        return getReporter().createTest(testName);
    }

    /**
     * Flushes/writes the report to disk. Call at the end of the suite.
     */
    public static synchronized void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
