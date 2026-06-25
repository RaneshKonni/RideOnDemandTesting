package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminProfilePage extends BasePage{

    public AdminProfilePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//button[text() = 'Sign out']")
    WebElement btnSignOut;

    public void clickSignout(){
        btnSignOut.click();
    }
}
