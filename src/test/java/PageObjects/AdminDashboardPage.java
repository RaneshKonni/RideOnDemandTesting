package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AdminDashboardPage extends BasePage {

    public AdminDashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[normalize-space() = 'Admin Dashboard']")
    WebElement adminWelcomeMsg;

    @FindBy(xpath = "//button/span[contains(@class,'avatar')]")
    WebElement btnAdminProfile;

    @FindBy(className = "app-logo")
    WebElement logo;

    @FindBy(xpath = "//h2[text()='Performance overview']")
    WebElement performanceOverviewTitle;

    @FindBy(xpath = "//p[contains(text(), 'Operational metrics')]")
    WebElement operationalMetricsSubtitle;

    @FindBy(css = ".range-btn")
    List<WebElement> filterButtons;

    @FindBy(xpath = "//button[normalize-space()='Month']")
    WebElement btnMonth;

    @FindBy(xpath = "//button[normalize-space()='Year']")
    WebElement btnYear;

    public boolean adminDashboardMessage() {
        wait.until(ExpectedConditions.visibilityOf(adminWelcomeMsg));
        return adminWelcomeMsg.isDisplayed();
    }

    public boolean isLogoVisible() { return logo.isDisplayed(); }
    public boolean isProfileButtonVisible() { return btnAdminProfile.isDisplayed(); }
    public boolean isPerformanceOverviewTitleDisplayed() { return performanceOverviewTitle.isDisplayed(); }
    public boolean isOperationalMetricsTextDisplayed() { return operationalMetricsSubtitle.isDisplayed(); }
    public void clickAdminProfile() { btnAdminProfile.click(); }

    public boolean areAllFiltersVisible() { return filterButtons.size() >= 3; }

    public void clickMonthFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(btnMonth)).click();
    }

    public void clickYearFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(btnYear)).click();
    }

    public boolean isDayFilterActive() { return isFilterActive("Day"); }
    public boolean isMonthFilterActive() { return isFilterActive("Month"); }
    public boolean isYearFilterActive() { return isFilterActive("Year"); }

    private boolean isFilterActive(String filterName) {
        try {
            String xpath = String.format("//button[contains(@class,'active') and normalize-space()='%s']", filterName);
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ================= DYNAMIC SHOP LIST VERIFICATIONS =================

    public int getVerifiedShopsCount() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section[@class='stats-grid']//h3")));
        return Integer.parseInt(driver.findElements(By.xpath("//section[@class='stats-grid']//h3")).get(3).getText());
    }

    public boolean isShopVisible(String shopName) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("item")));
            List<WebElement> currentShopItems = driver.findElements(By.className("item"));
            return currentShopItems.stream().anyMatch(item -> item.getText().contains(shopName));
        } catch (Exception e) {
            return false;
        }
    }

    public void approveShop(String shopName) {
        processShopAction(shopName, "Approve");
    }

    public void rejectShop(String shopName) {
        processShopAction(shopName, "Reject");
    }

    private void processShopAction(String shopName, String action) {
        // Build dynamic layout-insensitive locator using text-scoping
        String xpathExpression = String.format("//div[contains(@class,'item') and .//strong[normalize-space()='%s']]//button[text()='%s']", shopName, action);

        WebElement targetButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
        targetButton.click();

        // Wait strictly until that specific button leaves the DOM structure
        wait.until(ExpectedConditions.stalenessOf(targetButton));
    }
}