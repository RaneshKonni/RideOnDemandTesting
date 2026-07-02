package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class AdminDashboardPage extends BasePage {

    public AdminDashboardPage(WebDriver driver) {
        super(driver);
    }

    // ================= STATIC UI LOCATORS =================
    @FindBy(xpath = "//h1[normalize-space() = 'Admin Dashboard']")
    WebElement adminWelcomeMsg;

    @FindBy(className = "avatar-btn")
    WebElement btnAdminProfile;

    @FindBy(className = "app-logo")
    WebElement logo;

    @FindBy(xpath = "//h2[text()='Performance overview']")
    WebElement performanceOverviewTitle;

    @FindBy(css = ".range-btn")
    List<WebElement> filterButtons;

    @FindBy(xpath = "//button[normalize-space()='Month']")
    WebElement btnMonth;

    @FindBy(xpath = "//button[normalize-space()='Year']")
    WebElement btnYear;

    // ================= STATIC UI METHODS =================
    public boolean adminDashboardMessage() {
        try {
            waitForElementToBeVisible(adminWelcomeMsg);
            return adminWelcomeMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoVisible() { return logo.isDisplayed(); }
    public boolean isPerformanceOverviewTitleDisplayed() { return performanceOverviewTitle.isDisplayed(); }
    public boolean areAllFiltersVisible() { return filterButtons.size() >= 3; }

    public void clickAdminProfile() {
        waitForElementToBeClickable(btnAdminProfile).click();
    }

    public void clickMonthFilter() {
        waitForElementToBeClickable(btnMonth).click();
    }

    public void clickYearFilter() {
        waitForElementToBeClickable(btnYear).click();
    }

    public boolean isDayFilterActive() { return isFilterActive("Day"); }
    public boolean isMonthFilterActive() { return isFilterActive("Month"); }
    public boolean isYearFilterActive() { return isFilterActive("Year"); }

    private boolean isFilterActive(String filterName) {
        try {
            String xpath = String.format("//button[contains(@class,'active') and normalize-space()='%s']", filterName);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ================= DYNAMIC METRICS & LIST VERIFICATIONS =================
    public int getVerifiedShopsCount() {
        try {
            String xpath = "//section[@class='stats-grid']//article[.//p[normalize-space()='Verified shops']]//h3";
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return Integer.parseInt(element.getText().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean waitForVerifiedShopsCountToBe(int expected, int timeoutSeconds) {
        try {
            // Using the inherited 'wait' object directly
            return wait.until(d -> getVerifiedShopsCount() == expected);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasPendingShops() {
        String xpath = "//section[h3[normalize-space()='Pending shop approvals']]//div[contains(@class,'item')]";
        return !driver.findElements(By.xpath(xpath)).isEmpty();
    }

    public String getFirstShopName() {
        try {
            String xpath = "//section[h3[normalize-space()='Pending shop approvals']]//div[contains(@class,'item')][1]//strong";
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    // ================= DYNAMIC SHOP ACTIONS =================
    public void approveFirstShop() {
        processFirstShopAction("Approve", "primary-btn");
    }

    public void rejectFirstShop() {
        processFirstShopAction("Reject", "ghost-btn");
    }

    private void processFirstShopAction(String action, String btnClass) {
        String firstItemXpath = "//section[h3[normalize-space()='Pending shop approvals']]//div[contains(@class,'item')][1]";
        String buttonScopedXpath = firstItemXpath + String.format("//div[contains(@class,'actions')]//button[contains(@class,'%s') and normalize-space()='%s']", btnClass, action);

        try {
            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(buttonScopedXpath)));

            // Reusing the inherited JavascriptExecutor ('js') from your original BasePage
            js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", btn);
            js.executeScript("arguments[0].click();", btn);

            // Reusing your original BasePage invisibility check
            waitForElementInvisibility(By.xpath(firstItemXpath));
        } catch (Exception ignored) {
            // If it fails here, the row vanished instantly or wasn't there
        }
    }

    public boolean waitForShopToDisappear(String shopName, int timeoutSeconds) {
        String xpath = String.format("//section[h3[normalize-space()='Pending shop approvals']]//div[contains(@class,'item') and .//strong[normalize-space()='%s']]", shopName);
        return waitForElementInvisibility(By.xpath(xpath));
    }

    public boolean isItemActionsEmpty(String shopName) {
        try {
            String xpath = String.format("//section[h3[normalize-space()='Pending shop approvals']]//div[contains(@class,'item') and .//strong[normalize-space()='%s']]//div[contains(@class,'actions')]//button", shopName);
            return driver.findElements(By.xpath(xpath)).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}