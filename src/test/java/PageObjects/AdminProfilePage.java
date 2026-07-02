package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AdminProfilePage extends BasePage {

    public AdminProfilePage(WebDriver driver) {
        super(driver);
    }

    // ================= STATIC UI LOCATORS =================
    @FindBy(xpath = "//h1[normalize-space() = 'Admin Profile']")
    WebElement profileHeader;

    @FindBy(className = "profile-overview")
    WebElement profileOverviewCard;

    @FindBy(className = "profile-avatar")
    WebElement avatarImage;

    @FindBy(css = ".profile-tags .tag")
    List<WebElement> statusPills;

    @FindBy(className = "logout-btn")
    WebElement btnSignOut;

    // ================= STATIC UI METHODS =================
    public boolean isProfilePageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(profileHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForProfileLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOf(profileOverviewCard));
        } catch (Exception ignored) {
            // Fails silently; test assertions will catch rendering issues downstream
        }
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
            // Explicitly wait for the list to populate before checking if it's empty
            wait.until(ExpectedConditions.visibilityOfAllElements(statusPills));
            return !statusPills.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    // ================= DYNAMIC PROFILE DATA =================
    public String getProfileFieldValue(String fieldName) {
        try {
            String xpath = String.format("//span[contains(@class, 'label') and normalize-space(text())='%s']/following-sibling::strong", fieldName);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return element.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    // ================= DYNAMIC ACTIONS =================
    public void clickSignOut() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnSignOut));

            // Reusing the inherited JavascriptExecutor ('js') from your original BasePage
            js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", btnSignOut);

            // Standard Selenium click first, fallback to JS if intercepted by UI overlays
            try {
                btnSignOut.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", btnSignOut);
            }
        } catch (Exception ignored) {
            // Action failure will be caught by URL redirect assertions in the test class
        }
    }
}