
package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class CustomerPostRequirementPage extends BasePage{

    public CustomerPostRequirementPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(text(), 'Post Requirement')]")
    WebElement pageHeading;

    @FindBy(xpath = "//h2[contains(text(), 'Create your rental requirement')]")
    WebElement createRequirementText;

    @FindBy(xpath = "//p[@class='step']")
    WebElement stepIndicator;

    @FindBy(xpath = "//p[@class='step']")
    List<WebElement> stepNumbers;

    // Back to profile button
    @FindBy(xpath = "//button[contains(text(), 'Profile')]")
    WebElement btnBackToProfile;

    // Profile Avatarbutton
    @FindBy(xpath = "//button/span[contains(@class,'avatar')]")
    WebElement btnCustomerProfile;


    @FindBy(xpath = "//input[@formcontrolname='startDate']")
    WebElement tfStartDate;

    @FindBy(xpath = "//input[@formcontrolname='endDate']")
    WebElement tfEndDate;

    @FindBy(xpath = "//select[@formcontrolname='vehicleType']")
    WebElement ddVehicleType;

    @FindBy(xpath = "//input[@formcontrolname='location']")
    WebElement tfPickupLocation;

    @FindBy(xpath = "//input[@formcontrolname='budgetPerDay']")
    WebElement tfBudgetPerDay;

    @FindBy(xpath = "//textarea[@formcontrolname='notes']")
    WebElement tfAdditionalDetails;

    @FindBy(xpath = "//button[text()='Next']")
    WebElement btnNext;

    @FindBy(xpath = "//button[text()='Back']")
    WebElement btnBack;

    @FindBy(xpath = "//button[contains(text(), 'Post Requirement')]")
    WebElement btnPostRequirement;

    public boolean isPostRequirementPageDisplayed(){
        try{
            waitForElementToBeVisible(pageHeading);
            return pageHeading.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isCreateRequirementTextDisplayed(){
        try{
            waitForElementToBeVisible(createRequirementText);
            return createRequirementText.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public void selectVehicleType(String vehicleType){
        waitForElementToBeVisible(ddVehicleType);
        Select select = new Select(ddVehicleType);
        select.selectByVisibleText(vehicleType);

    }

    public boolean isVehicleTypeDropdownDisplayed(){
        try{
            waitForElementToBeVisible(ddVehicleType);
            return ddVehicleType.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }
    public List<WebElement> getVehicleTypeOptions(){
        Select select = new Select(ddVehicleType);
        return select.getOptions();
    }

    public boolean isStartDateFieldDisplayed(){
        try{
            waitForElementToBeVisible(tfStartDate);
            return tfStartDate.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isEndDateFieldDisplayed(){
        try{
            waitForElementToBeVisible(tfEndDate);
            return tfEndDate.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public void clickStartDatePicker() {
        waitForElementToBeClickable(tfStartDate).click();
    }

    public void clickEndDatePicker() {
        waitForElementToBeClickable(tfEndDate).click();

    }

    public void setStartDate(String date){
        waitForElementToBeVisible(tfStartDate);
        tfStartDate.clear();
        tfStartDate.sendKeys(date);
    }

    public void setEndDate(String date){
        waitForElementToBeVisible(tfEndDate);
        tfEndDate.clear();
        tfEndDate.sendKeys(date);
    }

    public void setPickupLocation(String location){
        waitForElementToBeVisible(tfPickupLocation);
        tfPickupLocation.clear();
        tfPickupLocation.sendKeys(location);
    }

    public void setBudgetPerDay(String budget){
        waitForElementToBeVisible(tfBudgetPerDay);
        tfBudgetPerDay.clear();
        tfBudgetPerDay.sendKeys(budget);
    }
    public boolean isPickupLocationFieldDisplayed(){
        try{
            waitForElementToBeVisible(tfPickupLocation);
            return tfPickupLocation.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isBudgetFieldDisplayed(){
        try{
            waitForElementToBeVisible(tfBudgetPerDay);
            return tfBudgetPerDay.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public void clickNext(){
        waitForElementToBeClickable(btnNext);
        btnNext.click();
    }

    public void clickBack(){
        waitForElementToBeClickable(btnBack);
        btnBack.click();
    }

    public void clickPostRequirement(){
        waitForElementToBeClickable(btnPostRequirement);
        btnPostRequirement.click();
    }

    public boolean isNextButtonDisplayed(){
        try{
            waitForElementToBeVisible(btnNext);
            return btnNext.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }
    public boolean isBackButtonDisplayed(){
        try{
            waitForElementToBeVisible(btnBack);
            return btnBack.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isPostRequirementButtonDisplayed(){
        try{
            waitForElementToBeVisible(btnPostRequirement);
            return btnPostRequirement.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public void clickBackToProfile(){
        waitForElementToBeClickable(btnCustomerProfile).click();
    }

    public boolean canNavigateToProfile() {
        try {
            waitForElementToBeVisible(btnCustomerProfile);
            return btnCustomerProfile.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void clearStartDate(){
        tfStartDate.clear();
    }

    public void clearEndDate(){
        tfEndDate.clear();
    }

    public void clearPickupLocation(){
        tfPickupLocation.clear();
    }

    public void clearBudget(){
        tfBudgetPerDay.clear();
    }

    public String getStartDateValue(){
        waitForElementToBeVisible(tfStartDate);
        return tfStartDate.getAttribute("value");
    }
    public String getEndDateValue(){
        waitForElementToBeVisible(tfEndDate);
        return tfEndDate.getAttribute("value");
    }
    public String getPickupLocationValue(){
        waitForElementToBeVisible(tfPickupLocation);
        return tfPickupLocation.getAttribute("value");
    }
    public String getBudgetValue(){
        waitForElementToBeVisible(tfBudgetPerDay);
        return tfBudgetPerDay.getAttribute("value");
    }
}

