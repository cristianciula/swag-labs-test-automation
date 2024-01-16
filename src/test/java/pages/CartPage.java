package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ColorCodesUtils;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    //__________LOCATORS__________//
    private By pageTitle = By.xpath("//span[@class=\"title\"]");
    private By productNameLabels = By.xpath("//div[@class=\"inventory_item_name\"]");
    private By productPrice(String productName) {
        return By.xpath("//div[@class=\"inventory_item_name\"][text()=\""+productName+"\"]/following::div[@class=\"inventory_item_price\"][1]");
    }
    private By checkoutButton = By.id("checkout");
    private By productQuantityLabel(String productName) {
        return By.xpath("//div[@class=\"inventory_item_name\"][text()=\""+productName+"\"]/ancestor::div[@class=\"cart_item\"]/div[@class=\"cart_quantity\"]");
    }

    //__________METHODS__________//
    public List<String> getAllProductsInCart() {
        List<WebElement> productsNamesLabels = driver.findElements(productNameLabels);
        List<String> productsNames = new ArrayList<>();

        for (WebElement productNameLabel : productsNamesLabels) {
            productsNames.add(productNameLabel.getText());
        }
        return productsNames;
    }
    public String getProductPrice(String productName) {
        return driver.findElement(productPrice(productName)).getText();
    }
    public boolean checkoutButtonIsEnabled() {
        return driver.findElement(checkoutButton).isEnabled();
    }
    public boolean checkoutButtonIsDisplayed() {
        return driver.findElement(checkoutButton).isDisplayed();
    }
    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }
    public String getProductQuantity(String productName) {
        return driver.findElement(productQuantityLabel(productName)).getText();
    }
    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }
    public String getCheckoutButtonColor() {
        String rgba = driver.findElement(checkoutButton).getCssValue("background-color");
        return ColorCodesUtils.rgbaToHex(rgba);
    }
    public String getCheckoutButtonText() {
        return driver.findElement(checkoutButton).getText();
    }
}
