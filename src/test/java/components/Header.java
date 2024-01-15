package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ColorCodesUtils;

public class Header {
    WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    //LOCATORS
    private By shoppingCartButton = By.id("shopping_cart_container");
    private By productsInCartCounter = By.xpath("//a[@class=\"shopping_cart_link\"]");
    private By shoppingCartBadge = By.xpath("//span[@class=\"shopping_cart_badge\"]");
    private By menuButton = By.id("react-burger-menu-btn");

    //ACTIONS
    public boolean shoppingCartButtonIsDisplayed() {
        return driver.findElement(shoppingCartButton).isDisplayed();
    }
    public void clickMenu() {
        driver.findElement(menuButton).click();
    }
    public boolean menuButtonIsDisplayed() {
        return driver.findElement(menuButton).isDisplayed();
    }
    public boolean menuButtonIsEnabled() {
        return driver.findElement(menuButton).isEnabled();
    }
    public String getCartBadgeValue() {
        return driver.findElement(productsInCartCounter).getText();
    }
    public boolean cartBadgeIsDisplayed() {
        return !driver.findElement(productsInCartCounter).getText().isEmpty();
    }
    public String getShoppingCartBadgeColor() {
        String rgba = driver.findElement(shoppingCartBadge).getCssValue("background-color");
        return ColorCodesUtils.rgbaToHex(rgba);
    }
    public void clickShoppingCart() {
        driver.findElement(shoppingCartButton).click();
    }
}
