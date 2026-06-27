package TestBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

/**
 * TestNG Listener: Closes shared WebDriver after entire test suite completes
 */
public class SuiteListener implements ISuiteListener {

    private static final Logger logger = LogManager.getLogger(SuiteListener.class);

    @Override
    public void onStart(ISuite suite) {
        logger.info("Suite started: {}", suite.getName());
        System.out.println("Suite started: " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        logger.info("Suite finished: {}", suite.getName());
        System.out.println("Suite finished: " + suite.getName());
        // Close shared driver after all tests in suite complete
        //BaseClass.closeSharedDriver();
        logger.info("Shared WebDriver closed after suite completion");
        System.out.println("Shared WebDriver closed after suite completion");
    }
}
