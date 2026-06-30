package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminProfilePage extends BasePage {

    public AdminProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Target the unique h1 header on the profile page
    @FindBy(xpath = "//h1[normalize-space() = 'Admin Profile']")
    WebElement profileHeader;

    // Optional: Target the profile overview card to ensure content loaded
    @FindBy(className = "profile-overview")
    WebElement profileOverviewCard;

    public boolean isProfilePageDisplayed() {
        try {
            // Wait for the header to be visible to ensure navigation is complete
            wait.until(ExpectedConditions.visibilityOf(profileHeader));
            return profileHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // You can also add a method to wait specifically for page load if needed
    public void waitForProfileLoad() {
        wait.until(ExpectedConditions.visibilityOf(profileOverviewCard));
    }
}