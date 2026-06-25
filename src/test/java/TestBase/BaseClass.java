package TestBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    // Static shared driver: one browser for all tests
    private static WebDriver sharedDriver;
    public WebDriver driver;
    public Properties p;
    WebDriverWait wait;


    @BeforeClass
    @Parameters({"browser"})
    public void setup(String browser) throws IOException {

        // If shared driver exists, reuse it; otherwise create new one
        if (sharedDriver != null) {
            driver = sharedDriver;
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return;
        }

        //loading config properties file
        FileReader file = new FileReader("C:\\Users\\2494470\\OneDrive - Cognizant\\Desktop\\Testing Project\\RideOnDemandTest\\src\\test\\resources\\config.properties");
        p = new Properties();
        p.load(file);

        //selecting the browser and setup driver executables via WebDriverManager
        switch(browser.toLowerCase()){
            case "chrome" : 
                WebDriverManager.chromedriver().setup();
                sharedDriver = new ChromeDriver();
                break;

            case "edge" : 
                WebDriverManager.edgedriver().setup();
                sharedDriver = new EdgeDriver();
                break;

            default :
                System.out.println("Invalid Browser");
                return;

        }

        sharedDriver.get(p.getProperty("appUrl"));
        sharedDriver.manage().window().maximize();
        driver = sharedDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    // Static method to close shared driver after entire suite
    public static void closeSharedDriver() {
        if (sharedDriver != null) {
            sharedDriver.quit();
            sharedDriver = null;
        }
    }
}
