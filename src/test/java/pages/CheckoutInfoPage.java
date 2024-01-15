package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testdata.UserData;
import utils.ColorCodesUtils;

public class CheckoutInfoPage {
    WebDriver driver;

    public CheckoutInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    //LOCATORS
    private By pageTitleLabel = By.xpath("//span[@class=\"title\"]");
    private By cancelButton = By.id("cancel");
    private By continueButton = By.id("continue");
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By zipCodeInput = By.id("postal-code");



    //ACTIONS
    public String getPageTitle() {
        return driver.findElement(pageTitleLabel).getText();
    }
    public boolean continueButtonIsEnabled() {
        return driver.findElement(continueButton).isEnabled();
    }
    public boolean cancelButtonIsEnabled() {
        return driver.findElement(cancelButton).isEnabled();
    }
    public void clickContinue() {
        driver.findElement(continueButton).click();
    }
    public boolean firstNameInputIsDisplayed() {
        return driver.findElement(firstNameInput).isDisplayed();
    }
    public boolean lastNameInputIsDisplayed() {
        return driver.findElement(lastNameInput).isDisplayed();
    }
    public boolean zipCodeInputIsDisplayed() {
        return driver.findElement(zipCodeInput).isDisplayed();
    }
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }
    public void enterZipCode(String zipCode) {
        driver.findElement(zipCodeInput).sendKeys(zipCode);
    }
    public void fillUpForm(UserData userData) {
        enterFirstName(userData.getFirstName());
        enterLastName(userData.getLastName());
        enterZipCode(userData.getZipCode());
    }
    public String getContinueButtonText() {
        return driver.findElement(continueButton).getAttribute("value");
    }
    public String getContinueButtonColor() {
        String rgba = driver.findElement(continueButton).getCssValue("background-color");
        return ColorCodesUtils.rgbaToHex(rgba);
    }
    public String getFirstNamePlaceholder() {
        return driver.findElement(firstNameInput).getAttribute("placeholder");
    }
    public String getLastNamePlaceholder() {
        return driver.findElement(lastNameInput).getAttribute("placeholder");
    }
    public String getZipCodePlaceholder() {
        return driver.findElement(zipCodeInput).getAttribute("placeholder");
    }
}
