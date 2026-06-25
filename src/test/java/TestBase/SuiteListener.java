package TestBase;

import org.testng.ISuite;
import org.testng.ISuiteListener;

/**
 * TestNG Listener: Closes shared WebDriver after entire test suite completes
 */
public class SuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        System.out.println("Suite started: " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Suite finished: " + suite.getName());
        // Close shared driver after all tests in suite complete
        BaseClass.closeSharedDriver();
        System.out.println("Shared WebDriver closed after suite completion");
    }
}

