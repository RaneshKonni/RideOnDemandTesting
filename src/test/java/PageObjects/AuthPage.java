package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//button[normalize-space() = 'Vendor']")
    WebElement btnRoleVendor;

    @FindBy(xpath = "//button[normalize-space() = 'Admin']")
    WebElement btnRoleAdmin;

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


    // for vendor this is the extra field
    @FindBy(xpath = "//input[@formcontrolname = 'shopName']")
    WebElement tfShopName;



    //===========
    //ACTIONS
    //===========

    public void setTfFullName(String fullName){
        tfFullName.sendKeys(fullName);
    }

    public void setTfEmail(String email){
        tfEmail.sendKeys(email);
    }

    public void setTfPassword(String password){
        tfPassword.sendKeys(password);
    }

    public void setTfMobile(String mobile){
        tfMobile.sendKeys(mobile);
    }

    public void setTfCity(String city){
        tfCity.sendKeys(city);
    }

    public void setTfShopName(String shopName){
        tfShopName.sendKeys(shopName);
    }

    public void registerActivity(){
        btnRegister.click();
    }

    public void loginActivity(){
        btnLogin.click();
    }

    public void setRole(String role){
        if(role.equalsIgnoreCase("admin")){
            btnRoleAdmin.click();
        }else if(role.equalsIgnoreCase("vendor")){
            btnRoleVendor.click();
        }else{
            btnRoleCustomer.click();
        }
    }

    public void clickBtnSubmit(){
        btnSubmit.click();
    }

    public void clearAll(){
        tfFullName.clear();
        tfEmail.clear();
        tfPassword.clear();
        tfMobile.clear();
        tfCity.clear();
    }
}
