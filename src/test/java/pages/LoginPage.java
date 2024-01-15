package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testdata.User;
import utils.ColorCodesUtils;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //LOCATORS
    public By usernameInput = By.id("user-name");
    public By passwordInput = By.id("password");
    public By loginButton = By.id("login-button");
    public By errorMessage = By.xpath("//div[@class=\"error-message-container error\"]");

    //ACTIONS
    public boolean loginButtonIsEnabled() {
        return driver.findElement(loginButton).isEnabled();
    }
    public boolean loginButtonIsDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }
    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
    public void authenticate(User user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
        clickLogin();
    }
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
    public String getLoginButtonColor() {
        String rgba = driver.findElement(loginButton).getCssValue("background-color");
        return ColorCodesUtils.rgbaToHex(rgba);
    }
    public String getUsernamePlaceholder() {
        return driver.findElement(usernameInput).getAttribute("placeholder");
    }
    public String getPasswordPlaceholder() {
        return driver.findElement(passwordInput).getAttribute("placeholder");
    }
    public String getErrorBackgroundColor() {
        String rgba = driver.findElement(errorMessage).getCssValue("background-color");
        return ColorCodesUtils.rgbaToHex(rgba);
    }
}
