package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends BasePage{
    public AuthPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@aria-label='Select role']/button[text()=' Customer ']")
    WebElement customerButton;
    @FindBy(xpath = "//div[@aria-label='Select role']/button[text()=' Vendor ']")
    WebElement vendorButton;
    @FindBy(xpath = "//div[@aria-label='Select role']/button[text()=' Admin ']")
    WebElement adminButton;
    @FindBy(xpath = "//div[@aria-label='Select mode']/button[text()='Register']")
    WebElement registerButton;
    @FindBy(xpath = "//div[@aria-label='Select mode']/button[text()='Login']")
    WebElement loginButton;
    @FindBy(xpath = "//form//input[@formcontrolname='fullName']")
    WebElement fullNameInput;
    @FindBy(xpath = "//form//input[@formcontrolname='email']")
    WebElement emailInput;
    @FindBy(xpath = "//form//input[@formcontrolname='password']")
    WebElement passwordInput;
    @FindBy(xpath = "//form//input[@formcontrolname='mobile']")
    WebElement mobileInput;
    @FindBy(xpath = "//form//input[@formcontrolname='city']")
    WebElement cityInput;
    @FindBy(xpath = "//form//input[@formcontrolname='shopName']")
    WebElement shopNameInput;
    @FindBy(xpath = "//form/button[text()=' Register & Continue ']")
    WebElement registerAndContinueButton;
    @FindBy(xpath = "//form/button[text()=' Login ']")
    WebElement actualLoginButton;
    @FindBy(xpath = "//p[@role='alert' and @class='toastmsg']")
    WebElement errorMessage;

    public void clickCustomerButton(){
        waitForElementToBeClickable(customerButton).click();
    }

    public void clickVendorButton(){
        waitForElementToBeClickable(vendorButton).click();
    }

    public void clickAdminButton(){
        waitForElementToBeClickable(adminButton).click();
    }

    public void clickRegisterButton(){
        waitForElementToBeClickable(registerButton).click();
    }

    public void clickLoginButton(){
        waitForElementToBeClickable(loginButton).click();
    }

    public void clickActualLoginButton(){
        waitForElementToBeClickable(actualLoginButton).click();
    }

    public void clickRegisterAndContinueButton(){
        waitForElementToBeClickable(registerAndContinueButton).click();
    }

    public void setFullName(String fullName){
        fullNameInput.clear();
        fullNameInput.sendKeys(fullName);
    }

    public void setEmail(String email){
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void setPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void setMobile(String mobile){
        mobileInput.clear();
        mobileInput.sendKeys(mobile);
    }

    public void setCity(String city){
        cityInput.clear();
        cityInput.sendKeys(city);
    }

    public void setShopName(String shopName){
        shopNameInput.clear();
        shopNameInput.sendKeys(shopName);
    }

    public boolean errorIsDisplayed(){
        waitForElementToBeVisible(errorMessage);
        return errorMessage.isDisplayed();
    }

    public String getErrorMessage(){
        waitForElementToBeVisible(errorMessage);
        return errorMessage.getText();
    }

}
