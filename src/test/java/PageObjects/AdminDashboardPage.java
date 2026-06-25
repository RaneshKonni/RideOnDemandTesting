package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminDashboardPage extends BasePage{

    public AdminDashboardPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//h1[normalize-space() = 'Admin Dashboard']")
    WebElement adminWelcomeMsg;

    @FindBy(xpath = "//button/span[contains(@class,'avatar')]")
    WebElement btnAdminProfile;
    public boolean adminDashboardMessage(){
        wait.until(ExpectedConditions.visibilityOf(adminWelcomeMsg));
        return adminWelcomeMsg.isDisplayed();
    }

    public void clickAdminProfile(){
        btnAdminProfile.click();
    }


}
