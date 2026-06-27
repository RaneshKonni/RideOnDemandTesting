package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthPage extends BasePage{

    public AuthPage(WebDriver driver){
        super(driver);
    }

    //=============
    //LOCATORS
    //=============



    // For both customer and admin Register
    @FindBy(xpath = "//button[normalize-space() = 'Customer']")
    WebElement btnRoleCustomer;

    @FindBy(xpath = "//button[normalize-space() = 'Register']")
    WebElement btnRegister;

    @FindBy(xpath = "//button[normalize-space() = 'Login']")
    WebElement btnLogin;

    @FindBy(xpath = "//input[@formcontrolname = 'fullName']")
    WebElement tfFullName;

    @FindBy(xpath = "//input[@formcontrolname = 'email']")
    WebElement tfEmail;

    @FindBy(xpath = "//input[@formcontrolname = 'password']")
    WebElement tfPassword;

    @FindBy(xpath = "//input[@formcontrolname = 'mobile']")
    WebElement tfMobile;

    @FindBy(xpath = "//input[@formcontrolname = 'city']")
    WebElement tfCity;

    @FindBy(className = "primary-btn")
    WebElement btnSubmit;


    //===========
    //ACTIONS
    //===========

    public void setTfFullName(String fullName){
        tfFullName.sendKeys(fullName);
    }

    public void setTfEmail(String email){
        wait.until(ExpectedConditions.visibilityOf(tfEmail));
        tfEmail.clear();  // Clear existing data first
        tfEmail.sendKeys(email);
    }

    public void setTfPassword(String password){
        wait.until(ExpectedConditions.visibilityOf(tfPassword));
        tfPassword.clear();  // Clear existing data first
        tfPassword.sendKeys(password);
    }

    public void setTfMobile(String mobile){
        tfMobile.sendKeys(mobile);
    }

    public void setTfCity(String city){
        tfCity.sendKeys(city);
    }

    public void registerActivity(){
        wait.until(ExpectedConditions.elementToBeClickable(btnRegister));
        btnRegister.click();
    }

    public void loginActivity(){
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        btnLogin.click();
    }

    public void setRole(String role){
        try {
            if(role.equalsIgnoreCase("admin")){
                wait.until(ExpectedConditions.elementToBeClickable(btnRoleAdmin));
                btnRoleAdmin.click();
            }else if(role.equalsIgnoreCase("vendor")){
                wait.until(ExpectedConditions.elementToBeClickable(btnRoleVendor));
                btnRoleVendor.click();
            }else{
                wait.until(ExpectedConditions.elementToBeClickable(btnRoleCustomer));
                btnRoleCustomer.click();
            }
        } catch (Exception e) {
            System.out.println("Error selecting role: " + e.getMessage());
            throw new RuntimeException("Failed to select role '" + role + "': " + e.getMessage());
        }
    }

    public void clickBtnSubmit(){
        wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
        btnSubmit.click();
    }

    public void clearAll(){
        tfFullName.clear();
        tfEmail.clear();
        tfPassword.clear();
        tfMobile.clear();
        tfCity.clear();
    }

    //vendor login method
    public  void loginAsVendor(String email, String password) {

        try {
            // Wait for role selection buttons to be visible
            wait.until(ExpectedConditions.visibilityOf(btnRoleVendor));
            
            // Select Vendor role
            this.setRole("vendor");
            
            Thread.sleep(1000); // Wait for page to update after role selection
            
            // Wait for Login button to be visible
            wait.until(ExpectedConditions.visibilityOf(btnLogin));
            
            // Click on Login button
            this.loginActivity();
            
            Thread.sleep(1000); // Wait for login form to load
            
            // Wait for email field to be visible
            wait.until(ExpectedConditions.visibilityOf(tfEmail));

            // Enter credentials
            this.setTfEmail(email);
            this.setTfPassword(password);

         //   System.out.println("Entered email: " + email + " and password: " + password);

            // Submit
            this.clickBtnSubmit();

            // Wait for dashboard to load
            wait.until(ExpectedConditions.urlContains("vendor"));
            
        } catch (Exception e) {
            System.out.println("Error during vendor login: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to login as vendor: " + e.getMessage());
        }
    }
}
