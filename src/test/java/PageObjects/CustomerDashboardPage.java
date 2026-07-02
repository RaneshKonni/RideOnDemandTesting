

package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


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
            waitForElementToBeVisible(customerWelcomeMsg);
            return customerWelcomeMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getWelcomeMessage() {
        waitForElementToBeVisible(customerWelcomeMsg);
        return customerWelcomeMsg.getText();
    }

    public boolean isActiveRequirementsHeadingDisplayed() {
        try {
            waitForElementToBeVisible(activeRequirementsHeading);
            return activeRequirementsHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isVehicleRequirementTextDisplayed() {
        try {
            waitForElementToBeVisible(txtNeedVehicle);
            return txtNeedVehicle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getVehicleRequirementText() {
        waitForElementToBeVisible(txtNeedVehicle);
        return txtNeedVehicle.getText();
    }

    public int getActiveRequirementsCount() {
        waitForElementToBeVisible(activeRequirementsCount);
        return Integer.parseInt(activeRequirementsCount.getText());
    }

    public int getRequirementItemsCount() {
        return requirementItems.size();
    }

    public boolean isMyRequirementsSectionEmpty() {
        return requirementItems.isEmpty();
    }

    public void clickCustomerProfile() {
        waitForElementToBeClickable(btnCustomerProfile).click();
    }

    public void clickPostRequirement() {
        waitForElementToBeClickable(btnPostRequirement).click();
    }

    public boolean isProfileButtonDisplayed() {
        try {
            waitForElementToBeVisible(btnCustomerProfile);
            return btnCustomerProfile.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPostRequirementButtonDisplayed() {
        try {
            waitForElementToBeVisible(btnPostRequirement);
            return btnPostRequirement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAcceptedRequirement() {
        waitForElementToBeClickable(btnAcceptedRequirement).click();
    }

    public boolean isAcceptedRequirementDisplayed() {
        try {
            waitForElementToBeVisible(btnAcceptedRequirement);
            return btnAcceptedRequirement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}