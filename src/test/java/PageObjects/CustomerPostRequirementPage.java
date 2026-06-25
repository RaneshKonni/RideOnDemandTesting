package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class CustomerPostRequirementPage extends BasePage{

    public CustomerPostRequirementPage(WebDriver driver){
        super(driver);
    }

    //========================
    // GENERAL LOCATORS
    //========================

    @FindBy(xpath = "//h1[contains(text(), 'Post Requirement')]")
    WebElement pageHeading;

    @FindBy(xpath = "//span[contains(text(), 'Post Requirement')]")
    WebElement navText;

    @FindBy(xpath = "//h2[contains(text(), 'Create your rental requirement')]")
    WebElement createRequirementText;

    @FindBy(xpath = "//div[@class='step-indicator']")
    WebElement stepIndicator;

    @FindBy(xpath = "//span[@class='step-number']")
    List<WebElement> stepNumbers;

    // Back to profile button
    @FindBy(xpath = "//button[contains(text(), 'Profile')]")
    WebElement btnBackToProfile;

    @FindBy(xpath = "//a[contains(text(), 'Profile')]")
    WebElement lnkProfile;

    //========================
    // STEP 1: BASIC DETAILS LOCATORS
    //========================

    @FindBy(xpath = "//input[@name='startDate']")
    WebElement tfStartDate;

    @FindBy(xpath = "//input[@type='date'][@name='startDate']")
    WebElement datePickerStartDate;

    @FindBy(xpath = "//input[@name='endDate']")
    WebElement tfEndDate;

    @FindBy(xpath = "//input[@type='date'][@name='endDate']")
    WebElement datePickerEndDate;

    @FindBy(xpath = "//select[@name='vehicleType']")
    WebElement ddVehicleType;

    @FindBy(xpath = "//option[contains(text(), 'Bike')]")
    WebElement optionBike;

    @FindBy(xpath = "//option[contains(text(), 'Scooty')]")
    WebElement optionScooty;

    @FindBy(xpath = "//option[contains(text(), 'Car')]")
    WebElement optionCar;

    @FindBy(xpath = "//option[contains(text(), 'SUV')]")
    WebElement optionSUV;

    //========================
    // STEP 2: LOCATION & BUDGET LOCATORS
    //========================

    @FindBy(xpath = "//input[@name='pickupLocation']")
    WebElement tfPickupLocation;

    @FindBy(xpath = "//input[@name='budget']")
    WebElement tfBudgetPerDay;

    //========================
    // STEP 3: ADDITIONAL DETAILS LOCATORS
    //========================

    @FindBy(xpath = "//textarea[@name='additionalDetails']")
    WebElement tfAdditionalDetails;

    @FindBy(xpath = "//input[@name='preferredShops']")
    WebElement tfPreferredShops;

    //========================
    // NAVIGATION BUTTONS
    //========================

    @FindBy(xpath = "//button[text()='Next']")
    WebElement btnNext;

    @FindBy(xpath = "//button[text()='Back']")
    WebElement btnBack;

    @FindBy(xpath = "//button[contains(text(), 'Post Requirement')]")
    WebElement btnPostRequirement;

    //========================
    // PAGE VERIFICATION METHODS
    //========================

    public boolean isPostRequirementPageDisplayed(){
        try{
            wait.until(ExpectedConditions.visibilityOf(pageHeading));
            return pageHeading.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isNavTextDisplayed(){
        try{
            wait.until(ExpectedConditions.visibilityOf(navText));
            return navText.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public String getNavText(){
        wait.until(ExpectedConditions.visibilityOf(navText));
        return navText.getText();
    }

    public boolean isCreateRequirementTextDisplayed(){
        try{
            wait.until(ExpectedConditions.visibilityOf(createRequirementText));
            return createRequirementText.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public String getCreateRequirementText(){
        wait.until(ExpectedConditions.visibilityOf(createRequirementText));
        return createRequirementText.getText();
    }

    public boolean isStepIndicatorDisplayed(){
        try{
            return stepIndicator.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public int getTotalSteps(){
        return stepNumbers.size();
    }

    //========================
    // STEP 1 METHODS
    //========================

    public void setStartDate(String date){
        wait.until(ExpectedConditions.visibilityOf(tfStartDate));
        tfStartDate.sendKeys(date);
    }

    public void setEndDate(String date){
        wait.until(ExpectedConditions.visibilityOf(tfEndDate));
        tfEndDate.sendKeys(date);
    }

    public void selectVehicleType(String vehicleType){
        wait.until(ExpectedConditions.visibilityOf(ddVehicleType));
        Select select = new Select(ddVehicleType);
        select.selectByValue(vehicleType.toLowerCase());
    }

    public boolean isVehicleTypeDropdownDisplayed(){
        try{
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
            return tfStartDate.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isEndDateFieldDisplayed(){
        try{
            return tfEndDate.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public void clickStartDatePicker(){
        wait.until(ExpectedConditions.elementToBeClickable(tfStartDate));
        tfStartDate.click();
    }

    public void clickEndDatePicker(){
        wait.until(ExpectedConditions.elementToBeClickable(tfEndDate));
        tfEndDate.click();
    }

    //========================
    // STEP 2 METHODS
    //========================

    public void setPickupLocation(String location){
        wait.until(ExpectedConditions.visibilityOf(tfPickupLocation));
        tfPickupLocation.sendKeys(location);
    }

    public void setBudgetPerDay(String budget){
        wait.until(ExpectedConditions.visibilityOf(tfBudgetPerDay));
        tfBudgetPerDay.sendKeys(budget);
    }

    public boolean isPickupLocationFieldDisplayed(){
        try{
            return tfPickupLocation.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isBudgetFieldDisplayed(){
        try{
            return tfBudgetPerDay.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    //========================
    // STEP 3 METHODS
    //========================

    public void setAdditionalDetails(String details){
        try{
            wait.until(ExpectedConditions.visibilityOf(tfAdditionalDetails));
            tfAdditionalDetails.sendKeys(details);
        }catch(Exception e){
            // Additional details might be optional
        }
    }

    public void setPreferredShops(String shops){
        try{
            wait.until(ExpectedConditions.visibilityOf(tfPreferredShops));
            tfPreferredShops.sendKeys(shops);
        }catch(Exception e){
            // Preferred shops might be optional
        }
    }

    //========================
    // NAVIGATION METHODS
    //========================

    public void clickNext(){
        wait.until(ExpectedConditions.elementToBeClickable(btnNext));
        btnNext.click();
    }

    public void clickBack(){
        wait.until(ExpectedConditions.elementToBeClickable(btnBack));
        btnBack.click();
    }

    public void clickPostRequirement(){
        wait.until(ExpectedConditions.elementToBeClickable(btnPostRequirement));
        btnPostRequirement.click();
    }

    public boolean isNextButtonDisplayed(){
        try{
            return btnNext.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isBackButtonDisplayed(){
        try{
            return btnBack.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isPostRequirementButtonDisplayed(){
        try{
            return btnPostRequirement.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isNextButtonVisible(){
        try{
            wait.until(ExpectedConditions.visibilityOf(btnNext));
            return btnNext.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isBackButtonVisible(){
        try{
            wait.until(ExpectedConditions.visibilityOf(btnBack));
            return btnBack.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    //========================
    // PROFILE NAVIGATION
    //========================

    public void clickBackToProfile(){
        try{
            wait.until(ExpectedConditions.elementToBeClickable(btnBackToProfile));
            btnBackToProfile.click();
        }catch(Exception e){
            try{
                wait.until(ExpectedConditions.elementToBeClickable(lnkProfile));
                lnkProfile.click();
            }catch(Exception ex){
                throw new RuntimeException("Could not find button to navigate back to profile");
            }
        }
    }

    public boolean canNavigateToProfile(){
        try{
            return btnBackToProfile.isDisplayed() || lnkProfile.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public WebDriver getDriver(){
        return driver;
    }

    //========================
    // VALIDATION METHODS
    //========================

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
        return tfStartDate.getAttribute("value");
    }

    public String getEndDateValue(){
        return tfEndDate.getAttribute("value");
    }

    public String getPickupLocationValue(){
        return tfPickupLocation.getAttribute("value");
    }

    public String getBudgetValue(){
        return tfBudgetPerDay.getAttribute("value");
    }
}

