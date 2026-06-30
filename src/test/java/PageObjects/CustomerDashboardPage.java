//package PageObjects;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.util.List;
//
//public class CustomerDashboardPage extends BasePage{
//
//    public CustomerDashboardPage(WebDriver driver){
//        super(driver);
//    }
//
//    // Welcome Message
//    @FindBy(xpath = "//h1[contains(text(), 'Welcome')]")
//    WebElement customerWelcomeMsg;
//
//    // Active Requirements heading
//    @FindBy(xpath = "//h2[contains(text(), 'Active Requirements')]")
//    WebElement activeRequirementsHeading;
//
//    // Dashboard information text
//    @FindBy(xpath = "//p[contains(text(), 'Need a vehicle')]")
//    WebElement vehicleRequirementText;
//
//    // Profile button
//    @FindBy(xpath = "//button/span[contains(@class,'avatar')]")
//    WebElement btnCustomerProfile;
//
//    // Post Requirement button
//    @FindBy(xpath = "//button[contains(text(), 'Post')]")
//    WebElement btnPostRequirement;
//
//    // My Requirements section
//    @FindBy(xpath = "//div[contains(text(), 'My Requirements')]")
//    WebElement myRequirementsSection;
//
//    // Requirements count/items
//    @FindBy(xpath = "//div[@class='requirement-item']")
//    List<WebElement> requirementItems;
//
//    // Active requirements count
//    @FindBy(xpath = "//span[contains(text(), 'Active Requirements')]//following::span[1]")
//    WebElement activeRequirementsCount;
//
//    //========================
//    // UTILITY METHODS
//    //========================
//
//    public WebDriver getDriver(){
//        return driver;
//    }
//
//    public WebDriverWait getWait(){
//        return wait;
//    }
//
//    //========================
//    // PAGE VERIFICATION METHODS
//    //========================
//
//    public boolean isCustomerDashboardDisplayed(){
//        wait.until(ExpectedConditions.visibilityOf(customerWelcomeMsg));
//        return customerWelcomeMsg.isDisplayed();
//    }
//
//    public boolean customerDashboardMessage(){
//        wait.until(ExpectedConditions.visibilityOf(customerWelcomeMsg));
//        return customerWelcomeMsg.isDisplayed();
//    }
//
//    public boolean isWelcomeMessageDisplayed(){
//        try{
//            wait.until(ExpectedConditions.visibilityOf(customerWelcomeMsg));
//            return customerWelcomeMsg.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public String getWelcomeMessage(){
//        wait.until(ExpectedConditions.visibilityOf(customerWelcomeMsg));
//        return customerWelcomeMsg.getText();
//    }
//
//    public boolean isActiveRequirementsHeadingDisplayed(){
//        try{
//            wait.until(ExpectedConditions.visibilityOf(activeRequirementsHeading));
//            return activeRequirementsHeading.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public boolean isVehicleRequirementTextDisplayed(){
//        try{
//            wait.until(ExpectedConditions.visibilityOf(vehicleRequirementText));
//            return vehicleRequirementText.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public String getVehicleRequirementText(){
//        wait.until(ExpectedConditions.visibilityOf(vehicleRequirementText));
//        return vehicleRequirementText.getText();
//    }
//
//    public int getActiveRequirementsCount(){
//        try{
//            String count = activeRequirementsCount.getText();
//            return Integer.parseInt(count);
//        }catch(Exception e){
//            return 0;
//        }
//    }
//
//    public int getRequirementItemsCount(){
//        return requirementItems.size();
//    }
//
//    public boolean isMyRequirementsSectionEmpty(){
//        return getRequirementItemsCount() == 0;
//    }
//
//    //========================
//    // NAVIGATION METHODS
//    //========================
//
//    public void clickCustomerProfile(){
//        wait.until(ExpectedConditions.elementToBeClickable(btnCustomerProfile));
//        btnCustomerProfile.click();
//    }
//
//    public void clickPostRequirement(){
//        wait.until(ExpectedConditions.elementToBeClickable(btnPostRequirement));
//        btnPostRequirement.click();
//    }
//
//    public boolean isProfileButtonDisplayed(){
//        try{
//            return btnCustomerProfile.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    public boolean isPostRequirementButtonDisplayed(){
//        try{
//            return btnPostRequirement.isDisplayed();
//        }catch(Exception e){
//            return false;
//        }
//    }
//}



package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CustomerDashboardPage extends BasePage {

    public CustomerDashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(text(), 'Welcome')]")
    WebElement customerWelcomeMsg;

    @FindBy(xpath = "//p[contains(text(), 'Active Requirements')]")
    WebElement activeRequirementsHeading;

    @FindBy(xpath = "//h2[contains(text(), 'Need a vehicle')]")
    WebElement txtNeedVehicle;

    @FindBy(xpath = "//button/span[contains(@class,'avatar')]")
    WebElement btnCustomerProfile;

    @FindBy(xpath = "//button[contains(text(), 'Post')]")
    WebElement btnPostRequirement;

    @FindBy(xpath = "//div[@class='item clickable']")
    List<WebElement> requirementItems;

    @FindBy(xpath = "//div/p/following-sibling::strong")
    WebElement activeRequirementsCount;

    @FindBy(xpath = "//span[contains(@class,'status-badge')]")
    WebElement btnAcceptedRequirement;

    public boolean isWelcomeMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(customerWelcomeMsg));
            return customerWelcomeMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getWelcomeMessage() {
        wait.until(ExpectedConditions.visibilityOf(customerWelcomeMsg));
        return customerWelcomeMsg.getText();
    }

    public boolean isActiveRequirementsHeadingDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(activeRequirementsHeading));
            return activeRequirementsHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isVehicleRequirementTextDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(txtNeedVehicle));
            return txtNeedVehicle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getVehicleRequirementText() {
        wait.until(ExpectedConditions.visibilityOf(txtNeedVehicle));
        return txtNeedVehicle.getText();
    }

    public int getActiveRequirementsCount() {
        wait.until(ExpectedConditions.visibilityOf(activeRequirementsCount));
        return Integer.parseInt(activeRequirementsCount.getText());
    }

    public int getRequirementItemsCount() {
        return requirementItems.size();
    }

    public boolean isMyRequirementsSectionEmpty() {
        return requirementItems.isEmpty();
    }

    public void clickCustomerProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(btnCustomerProfile)).click();
    }

    public void clickPostRequirement() {
        wait.until(ExpectedConditions.elementToBeClickable(btnPostRequirement)).click();
    }

    public boolean isProfileButtonDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(btnCustomerProfile));
            return btnCustomerProfile.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPostRequirementButtonDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(btnPostRequirement));
            return btnPostRequirement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAcceptedRequirement() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAcceptedRequirement)).click();
    }

    public boolean isAcceptedRequirementDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(btnAcceptedRequirement));
            return btnAcceptedRequirement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}