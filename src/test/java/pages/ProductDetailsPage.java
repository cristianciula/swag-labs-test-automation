package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testdata.Product;
import utils.ColorCodesUtils;

public class ProductDetailsPage {
    WebDriver driver;

    public static Product product = new Product("product");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    //LOCATORS
    private By productName = By.xpath("//div[@class=\"inventory_details_name large_size\"]");
    private By productDescription = By.xpath("//div[@class=\"inventory_details_desc large_size\"]");
    private By productPrice = By.xpath("//div[@class=\"inventory_details_price\"]");
    private By addToCartButton = By.id("add-to-cart-sauce-labs-bike-light");
    private By backToProductsButton = By.id("back-to-products");
    private By removeButton = By.xpath("//button[@class=\"btn btn_secondary btn_small btn_inventory\"]");
    private By productImage = By.xpath("//img[@class=\"inventory_details_img\"]");

    //ACTIONS
    public String getProductName() {
        return driver.findElement(productName).getText();
    }
    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }
    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }
    public boolean addToCartButtonIsEnabled() {
        return driver.findElement(addToCartButton).isEnabled();
    }
    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
    }
    public String getRemoveButtonText() {
        return driver.findElement(removeButton).getText();
    }
    public String getRemoveButtonTextColor() {
        String rgba = driver.findElement(removeButton).getCssValue("color");
        return ColorCodesUtils.rgbaToHex(rgba);
    }
    public boolean backToProductsButtonIsDisplayed() {
        return driver.findElement(backToProductsButton).isDisplayed();
    }
    public boolean productImageIsDisplayed() {
        return driver.findElement(productImage).isDisplayed();
    }
    public String getProductImage() {
        return driver.findElement(productImage).getAttribute("src");
    }
}
