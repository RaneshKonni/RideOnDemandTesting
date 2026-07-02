package TestBase;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import PageObjects.AuthPage;
import PageObjects.Navbar;
import PageObjects.ProfilePage;
import mapper.User;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class BaseClass {
    public WebDriver driver;
    public Properties properties;
    public Logger logger;

    @BeforeClass
    public void setupTest() throws IOException {
        FileReader file = new FileReader("./src/test/resources/config.properties");
        properties = new Properties();
        properties.load(file);

        logger = LogManager.getLogger(this.getClass());
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {

        switch (browser.toLowerCase()){
            case "chrome":
                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--headless");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-infobars");

                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("credentials_enable_service", false);
                chromePrefs.put("profile.password_manager_enabled", false);
                chromePrefs.put("profile.password_manager_leak_detection", false);

                options.setExperimentalOption("prefs", chromePrefs);

                driver = new ChromeDriver(options);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name:  " + browser);
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(properties.getProperty("url"));
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public String captureScreen(String tname) throws IOException {
        File directory = new File(System.getProperty("user.dir")+"/screenshots/");
        if(!directory.exists()){
            directory.mkdir();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir")+"/screenshots/"+tname+"_"+timeStamp+".png";
        File targetFile = new File(targetFilePath);
        FileUtils.copyFile(sourceFile, targetFile);

        return targetFilePath;
    }


    public boolean loginUser(String role, String email, String password){
//        logger.info("Switching to login page...");
        AuthPage authPage = new AuthPage(driver);
        switch (role){
            case "Customer":
                authPage.clickCustomerButton();
                break;
            case "Vendor":
                authPage.clickVendorButton();
                break;
            case "Admin":
                authPage.clickAdminButton();
        }
        authPage.clickLoginButton();
        logger.info("Providing user credentials...");
        authPage.setEmail(email);
        authPage.setPassword(password);
        logger.info("Trying to logging in...");
        authPage.clickActualLoginButton();
        if(role.isEmpty()) return false;
        return authPage.waitForUrlToContain("/"+role.toLowerCase());
    }

    public boolean registerUser(User user){
        AuthPage authPage = new AuthPage(driver);
        logger.info("Navigating to Registration page from Home page...");
        authPage.clickRegisterButton();

        switch (user.getRole()){
            case "Customer":
                logger.info("Clicking Customer button");
                authPage.clickCustomerButton();
                break;
            case "Vendor":
                logger.info("Clicking Vendor button");
                authPage.clickVendorButton();
                authPage.setShopName(user.getShopName());
                break;
            case "Admin":
                authPage.clickAdminButton();
                break;
        }

        authPage.setFullName(user.getFullName());
        authPage.setEmail(user.getEmail());
        authPage.setPassword(user.getPassword());
        authPage.setMobile(user.getMobile());
        authPage.setCity(user.getCity());
        authPage.clickRegisterAndContinueButton();

        if(user.getRole().isEmpty()){
            return false;
        }

        return authPage.waitForUrlToContain("/"+user.getRole().toLowerCase());
    }

    public boolean logoutUser() throws InterruptedException {
        Navbar navbar = new Navbar(driver);
        navbar.clickProfileButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickSignOutButton();
        return profilePage.waitForUrlToContain("/auth");
    }

    public void verifyErrorMessagesWhileRegisteringUser(User user, String errorMsg){
        if(registerUser(user)) Assert.fail("User should not be able to register");
        AuthPage authPage = new AuthPage(driver);
        authPage.clickRegisterAndContinueButton();
        Assert.assertEquals(authPage.getErrorMessage(), errorMsg);
    }

    public void verifyErrorMsgWhileUserLogin(String role, String email, String password, String errorMsg){
        if(loginUser(role, email, password)) Assert.fail("User should not be able to login");
        AuthPage authPage = new AuthPage(driver);
        authPage.clickActualLoginButton();
        Assert.assertEquals(authPage.getErrorMessage(), errorMsg);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
