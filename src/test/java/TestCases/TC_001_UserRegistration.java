package TestCases;

import DataProviders.RegDataProvider;
import PageObjects.*;
import TestBase.BaseClass;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_001_UserRegistration extends BaseClass {

    AdminProfilePage adminProfilePage;
    VendorProfilePage vendorProfilePage;
    CustomerProfilePage customerProfilePage;
    @Test(dataProvider = "RegistrationData", dataProviderClass = RegDataProvider.class)
    public void verifyCustomerRegistration(String role, String fullname, String email, String password, String mobile, String city, String expected){

        try{
            AuthPage ap = new AuthPage(driver);
            ap.setRole(role);
            ap.setTfFullName(fullname);
            ap.setTfEmail(email);
            ap.setTfPassword(password);
            ap.setTfMobile(mobile);
            ap.setTfCity(city);

            ap.clickBtnSubmit();
            boolean targetPage = false;

                CustomerDashboardPage customer = new CustomerDashboardPage(driver);
                targetPage = customer.customerDashboardMessage();
                customer.clickCustomerProfile();
                customerProfilePage = new CustomerProfilePage(driver);

            if(expected.equalsIgnoreCase("valid")){
                if(targetPage){

                        customerProfilePage.clickSignout();

                    Assert.assertTrue(true);

                }else{
                    ap.clearAll();
                    Assert.assertTrue(false);
                }
            }else{
                if(targetPage){
                    ap.clearAll();
                    Assert.assertTrue(false);
                }else{

                        customerProfilePage.clickSignout();

                    Assert.assertTrue(true);

                }
            }

        } catch (Exception e) {
            Assert.fail("Exception : "+e.getMessage());
        }
    }

    @Test(dataProvider = "RegistrationData", dataProviderClass = RegDataProvider.class)
    public void verifyVendorRegistration(String role, String fullname, String email, String password, String mobile, String city, String expected){

        try{
            AuthPage ap = new AuthPage(driver);
            ap.setRole(role);
            ap.setTfFullName(fullname);
            ap.setTfEmail(email);
            ap.setTfPassword(password);
            ap.setTfMobile(mobile);
            ap.setTfCity(city);

            ap.clickBtnSubmit();
            boolean targetPage = false;

            CustomerDashboardPage customer = new CustomerDashboardPage(driver);
            targetPage = customer.customerDashboardMessage();
            customer.clickCustomerProfile();
            customerProfilePage = new CustomerProfilePage(driver);

            if(expected.equalsIgnoreCase("valid")){
                if(targetPage){

                    customerProfilePage.clickSignout();

                    Assert.assertTrue(true);

                }else{
                    ap.clearAll();
                    Assert.assertTrue(false);
                }
            }else{
                if(targetPage){
                    ap.clearAll();
                    Assert.assertTrue(false);
                }else{

                    customerProfilePage.clickSignout();

                    Assert.assertTrue(true);

                }
            }

        } catch (Exception e) {
            Assert.fail("Exception : "+e.getMessage());
        }
    }

    @Test(dataProvider = "RegistrationData", dataProviderClass = RegDataProvider.class)
    public void verifyAdminRegistration(String role, String fullname, String email, String password, String mobile, String city, String expected){

        try{
            AuthPage ap = new AuthPage(driver);
            ap.setRole(role);
            ap.setTfFullName(fullname);
            ap.setTfEmail(email);
            ap.setTfPassword(password);
            ap.setTfMobile(mobile);
            ap.setTfCity(city);

            ap.clickBtnSubmit();
            boolean targetPage = false;

            CustomerDashboardPage customer = new CustomerDashboardPage(driver);
            targetPage = customer.customerDashboardMessage();
            customer.clickCustomerProfile();
            customerProfilePage = new CustomerProfilePage(driver);

            if(expected.equalsIgnoreCase("valid")){
                if(targetPage){

                    customerProfilePage.clickSignout();

                    Assert.assertTrue(true);

                }else{
                    ap.clearAll();
                    Assert.assertTrue(false);
                }
            }else{
                if(targetPage){
                    ap.clearAll();
                    Assert.assertTrue(false);
                }else{

                    customerProfilePage.clickSignout();

                    Assert.assertTrue(true);

                }
            }

        } catch (Exception e) {
            Assert.fail("Exception : "+e.getMessage());
        }
    }
}
