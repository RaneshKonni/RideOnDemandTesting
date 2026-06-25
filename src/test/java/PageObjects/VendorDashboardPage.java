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

    public boolean vendorDashboardMessage(){

        wait.until(ExpectedConditions.visibilityOf(vendorWelcomeMessage));
        return vendorWelcomeMessage.isDisplayed();
    }

    public void clickVendorProfile(){
        btnVendorProfile.click();
    }
}
