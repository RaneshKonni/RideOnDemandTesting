package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class VendorProfilePage extends BasePage {
    private WebDriverWait wait;

    // Precise locators using sibling navigation
    private By fullNameValue = By.xpath("//span[text()='Full name']/following-sibling::strong");
    private By emailValue    = By.xpath("//span[text()='Email']/following-sibling::strong");
    private By mobileValue   = By.xpath("//span[text()='Mobile']/following-sibling::strong");
    private By cityValue     = By.xpath("//span[text()='City']/following-sibling::strong");
    private By shopNameValue = By.xpath("//span[text()='Shop name']/following-sibling::strong");

    private By totalItemsCount = By.xpath("//div[@aria-label='Mail summary']//div[1]/strong");
    private By unreadCount     = By.xpath("//div[@aria-label='Mail summary']//div[2]/strong");

    // Locator for the logout button
    private By signOutButton = By.cssSelector("button.logout-btn");

    public VendorProfilePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForProfileLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fullNameValue));
    }

    public String getTotalItemsCount() {
        return driver.findElement(totalItemsCount).getText();
    }

    public String getUnreadCount() {
        return driver.findElement(unreadCount).getText();
    }

    // Update your waitForProfileLoad to include these metrics for stability
    public void waitForMailSection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalItemsCount));
    }


    public void clickSignOut() {
        wait.until(ExpectedConditions.elementToBeClickable(signOutButton)).click();
    }

    public String getFullName() { return driver.findElement(fullNameValue).getText(); }
    public String getEmail()    { return driver.findElement(emailValue).getText(); }
    public String getMobile()   { return driver.findElement(mobileValue).getText(); }
    public String getCity()     { return driver.findElement(cityValue).getText(); }
    public String getShopName() { return driver.findElement(shopNameValue).getText(); }
}

