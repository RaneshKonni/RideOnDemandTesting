package TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public Properties p;
    WebDriverWait wait;


    @BeforeClass
    @Parameters({"browser"})
    public void setup(String browser) throws IOException {

        //loading config properties file
        FileReader file = new FileReader("C:\\Users\\2494470\\OneDrive - Cognizant\\Desktop\\Testing Project\\RideOnDemandTest\\src\\test\\resources\\config.properties");
        p = new Properties();
        p.load(file);

        //selecting the browser
        switch(browser.toLowerCase()){
            case "chrome" : driver = new ChromeDriver();
                            break;

            case "edge" : driver = new EdgeDriver();
                            break;

            default :
                System.out.println("Invalid Browser");
                return;

        }

        driver.get(p.getProperty("appUrl"));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @AfterClass
    public void teadDown(){
        driver.quit();
    }

}
