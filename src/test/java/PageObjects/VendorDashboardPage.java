package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VendorDashboardPage extends BasePage{
    public VendorDashboardPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//h1[normalize-space() = 'Vendor Dashboard']")
    WebElement vendorWelcomeMessage;

    @FindBy(xpath = "//button/span[contains(@class,'avatar')]")
    WebElement btnVendorProfile;

    @FindBy(xpath="//strong[normalize-space()='11']")
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









    public boolean vendorDashboardMessage(){

        wait.until(ExpectedConditions.visibilityOf(vendorWelcomeMessage));
        return vendorWelcomeMessage.isDisplayed();
    }

    public boolean isMetricsVisible(){

        wait.until(ExpectedConditions.visibilityOf(requirementCard));
        wait.until(ExpectedConditions.visibilityOf(offerCard));
        wait.until(ExpectedConditions.visibilityOf(selectedRequirementCard));

        return requirementCard.isDisplayed() && offerCard.isDisplayed() && selectedRequirementCard.isDisplayed();
    }

    //is profile button visible
    public boolean isProfileButtonDisplayed(){
        wait.until(ExpectedConditions.visibilityOf(btnVendorProfile));
        return btnVendorProfile.isDisplayed();
    }

    public void clickVendorProfile(){
        btnVendorProfile.click();
    }




}
