package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AdminDashboardPage extends BasePage {

    public AdminDashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Wait until the 'Verified shops' metric equals expected within timeoutSeconds.
     * Returns true if matched within timeout, false otherwise.
     */
    public boolean waitForVerifiedShopsCountToBe(int expected, int timeoutSeconds) {
        try {
            Duration to = Duration.ofSeconds(timeoutSeconds);
            WebDriverWait localWait = new WebDriverWait(driver, to);
            return localWait.until(d -> {
                try {
                    return getVerifiedShopsCount() == expected;
                } catch (Exception ex) {
                    return false;
                }
            });
        } catch (Exception e) {
            return false;
        }
    }

    @FindBy(xpath = "//h1[normalize-space() = 'Admin Dashboard']")
    WebElement adminWelcomeMsg;

    @FindBy(className = "avatar-btn")
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
    public void clickAdminProfile() {
        // Wait for the button to be clickable
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnAdminProfile));

        // Execute JS Click
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", btn);
        js.executeScript("arguments[0].click();", btn);
    }

    public boolean areAllFiltersVisible() { return filterButtons.size() >= 3; }

    public void clickMonthFilter() {
        // 1. Wait for it to be present in DOM
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Month']")));

        // 2. Use your reliable JS click routine
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", btn);
        js.executeScript("arguments[0].click();", btn);
    }

    public void clickYearFilter() {
        // 1. Wait for it to be present in DOM
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Year']")));

        // 2. Use your reliable JS click routine
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", btn);
        js.executeScript("arguments[0].click();", btn);
    }

    public boolean isDayFilterActive() { return isFilterActive("Day"); }
    public boolean isMonthFilterActive() { return isFilterActive("Month"); }
    public boolean isYearFilterActive() { return isFilterActive("Year"); }

    private boolean isFilterActive(String filterName) {
        try {
            String xpath = String.format("//button[contains(@class,'active') and normalize-space()='%s']", filterName);
            // Changed to visibilityOfElementLocated for accurate rendering checks
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ================= DYNAMIC SHOP LIST VERIFICATIONS =================

    public int getVerifiedShopsCount() {
        try {
            // Locate the stats card that specifically labels 'Verified shops' and read its h3 value.
            String xpath = "//section[@class='stats-grid']//article[.//p[normalize-space()='Verified shops']]//h3";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            String text = driver.findElement(By.xpath(xpath)).getText().trim();
            return Integer.parseInt(text);
        } catch (Exception e) {
            // Fall back to safest default of 0 if parsing fails
            return 0;
        }
    }

    public boolean isShopVisible(String shopName) {
        try {
            // Immediate, non-blocking DOM check for presence of the shop item
            String xpath = String.format("//section[h3[normalize-space()='Pending shop approvals']]//div[contains(@class,'item') and .//strong[normalize-space()='%s']]", shopName);
            return !driver.findElements(By.xpath(xpath)).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait until a specific pending shop (by shopName in the <strong>) disappears from the list.
     * Returns true if the shop disappeared within the timeout, false otherwise.
     */
    public boolean waitForShopToDisappear(String shopName, int timeoutSeconds) {
        String xpath = String.format("//section[h3[normalize-space()='Pending shop approvals']]//div[contains(@class,'item') and .//strong[normalize-space()='%s']]", shopName);
        System.out.println("[DEBUG-WAIT] Starting wait for invisibility of shop '" + shopName + "' (timeout: " + timeoutSeconds + "s)");
        try {
            WebDriverWait local = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            // Fixed: Use native ExpectedCondition for invisibility
            boolean result = local.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
            System.out.println("[DEBUG-WAIT] Shop '" + shopName + "' disappeared successfully");
            return result;
        } catch (Exception e) {
            System.out.println("[DEBUG-WAIT] Timeout waiting for shop '" + shopName + "' to disappear. Still visible.");
            return false;
        }
    }

    /**
     * Diagnostic helper: returns the number of pending shop items that match the given shopName.
     */
    public int countPendingShopItemsByName(String shopName) {
        try {
            String xpath = String.format("//section[h3[normalize-space()='Pending shop approvals']]//div[contains(@class,'item') and .//strong[normalize-space()='%s']]", shopName);
            return driver.findElements(By.xpath(xpath)).size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Refresh the current page and wait briefly for the pending list to be present.
     */
    public void refreshPage() {
        try {
            driver.navigate().refresh();
            // wait for pending approvals section to be present (short wait)
            String pendingSectionXpath = "//section[h3[normalize-space()='Pending shop approvals']]";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pendingSectionXpath)));
        } catch (Exception ignored) {
        }
    }

    /**
     * Returns true if the matching shop item exists but its .actions container contains no buttons.
     * This can indicate the item was processed in-place (actions removed) rather than removed from DOM.
     */
    public boolean isItemActionsEmpty(String shopName) {
        try {
            String xpathActions = String.format("//section[h3[normalize-space()='Pending shop approvals']]//div[contains(@class,'item') and .//strong[normalize-space()='%s']]//div[contains(@class,'actions')]//button", shopName);
            return driver.findElements(By.xpath(xpathActions)).isEmpty();
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

    // ---------- Helpers to operate on the first pending shop (no hardcoded names) ----------
    public boolean hasPendingShops() {
        try {
            String xpath = "//section[h3[normalize-space()='Pending shop approvals']]//div[contains(@class,'item')]";
            wait.until(driver -> driver.findElements(By.xpath(xpath)).size() > 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getFirstShopName() {
        try {
            String firstItemXpath = "//section[h3[normalize-space()='Pending shop approvals']]//div[contains(@class,'item')][1]";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(firstItemXpath)));
            WebElement firstItem = driver.findElement(By.xpath(firstItemXpath));
            WebElement strong = firstItem.findElement(By.xpath(".//strong"));
            return strong.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void approveFirstShop() {
        processFirstShopAction("Approve");
    }

    public void rejectFirstShop() {
        processFirstShopAction("Reject");
    }

    private void processFirstShopAction(String action) {
        String firstItemXpath = "//section[h3[normalize-space()='Pending shop approvals']]//div[contains(@class,'item')][1]";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(firstItemXpath)));
        WebElement firstItem = driver.findElement(By.xpath(firstItemXpath));

        String shopName = "";
        try {
            shopName = firstItem.findElement(By.xpath(".//strong")).getText().trim();
        } catch (Exception ignored) {}

        String btnClass = action.equalsIgnoreCase("Approve") ? "primary-btn" : "ghost-btn";
        String buttonScopedXpath = firstItemXpath + String.format("//div[contains(@class,'actions')]//button[contains(@class,'%s') and normalize-space()='%s']", btnClass, action);

        System.out.println("[DEBUG] About to JS-click '" + action + "' for shopName='" + shopName + "'");

        try {
            // Wait for presence, not necessarily clickable, since we are bypassing layout
            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(buttonScopedXpath)));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 1. Force scroll into view to ensure it isn't blocked by floating headers
            js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", btn);

            // 2. Micro-pause for the UI event loop
            Thread.sleep(500);

            // 3. Dispatch click
            js.executeScript("arguments[0].click();", btn);
            System.out.println("[DEBUG] Successfully executed JS click for '" + action + "'");
        } catch (Exception e) {
            System.out.println("[DEBUG] Exception during JS click: " + e.getMessage());
        }

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        if (!shopName.isEmpty()) {
            String itemByNameXpath = String.format("//section[h3[normalize-space()='Pending shop approvals']]//div[contains(@class,'item') and .//strong[normalize-space()='%s']]", shopName);
            try {
                // Fixed: Wait for invisibility
                shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(itemByNameXpath)));
                return;
            } catch (Exception ignored) {}
        }

        try {
            // Fixed: Wait for invisibility
            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(firstItemXpath)));
        } catch (Exception e) {
            // Do not wait longer here; tests will assert metrics or list state afterwards.
        }
    }

    private void processShopAction(String shopName, String action) {
        String btnClass = action.equalsIgnoreCase("Approve") ? "primary-btn" : "ghost-btn";
        String buttonXpath = String.format("//div[contains(@class,'item') and .//strong[normalize-space()='%s']]//div[contains(@class,'actions')]//button[contains(@class,'%s') and normalize-space()='%s']", shopName, btnClass, action);
        String itemXpath = String.format("//div[contains(@class,'item') and .//strong[normalize-space()='%s']]", shopName);

        System.out.println("[DEBUG] About to JS-click '" + action + "' for shopName='" + shopName + "'");

        try {
            WebElement targetButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(buttonXpath)));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", targetButton);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", targetButton);
            System.out.println("[DEBUG] Successfully executed JS click for '" + action + "'");
        } catch (Exception e) {
            System.out.println("[DEBUG] Exception during JS click: " + e.getMessage());
        }

        try {
            // Fixed: Wait for invisibility
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(itemXpath)));
        } catch (Exception e) {
            // Fallback: wait for the specific button locator to be invisible
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(buttonXpath)));
        }
    }
}