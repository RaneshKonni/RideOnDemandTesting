//package PageObjects;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//
//public class CustomerProfilePage extends BasePage{
//
//    public CustomerProfilePage(WebDriver driver){
//        super(driver);
//    }
//
//    // Profile Header
//    @FindBy(xpath = "//h1[contains(text(), 'Profile')]")
//    WebElement profileHeading;
//
//    @FindBy(xpath = "//span[contains(text(), 'Profile & Notifications')]")
//    WebElement profileAndNotificationsText;
//
//    @FindBy(xpath = "//img[@alt='logo']")
//    WebElement logoImage;
//
//    // Account Details Section
//    @FindBy(xpath = "//h2[contains(text(), 'Account Details')]")
//    WebElement accountDetailsHeading;
//
//    @FindBy(xpath = "//div[contains(text(), 'Email')]//following::span[1]")
//    WebElement customerEmail;
//
//    @FindBy(xpath = "//div[contains(text(), 'Mobile')]//following::span[1]")
//    WebElement customerMobile;
//
//    @FindBy(xpath = "//div[contains(text(), 'City')]//following::span[1]")
//    WebElement customerCity;
//
//    // Sign Out Button
//    @FindBy(xpath = "//button[text() = 'Sign out']")
//    WebElement btnSignOut;
//
//    // Requirements, Offers and OTP Updates Section
//    @FindBy(xpath = "//h2[contains(text(), 'Requirements')]")
//    WebElement notificationsHeading;
//
//    @FindBy(xpath = "//span[contains(text(), 'items')]")
//    WebElement itemsCount;
//
//    @FindBy(xpath = "//span[contains(text(), 'unread')]")
//    WebElement unreadCount;
//
//    @FindBy(xpath = "//p[contains(text(), 'No customer notifications')]")
//    WebElement noNotificationsMsg;
//
//    // Back/Navigation button to dashboard
//    @FindBy(xpath = "//button[contains(@class, 'back')]")
//    WebElement btnBackToDashboard;
//
//    @FindBy(xpath = "//a[contains(text(), 'Dashboard')]")
//    WebElement lnkDashboard;
//
//    //========================
//    // PAGE VERIFICATION METHODS
//    //========================
//
//    public boolean isProfilePageDisplayed(){
//        try{
//            wait.until(ExpectedConditions.visibilityOf(profileHeading));
//            return profileHeading.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public boolean isProfileAndNotificationsTextDisplayed(){
//        try{
//            wait.until(ExpectedConditions.visibilityOf(profileAndNotificationsText));
//            return profileAndNotificationsText.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public boolean isLogoDisplayed(){
//        try{
//            return logoImage.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public boolean isAccountDetailsHeadingDisplayed(){
//        try{
//            wait.until(ExpectedConditions.visibilityOf(accountDetailsHeading));
//            return accountDetailsHeading.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public boolean isCustomerEmailDisplayed(){
//        try{
//            wait.until(ExpectedConditions.visibilityOf(customerEmail));
//            return customerEmail.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public String getCustomerEmail(){
//        wait.until(ExpectedConditions.visibilityOf(customerEmail));
//        return customerEmail.getText();
//    }
//
//    public boolean isCustomerMobileDisplayed(){
//        try{
//            wait.until(ExpectedConditions.visibilityOf(customerMobile));
//            return customerMobile.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public String getCustomerMobile(){
//        wait.until(ExpectedConditions.visibilityOf(customerMobile));
//        return customerMobile.getText();
//    }
//
//    public boolean isCustomerCityDisplayed(){
//        try{
//            wait.until(ExpectedConditions.visibilityOf(customerCity));
//            return customerCity.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public String getCustomerCity(){
//        wait.until(ExpectedConditions.visibilityOf(customerCity));
//        return customerCity.getText();
//    }
//
//    public boolean isSignOutButtonDisplayed(){
//        try{
//            return btnSignOut.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public boolean isNotificationsHeadingDisplayed(){
//        try{
//            wait.until(ExpectedConditions.visibilityOf(notificationsHeading));
//            return notificationsHeading.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public boolean isItemsCountDisplayed(){
//        try{
//            return itemsCount.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public boolean isUnreadCountDisplayed(){
//        try{
//            return unreadCount.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public boolean isNoNotificationsMessageDisplayed(){
//        try{
//            wait.until(ExpectedConditions.visibilityOf(noNotificationsMsg));
//            return noNotificationsMsg.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public String getNoNotificationsMessage(){
//        wait.until(ExpectedConditions.visibilityOf(noNotificationsMsg));
//        return noNotificationsMsg.getText();
//    }
//
//    //========================
//    // ACTION METHODS
//    //========================
//
//    public void clickSignout(){
//        wait.until(ExpectedConditions.elementToBeClickable(btnSignOut));
//        btnSignOut.click();
//    }
//
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
//
//    public WebDriver getDriver(){
//        return driver;
//    }
//}


package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CustomerProfilePage extends BasePage {

    public CustomerProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(text(), 'Profile')]")
    WebElement profileHeading;

    @FindBy(xpath = "//img[@alt='RideOnDemand logo']")
    WebElement logoImage;

    @FindBy(xpath = "//button[text() = 'Sign out']")
    WebElement btnSignOut;

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

    @FindBy(xpath = "//h3[contains(text(), 'Requirements')]")
    WebElement notificationsHeading;

    @FindBy(xpath = "//app-mail-inbox[@title='Requirements, offers and OTP updates']//div[1]//strong[1]")
    WebElement itemsCount;

    @FindBy(xpath = "//div[@aria-label='Mail summary']//div[2]//strong[1]")
    WebElement unreadCount;

    @FindBy(xpath = "//h4[contains(text(), 'No customer notifications')]")
    WebElement noNotificationsMsg;

    public boolean isProfilePageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(profileHeading));
            return profileHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(logoImage));
            return logoImage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAccountDetailsHeadingDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(accountDetailsHeading));
            return accountDetailsHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCustomerEmailDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(customerEmail));
            return customerEmail.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCustomerName() {
        wait.until(ExpectedConditions.visibilityOf(customerName));
        return customerName.getText();
    }

    public String getCustomerEmail() {
        wait.until(ExpectedConditions.visibilityOf(customerEmail));
        return customerEmail.getText();
    }

    public boolean isCustomerMobileDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(customerMobile));
            return customerMobile.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCustomerMobile() {
        wait.until(ExpectedConditions.visibilityOf(customerMobile));
        return customerMobile.getText();
    }

    public boolean isCustomerCityDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(customerCity));
            return customerCity.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCustomerCity() {
        wait.until(ExpectedConditions.visibilityOf(customerCity));
        return customerCity.getText();
    }

    public boolean isSignOutButtonDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(btnSignOut));
            return btnSignOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSignout() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSignOut)).click();
    }

    public boolean isNotificationsHeadingDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(notificationsHeading));
            return notificationsHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isItemsCountDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(itemsCount));
            return itemsCount.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getItemsCount() {
        wait.until(ExpectedConditions.visibilityOf(itemsCount));
        return itemsCount.getText();
    }

    public boolean isUnreadCountDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(unreadCount));
            return unreadCount.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getUnreadCount() {
        wait.until(ExpectedConditions.visibilityOf(unreadCount));
        return unreadCount.getText();
    }

    public boolean isNoNotificationsMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(noNotificationsMsg));
            return noNotificationsMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getNoNotificationsMessage() {
        wait.until(ExpectedConditions.visibilityOf(noNotificationsMsg));
        return noNotificationsMsg.getText();
    }
}