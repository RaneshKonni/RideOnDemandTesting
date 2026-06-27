package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class VendorDashboardPage extends BasePage {
    public VendorDashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[normalize-space() = 'Vendor Dashboard']")
    WebElement vendorWelcomeMessage;

    @FindBy(xpath = "//button/span[contains(@class,'avatar')]")
    WebElement btnVendorProfile;

    @FindBy(xpath = "//strong[normalize-space()='11']")
    WebElement requirementCount;

    @FindBy(xpath = "//strong[normalize-space()='1']")
    WebElement offerCount;

    @FindBy(xpath = "//div[@class='stat-tile stat-tile-highlight']//strong[contains(text(),'Bike')]")
    WebElement selectedRequirementName;

    @FindBy(xpath = "//small[normalize-space()='#1']")
    WebElement selectedRequirementId;


    //metrics cards
    @FindBy(xpath = "//div[@aria-label='Vendor dashboard overview']//div[1]")
    WebElement requirementCard;

    @FindBy(xpath = "//div[@aria-label='Vendor dashboard overview']//div[2]")
    WebElement offerCard;

    @FindBy(xpath = "//div[@aria-label='Vendor dashboard overview']//div[3]")
    WebElement selectedRequirementCard;

    //offer form container displayed
    @FindBy(css = ".card.panel.panel-sticky")
    WebElement offerFormContainer;


    //send offer button
    @FindBy(className = "ghost-btn")
            WebElement btnSendOffer;


    List<WebElement> requirementItems = driver.findElements(By.xpath("//div[@class='item']"));


    public boolean vendorDashboardMessage() {

        wait.until(ExpectedConditions.visibilityOf(vendorWelcomeMessage));
        return vendorWelcomeMessage.isDisplayed();
    }

    public boolean isMetricsVisible() {

        wait.until(ExpectedConditions.visibilityOf(requirementCard));
        wait.until(ExpectedConditions.visibilityOf(offerCard));
        wait.until(ExpectedConditions.visibilityOf(selectedRequirementCard));

        return requirementCard.isDisplayed() && offerCard.isDisplayed() && selectedRequirementCard.isDisplayed();
    }

    //is profile button visible
    public boolean isProfileButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(btnVendorProfile));
        return btnVendorProfile.isDisplayed();
    }

    public void clickVendorProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(btnVendorProfile));
        btnVendorProfile.click();
    }

    // Demand Feed List Methods
    public boolean isLiveRequirementsHeadingDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[normalize-space()='Live requirements']")));
            return true;
        } catch (Exception e) {
            System.out.println("Live requirements heading not found: " + e.getMessage());
            return false;
        }
    }

    public void scrollToLiveRequirements() {
        try {
            WebElement liveReqHeading = driver.findElement(By.xpath("//h3[normalize-space()='Live requirements']"));

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", liveReqHeading);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error scrolling to live requirements: " + e.getMessage());
        }
    }



    public boolean isDemandFeedListDisplayed() {
        try {
            scrollToLiveRequirements();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@class='card panel']")));
            return driver.findElement(By.xpath("//article[@class='card panel']")).isDisplayed();
        } catch (Exception e) {
            System.out.println("Demand feed container not visible: " + e.getMessage());
            return false;
        }
    }

    public int getRequirementItemsCount() {
        try {
            scrollToLiveRequirements();
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='item']")));
            return driver.findElements(By.xpath("//div[@class='item']")).size();
        } catch (Exception e) {
            System.out.println("Error getting requirement items count: " + e.getMessage());
            return 0;
        }
    }

    public String getRequirementVehicleType(int index) {
        try {
            // Wraps your XPath: (//div[@class='item-main']/strong)[1], [2], etc.
            String directXpath = "(//div[@class='item-main']/strong)[" + (index + 1) + "]";
            return driver.findElement(By.xpath(directXpath)).getText();
        } catch (Exception e) {
            return ""; // Returns empty string if the index doesn't exist
        }
    }

    public String getRequirementLocation(int index) {
        try {
            // Wraps your XPath: (//div[@class='item-main']/strong)[1], [2], etc.
            String directXpath = "(//div[@class='item-main']/p)[" + (index + 1) + "]";
            return driver.findElement(By.xpath(directXpath)).getText();
        } catch (Exception e) {
            return ""; // Returns empty string if the index doesn't exist
        }
    }

    public String getRequirementPrice(int index) {
        try {
            // Wraps your XPath: (//div[@class='item-main']/strong)[1], [2], etc.
            String directXpath = "(//div[@class='item-meta'])[" + (index + 1) + "]/span[1]";
            return driver.findElement(By.xpath(directXpath)).getText();
        } catch (Exception e) {
            return ""; // Returns empty string if the index doesn't exist
        }
    }





    public String getRequirementDates(int index) {
        try {
            // Wraps your XPath: (//div[@class='item-main']/strong)[1], [2], etc.
            String directXpath = "(//div[@class='item-meta'])[" + (index + 1) + "]/span[2]";
            return driver.findElement(By.xpath(directXpath)).getText();
        } catch (Exception e) {
            return ""; // Returns empty string if the index doesn't exist
        }
    }

    public String getRequirementNotes(int index) {
        try {
            // Wraps your XPath: (//div[@class='item-main']/strong)[1], [2], etc.
            String directXpath = "(//div[@class='item-main'])[" + (index + 1) + "]/p[2]";
            return driver.findElement(By.xpath(directXpath)).getText();
        } catch (Exception e) {
            return ""; // Returns empty string if the index doesn't exist
        }
    }

    public boolean hasRequirementDetails(int index) {
        String vehicleType = getRequirementVehicleType(index);
        String location = getRequirementLocation(index);
        String price = getRequirementPrice(index);
        String dates = getRequirementDates(index);

        return !vehicleType.isEmpty() && !location.isEmpty() && !price.isEmpty() && !dates.isEmpty();
    }

    public void clickFirstSendOfferButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnSendOffer));
            btnSendOffer.click();
        }catch (Exception e) {
            System.out.println("Error clicking Send Offer button: " + e.getMessage());
        }

    }


    public boolean isOfferFormDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(offerFormContainer));
            return offerFormContainer.isDisplayed();
        } catch (Exception e) {
            System.out.println("Offer form container not visible: " + e.getMessage());
            return false;
        }

    }
}
