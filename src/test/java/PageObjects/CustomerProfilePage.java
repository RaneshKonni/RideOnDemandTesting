
package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


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
            waitForElementToBeVisible(profileHeading);
            return profileHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoDisplayed() {
        try {
            waitForElementToBeVisible(logoImage);
            return logoImage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAccountDetailsHeadingDisplayed() {
        try {
            waitForElementToBeVisible(accountDetailsHeading);
            return accountDetailsHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCustomerEmailDisplayed() {
        try {
            waitForElementToBeVisible(customerEmail);
            return customerEmail.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCustomerName() {
        waitForElementToBeVisible(customerName);
        return customerName.getText();
    }

    public String getCustomerEmail() {
        waitForElementToBeVisible(customerEmail);
        return customerEmail.getText();
    }

    public boolean isCustomerMobileDisplayed() {
        try {
            waitForElementToBeVisible(customerMobile);
            return customerMobile.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCustomerMobile() {
        waitForElementToBeVisible(customerMobile);
        return customerMobile.getText();
    }

    public boolean isCustomerCityDisplayed() {
        try {
            waitForElementToBeVisible(customerCity);
            return customerCity.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCustomerCity() {
        waitForElementToBeVisible(customerCity);
        return customerCity.getText();
    }

    public boolean isSignOutButtonDisplayed() {
        try {
            waitForElementToBeVisible(btnSignOut);
            return btnSignOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSignout() {
        waitForElementToBeClickable(btnSignOut).click();
    }

    public boolean isNotificationsHeadingDisplayed() {
        try {
            waitForElementToBeVisible(notificationsHeading);
            return notificationsHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isItemsCountDisplayed() {
        try {
            waitForElementToBeVisible(itemsCount);
            return itemsCount.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getItemsCount() {
        waitForElementToBeVisible(itemsCount);
        return itemsCount.getText();
    }

    public boolean isUnreadCountDisplayed() {
        try {
            waitForElementToBeVisible(unreadCount);
            return unreadCount.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getUnreadCount() {
        waitForElementToBeVisible(unreadCount);
        return unreadCount.getText();
    }

    public boolean isNoNotificationsMessageDisplayed() {
        try {
            waitForElementToBeVisible(noNotificationsMsg);
            return noNotificationsMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getNoNotificationsMessage() {
        waitForElementToBeVisible(noNotificationsMsg);
        return noNotificationsMsg.getText();
    }
}