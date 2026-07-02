package PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CustomerOffersReceivedPage extends BasePage {

    public CustomerOffersReceivedPage(WebDriver driver) {
        super(driver);
    }

    //LOCATORS

    @FindBy(xpath="//h1[text()='Offers Received']")
    WebElement txtOffersReceived;

    @FindBy(xpath="//img[contains(@class,'app-logo')]")
    WebElement imgLogo;

    @FindBy(xpath="//strong[contains(@class,'requirement-id')]")
    WebElement txtRequirementId;

    @FindBy(xpath="//div[contains(@class,'summary-pill')]")
    WebElement txtTotalOffers;

    @FindBy(xpath="//article[contains(@class,'card')]")
    List<WebElement> offersArticles;

    @FindBy(xpath="//div[contains(@class,'price')]")
    WebElement txtPrice;

    @FindBy(xpath="//span[contains(@class,'status-chip')]")
    WebElement statusChip;

    @FindBy(xpath="//a[contains(@href,'wa.me')]")
    WebElement lnkWhatsApp;

    @FindBy(xpath="//a[contains(@href,'tel:')]")
    WebElement lnkCall;

    @FindBy(xpath="//button[contains(@class,'primary-btn')]")
    WebElement btnAcceptReject;

    //METHODS

    public boolean isOffersReceivedHeadingDisplayed(){
        return txtOffersReceived.isDisplayed();
    }

    public boolean isLogoDisplayed(){
        return imgLogo.isDisplayed();
    }

    public String getRequirementId(){
        return txtRequirementId.getText();
    }

    public String getTotalOffers(){
        return txtTotalOffers.getText();
    }

    public String getPrice(){
        return txtPrice.getText();
    }

    public boolean isStatusDisplayed(){
        return statusChip.isDisplayed();
    }

    public boolean isWhatsAppDisplayed(){
        return lnkWhatsApp.isDisplayed();
    }

    public boolean isCallDisplayed(){
        return lnkCall.isDisplayed();
    }

    public boolean isAcceptRejectButtonDisplayed(){
        return btnAcceptReject.isDisplayed();
    }

    public boolean isAcceptRejectButtonEnabled(){
        return btnAcceptReject.isEnabled();
    }

    public int getOffersCount() {
        return offersArticles.size();
    }


}
