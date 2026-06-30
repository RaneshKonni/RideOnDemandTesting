package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AdminProfilePage extends BasePage {

    public AdminProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Target the unique h1 header on the profile page
    @FindBy(xpath = "//h1[normalize-space() = 'Admin Profile']")
    WebElement profileHeader;

    // Target the profile overview card to ensure content loaded
    @FindBy(className = "profile-overview")
    WebElement profileOverviewCard;

    // Avatar locator based on the provided HTML
    @FindBy(className = "profile-avatar")
    WebElement avatarImage;

    // Status pills (Tags in the profile overview)
    @FindBy(css = ".profile-tags .tag")
    List<WebElement> statusPills;

    // Logout / Sign Out button
    @FindBy(className = "logout-btn")
    WebElement btnSignOut;

    public boolean isProfilePageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(profileHeader));
            return profileHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForProfileLoad() {
        wait.until(ExpectedConditions.visibilityOf(profileOverviewCard));
    }

    public boolean isAvatarDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(avatarImage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areStatusPillsDisplayed() {
        try {
            return !statusPills.isEmpty() && statusPills.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Dynamically fetches the value of a profile data box based on its label.
     * Matches the HTML: <span class="label">Field</span><strong>Value</strong>
     */
    public String getProfileFieldValue(String fieldName) {
        try {
            String xpath = String.format("//span[contains(@class, 'label') and normalize-space(text())='%s']/following-sibling::strong", fieldName);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return driver.findElement(By.xpath(xpath)).getText().trim();
        } catch (Exception e) {
            System.out.println("[DEBUG] Failed to find profile field value for: " + fieldName);
            return "";
        }
    }

    public void clickSignOut() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnSignOut));
            btn.click();
        } catch (Exception e) {
            // Fallback to JS click if styling intercepts the standard click
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btnSignOut);
        }
    }
}