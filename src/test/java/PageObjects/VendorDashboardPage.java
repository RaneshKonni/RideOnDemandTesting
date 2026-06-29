package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VendorDashboardPage extends BasePage {
    public VendorDashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[normalize-space() = 'Vendor Dashboard']")
    WebElement vendorWelcomeMessage;

    @FindBy(xpath = "//button/span[contains(@class,'avatar')]")
    WebElement btnVendorProfile;

    @FindBy(xpath = "//article[@class='card panel']//span[@class='count-pill']")
    WebElement requirementCount;

    @FindBy(xpath = "//section[contains(@class,'offers-panel')]//span[@class='count-pill']")
    WebElement offerCount;

    @FindBy(xpath = "//div[@class='stat-tile stat-tile-highlight']//strong[contains(text(),'Bike')]")
    WebElement selectedRequirementName;

    @FindBy(xpath = "//div[@aria-label='Vendor dashboard overview']//div[1]")
    WebElement requirementCard;

    @FindBy(xpath = "//div[@aria-label='Vendor dashboard overview']//div[2]")
    WebElement offerCard;

    @FindBy(xpath = "//div[@aria-label='Vendor dashboard overview']//div[3]")
    WebElement selectedRequirementCard;

    @FindBy(css = ".card.panel.panel-sticky")
    WebElement offerFormContainer;

    @FindBy(xpath = "//button[normalize-space()='Submit offer']")
    WebElement btnSubmitOffer;

    @FindBy(xpath = "//h3[normalize-space()='Live requirements']")
    WebElement liveRequirementsHeading;

    @FindBy(xpath = "//article[@class='card panel']")
    WebElement demandFeedContainer;

    @FindBy(xpath = "//h3[normalize-space()='My offers']")
    WebElement myOffersHeading;

    private By withdrawButton = By.xpath("//section[contains(@class,'offers-panel')]//button[contains(., 'Withdraw')]");
    private By firstOfferStatus = By.xpath("//section[contains(@class,'offers-panel')]//div[contains(@class, 'offer-item')][1]//span[contains(@class, 'status-pill')]");


    public boolean vendorDashboardMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(vendorWelcomeMessage));
            return vendorWelcomeMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMetricsVisible() {
        wait.until(ExpectedConditions.visibilityOf(requirementCard));
        wait.until(ExpectedConditions.visibilityOf(offerCard));
        wait.until(ExpectedConditions.visibilityOf(selectedRequirementCard));
        return requirementCard.isDisplayed() && offerCard.isDisplayed() && selectedRequirementCard.isDisplayed();
    }

    public boolean isProfileButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(btnVendorProfile));
        return btnVendorProfile.isDisplayed();
    }

    public void clickVendorProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(btnVendorProfile)).click();
    }

    public boolean isLiveRequirementsHeadingDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(liveRequirementsHeading));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void scrollToLiveRequirements() {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", liveRequirementsHeading);
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("Error scrolling: " + e.getMessage());
        }
    }

    public boolean isDemandFeedListDisplayed() {
        try {
            scrollToLiveRequirements();
            wait.until(ExpectedConditions.visibilityOf(demandFeedContainer));
            return demandFeedContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getRequirementItemsCount() {
        wait.until(ExpectedConditions.visibilityOf(requirementCount));
        return Integer.parseInt(requirementCount.getText().trim());
    }

    public int getOfferItemsCount() {
        wait.until(ExpectedConditions.visibilityOf(offerCount));
        return Integer.parseInt(offerCount.getText().trim());
    }

    public String getRequirementVehicleType(int index) {
        String xpath = "(//div[@class='item-main']/strong)[" + (index + 1) + "]";
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
    }

    public String getRequirementLocation(int index) {
        String xpath = "(//div[@class='item-main']/p)[" + (index + 1) + "]";
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
    }

    public String getRequirementPrice(int index) {
        String xpath = "(//div[@class='item-meta'])[" + (index + 1) + "]/span[1]";
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
    }

    public String getRequirementDates(int index) {
        String xpath = "(//div[@class='item-meta'])[" + (index + 1) + "]/span[2]";
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
    }

    public String getRequirementNotes(int index) {
        String xpath = "(//div[@class='item-main'])[" + (index + 1) + "]/p[contains(@class, 'item-notes')]";
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
    }

    public boolean hasRequirementDetails(int index) {
        return !getRequirementVehicleType(index).isEmpty() &&
                !getRequirementLocation(index).isEmpty() &&
                !getRequirementPrice(index).isEmpty() &&
                !getRequirementDates(index).isEmpty();
    }

    public void clickFirstSendOfferButton() {
        scrollToLiveRequirements();
        WebElement sendOfferBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[normalize-space()='Send offer'])[1]")));
        wait.until(ExpectedConditions.elementToBeClickable(sendOfferBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sendOfferBtn);
    }

    public boolean isOfferFormDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(offerFormContainer));
            return offerFormContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getFirstRequirementId() {
        return "REQ_INDEX_0";
    }

    public String getRecentlySubmittedOfferId() {
        WebElement offersSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class, 'offers-panel')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", offersSection);

        WebElement firstOfferIdStr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class, 'offer-item')]//p[@class='item-subtitle'])[1]")));
        return firstOfferIdStr.getText();
    }

    public void enterOfferDetails(String pricePerDay, String vehicleModel, String registrationNumber, String notes) {
        wait.until(ExpectedConditions.visibilityOf(offerFormContainer));

        WebElement tfPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[formcontrolname='pricePerDay']")));
        tfPrice.clear();
        tfPrice.sendKeys(pricePerDay);

        WebElement tfVehicle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[formcontrolname='vehicleModel']")));
        tfVehicle.clear();
        tfVehicle.sendKeys(vehicleModel);

        WebElement tfReg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[formcontrolname='registrationNumber']")));
        tfReg.clear();
        tfReg.sendKeys(registrationNumber);

        WebElement tfNotes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[formcontrolname='notes']")));
        tfNotes.clear();
        tfNotes.sendKeys(notes);
    }

    public void clickClearOfferButton() {
        WebElement clearBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Clear']")));
        wait.until(ExpectedConditions.elementToBeClickable(clearBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clearBtn);
    }

    public void clickSubmitOfferButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSubmitOffer)).click();
    }

    public String getPriceFieldValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[formcontrolname='pricePerDay']"))).getAttribute("value");
    }

    public String getVehicleFieldValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[formcontrolname='vehicleModel']"))).getAttribute("value");
    }

    public String getRegistrationFieldValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[formcontrolname='registrationNumber']"))).getAttribute("value");
    }

    public String getNotesFieldValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[formcontrolname='notes']"))).getAttribute("value");
    }

    public void scrollToMyOffers() {
        try {
            wait.until(ExpectedConditions.visibilityOf(myOffersHeading));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", myOffersHeading);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("⚠️ Error scrolling to My Offers: " + e.getMessage());
        }
    }

    public boolean isWithdrawButtonAvailable() {
        // Looks for a button containing the text "Withdraw" (case-insensitive) inside the offer items
        return !driver.findElements(By.xpath("//div[contains(@class, 'offer-item')]//button[contains(translate(., 'WITHDRAW', 'withdraw'), 'withdraw')]")).isEmpty();
    }

    public void clickWithdrawAndAcceptAlert() {
        // 1. Wait for the button to be clickable, then click it
        wait.until(ExpectedConditions.elementToBeClickable(withdrawButton)).click();

        // 2. Wait for the Javascript alert to appear and accept it
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public String waitForAndGetOfferStatus(String expectedStatus) {
        // 3. Wait explicitly for the status text to match the expected value
        wait.until(ExpectedConditions.textToBe(firstOfferStatus, expectedStatus));

        // 4. Return the new text directly from the DOM
        return driver.findElement(firstOfferStatus).getText();
    }

    public void navigateToProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(btnVendorProfile)).click();
    }
}