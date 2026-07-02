package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Navbar extends BasePage {
    public Navbar(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//button[@title='Open profile']")
    WebElement profileButton;

    public void clickProfileButton(){
        waitForElementToBeClickable(profileButton).click();
    }


}
