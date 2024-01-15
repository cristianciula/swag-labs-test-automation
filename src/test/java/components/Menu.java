package components;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Menu {
    WebDriver driver;

    public Menu(WebDriver driver){
        this.driver = driver;
    }

    //LOCATORS
    private By logoutButton = By.id("logout_sidebar_link");

    //ACTIONS
    public void waitMenuToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
    }
    public boolean logoutButtonIsEnabled() {
        return driver.findElement(logoutButton).isEnabled();
    }
    public void clickLogout() {
        driver.findElement(logoutButton).click();
    }
}
