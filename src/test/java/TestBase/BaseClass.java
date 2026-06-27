package TestBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    private static final Logger logger = LogManager.getLogger(BaseClass.class);

    // Static shared driver: one browser for all tests
    //private static WebDriver sharedDriver;
    public WebDriver driver;
    public Properties p;
    WebDriverWait wait;


    @BeforeClass
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browser) throws IOException {
        logger.info("Setting up browser: {}", browser);

        // If shared driver exists, reuse it; otherwise create new one
//        if (sharedDriver != null) {
//            driver = sharedDriver;
//            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//            return;
//        }

        //loading config properties file
        p = new Properties();
        p.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

        //selecting the browser and setup driver executables via WebDriverManager
        switch(browser.toLowerCase()){
            case "chrome" : 
                //WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "edge" : 
                //WebDriverManager.edgedriver().setup();
                //sharedDriver = new EdgeDriver();
                driver = new EdgeDriver();
                break;

            default :
                logger.error("Invalid browser specified: {}", browser);
                System.out.println("Invalid Browser");
                return;

        }

//        sharedDriver.get(p.getProperty("appUrl"));
//        sharedDriver.manage().window().maximize();
//        driver = sharedDriver;

        driver.get(p.getProperty("appUrl"));
        driver.manage().window().maximize();
//        driver = driver;


        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logger.info("Browser setup complete. Navigated to: {}", p.getProperty("appUrl"));
    }


    // Static method to close shared driver after entire suite
//    public static void closeSharedDriver() {
//        if (sharedDriver != null) {
//            sharedDriver.quit();
//            sharedDriver = null;
//        }
//    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            logger.info("Tearing down browser session");
            driver.quit();
        }
    }

}
