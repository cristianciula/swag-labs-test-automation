package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage {
    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    //__________LOCATORS__________//
    private By pageTitle = By.xpath("//span[@class=\"title\"]");
    private By productNameLabels = By.xpath("//div[@class=\"inventory_item_name \"]");
    private By productPriceLabels = By.xpath("//div[@class=\"inventory_item_price\"]");
    private By sortSelector = By.xpath("//select[@class=\"product_sort_container\"]");
    private By sortNameAZOption = By.xpath("//option[@value=\"az\"]");
    private By sortNameZAOption = By.xpath("//option[@value=\"za\"]");
    private By sortPriceLowToHighOption = By.xpath("//option[@value=\"lohi\"]");
    private By sortPriceHighToLowOption = By.xpath("//option[@value=\"hilo\"]");
    private By productName(String productName) {
        return By.xpath("//div[@class=\"inventory_item_name \"][contains(.,\""+productName+"\")]");
    }
    private By productImage(String productName) {
        return By.xpath("//img[@class=\"inventory_item_img\"][@alt=\""+productName+"\"]");
    }
    private By productDescription(String productName) {
        return By.xpath("//div[@class=\"inventory_item_name \"][text()=\""+productName+"\"]/following::div[@class=\"inventory_item_desc\"][1]");
    }
    private By productPrice(String productName) {
        return By.xpath("//div[@class=\"inventory_item_name \"][text()=\""+productName+"\"]/following::div[@class=\"inventory_item_price\"][1]");
    }
    private By addToCartButton(String productName) {
        return By.xpath("//div[@class=\"inventory_item_name \"][contains(.,\""+productName+"\")]/following::button[1]");
    }
    private By removeButton(String productName) {
        return By.xpath("//div[@class=\"inventory_item_name \"][contains(.,\""+productName+"\")]/following::button[contains(.,\"Remove\")][1]");
    }

    //__________METHODS__________//
    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }
    public List<String> getAllProductsNames() {
        List<WebElement> productsNamesLabels = driver.findElements(productNameLabels);
        List<String> productsNames = new ArrayList<>();

        for (WebElement productNameLabel : productsNamesLabels) {
            productsNames.add(productNameLabel.getText());
        }
        return productsNames;
    }
    public List<Double> getAllProductsPrices() {
        List<WebElement> productsPricesLabels = driver.findElements(productPriceLabels);
        List<Double> productsPrices = new ArrayList<>();

        for (WebElement productPriceLabel : productsPricesLabels) {
            productsPrices.add(Double.parseDouble(productPriceLabel.getText().replace("$", "")));
        }
        return productsPrices;
    }
    public boolean sortingIsDisplayed() {
        return driver.findElement(sortSelector).isDisplayed();
    }
    public boolean sortNameAZIsSelected() {
        return driver.findElement(sortNameAZOption).isSelected();
    }
    public void sortProductsByNameAToZ() {
        driver.findElement(sortNameAZOption).click();
    }
    public void sortProductsByNameZToA() {
        driver.findElement(sortNameZAOption).click();
    }
    public void sortProductsByPriceLowToHigh() {
        driver.findElement(sortPriceLowToHighOption).click();
    }
    public void sortProductsByPriceHighToLow() {
        driver.findElement(sortPriceHighToLowOption).click();
    }
    public void clickAddToCartButton(String productName) {
        driver.findElement(addToCartButton(productName)).click();
    }
    public String getAddToCartButtonText(String productName) {
        return driver.findElement(addToCartButton(productName)).getText();
    }
    public void clickRemoveButton(String productName) {
        driver.findElement(removeButton(productName)).click();
    }
    public String getRemoveButtonText(String productName) {
        return driver.findElement(removeButton(productName)).getText();
    }
    public void clickProductName(String productName) {
        driver.findElement(productName(productName)).click();
    }
    public boolean productImageIsDisplayed(String productName) {
        return driver.findElement(productImage(productName)).isDisplayed();
    }
    public String getProductImage(String productName) {
        return driver.findElement(productImage(productName)).getAttribute("src");
    }
    public String getProductDescription(String productName) {
        return driver.findElement(productDescription(productName)).getText();
    }
    public String getProductPrice(String productName) {
        return driver.findElement(productPrice(productName)).getText();
    }
}
