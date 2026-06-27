package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CustomerProfilePage extends BasePage{

    public CustomerProfilePage(WebDriver driver){
        super(driver);
    }

    // Profile Header
    @FindBy(xpath = "//h1[contains(text(), 'Profile')]")
    WebElement profileHeading;

    @FindBy(xpath = "//img[@alt='RideOnDemand logo']")
    WebElement logoImage;

    @FindBy(xpath = "//div[@class = 'profile-meta']//h3[1]")
    WebElement txtUserName;

    @FindBy(xpath = "//div[@class = 'profile-meta']//p[1]")
    WebElement txtUserDetails;

    // Sign Out Button
    @FindBy(xpath = "//button[text() = 'Sign out']")
    WebElement btnSignOut;

    // Account Details Section
    @FindBy(xpath = "//h3[normalize-space() = 'Account details']")
    WebElement accountDetailsHeading;

    @FindBy(xpath = "//div[@class='details-grid']//div[1]//strong[1]")
    WebElement customerName;

    @FindBy(xpath = "//div[@class='details-grid']//div[2]//strong[1]")
    WebElement customerEmail;

    @FindBy(xpath = "//div[@class='details-grid']//div[3]//strong[1]")
    WebElement customerMobile;

    @FindBy(xpath = "//div[@class='details-grid']//div[4]//strong[1]")
    WebElement customerCity;

    // Requirements, Offers and OTP Updates Section
    @FindBy(xpath = "//h3[contains(text(), 'Requirements')]")
    WebElement notificationsHeading;

    @FindBy(xpath = "//app-mail-inbox[@title='Requirements, offers and OTP updates']//div[1]//strong[1]")
    WebElement itemsCount;

    @FindBy(xpath = "//div[@aria-label='Mail summary']//div[2]//strong[1]")
    WebElement unreadCount;

    @FindBy(xpath = "//h4[contains(text(), 'No customer notifications')]")
    WebElement noNotificationsMsg;

//    // Back/Navigation button to dashboard
//    @FindBy(xpath = "//button[contains(@class, 'back')]")
//    WebElement btnBackToDashboard;
//
//    @FindBy(xpath = "//a[contains(text(), 'Dashboard')]")
//    WebElement lnkDashboard;

    //========================
    // PAGE VERIFICATION METHODS
    //========================

    public boolean isProfilePageDisplayed(){
        try{
            wait.until(ExpectedConditions.visibilityOf(profileHeading));
            return profileHeading.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }


    public boolean isLogoDisplayed(){
        try{
            return logoImage.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isAccountDetailsHeadingDisplayed(){
        try{
            wait.until(ExpectedConditions.visibilityOf(accountDetailsHeading));
            return accountDetailsHeading.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isCustomerEmailDisplayed(){
        try{
            wait.until(ExpectedConditions.visibilityOf(customerEmail));
            return customerEmail.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public String getCustomerEmail(){
        wait.until(ExpectedConditions.visibilityOf(customerEmail));
        return customerEmail.getText();
    }

    public boolean isCustomerMobileDisplayed(){
        try{
            wait.until(ExpectedConditions.visibilityOf(customerMobile));
            return customerMobile.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public String getCustomerMobile(){
        wait.until(ExpectedConditions.visibilityOf(customerMobile));
        return customerMobile.getText();
    }

    public boolean isCustomerCityDisplayed(){
        try{
            wait.until(ExpectedConditions.visibilityOf(customerCity));
            return customerCity.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public String getCustomerCity(){
        wait.until(ExpectedConditions.visibilityOf(customerCity));
        return customerCity.getText();
    }

    public boolean isSignOutButtonDisplayed(){
        try{
            return btnSignOut.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isNotificationsHeadingDisplayed(){
        try{
            wait.until(ExpectedConditions.visibilityOf(notificationsHeading));
            return notificationsHeading.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isItemsCountDisplayed(){
        try{
            return itemsCount.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isUnreadCountDisplayed(){
        try{
            return unreadCount.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isNoNotificationsMessageDisplayed(){
        try{
            wait.until(ExpectedConditions.visibilityOf(noNotificationsMsg));
            return noNotificationsMsg.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public String getNoNotificationsMessage(){
        wait.until(ExpectedConditions.visibilityOf(noNotificationsMsg));
        return noNotificationsMsg.getText();
    }

    //========================
    // ACTION METHODS
    //========================

    public void clickSignout(){
        wait.until(ExpectedConditions.elementToBeClickable(btnSignOut));
        btnSignOut.click();
    }

//    public void clickBackToDashboard(){
//        try{
//            wait.until(ExpectedConditions.elementToBeClickable(btnBackToDashboard));
//            btnBackToDashboard.click();
//        }catch(Exception e){
//            try{
//                wait.until(ExpectedConditions.elementToBeClickable(lnkDashboard));
//                lnkDashboard.click();
//            }catch(Exception ex){
//                // Try clicking browser back button or navigation
//                throw new RuntimeException("Could not find back button to dashboard");
//            }
//        }
//    }

//    public WebDriver getDriver(){
//        return driver;
//    }
}
