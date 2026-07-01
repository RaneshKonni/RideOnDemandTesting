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
            // Ensure it's clickable
            wait.until(ExpectedConditions.elementToBeClickable(btnSignOut));

            // Force scroll into view to avoid blocking issues
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSignOut);

            // Use JS click to bypass potential UI overlays
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btnSignOut);

            System.out.println("[DEBUG] Sign Out click executed.");
        } catch (Exception e) {
            System.out.println("[DEBUG] Exception during sign out click: " + e.getMessage());
        }
    }
}