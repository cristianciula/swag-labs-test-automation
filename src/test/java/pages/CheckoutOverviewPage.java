package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ColorCodesUtils;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage {

    WebDriver driver;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    //__________LOCATORS__________//
    private By pageTitle = By.xpath("//span[@class=\"title\"]");
    private By productNameLabels = By.xpath("//div[@class=\"inventory_item_name\"]");
    private By productDescription(String productName) {
        return By.xpath("//div[@class=\"inventory_item_name\"][text()=\""+productName+"\"]/following::div[@class=\"inventory_item_desc\"][1]");
    }
    private By productPrice(String productName) {
        return By.xpath("//div[@class=\"inventory_item_name\"][text()=\""+productName+"\"]/following::div[@class=\"inventory_item_price\"][1]");
    }
    private By productQuantityLabel(String productName) {
        return By.xpath("//div[@class=\"inventory_item_name\"][text()=\""+productName+"\"]/ancestor::div[@class=\"cart_item\"]/div[@class=\"cart_quantity\"]");
    }
    private By cancelButton = By.id("cancel");
    private By finishButton = By.id("finish");
    private By paymentInformationValueLabel = By.xpath("//div[@class=\"summary_value_label\"][1]");
    private By shippingInformationValueLabel = By.xpath("//div[@class=\"summary_value_label\"][2]");
    private By itemTotalValueLabel = By.xpath("//div[@class=\"summary_subtotal_label\"]");
    private By taxValueLabel = By.xpath("//div[@class=\"summary_tax_label\"]");
    private By totalLabel = By.xpath("//div[@class=\"summary_info_label summary_total_label\"]");

    //__________METHODS__________//
    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }
    public List<String> getAllProductsInCart() {
        List<WebElement> productsNamesLabels = driver.findElements(productNameLabels);
        List<String> productsNames = new ArrayList<>();

        for (WebElement productNameLabel : productsNamesLabels) {
            productsNames.add(productNameLabel.getText());
        }
        return productsNames;
    }
    public String getProductDescription(String productName) {
        return driver.findElement(productDescription(productName)).getText();
    }
    public String getProductPrice(String productName) {
        return driver.findElement(productPrice(productName)).getText();
    }
    public String getProductQuantity(String productName) {
        return driver.findElement(productQuantityLabel(productName)).getText();
    }
    public boolean cancelButtonIsEnabled() {
        return driver.findElement(cancelButton).isEnabled();
    }
    public boolean finishButtonIsEnabled() {
        return driver.findElement(finishButton).isEnabled();
    }
    public String getFinishButtonColor() {
        String rgba = driver.findElement(finishButton).getCssValue("background-color");
        return ColorCodesUtils.rgbaToHex(rgba);
    }
    public String getFinishButtonText() {
        return driver.findElement(finishButton).getText();
    }
    public void clickFinish() {
        driver.findElement(finishButton).click();
    }
    public String getCardDetails() {
        return driver.findElement(paymentInformationValueLabel).getText();
    }
    public String getShippingInformation() {
        return driver.findElement(shippingInformationValueLabel).getText();
    }
    public boolean totalLabelIsDisplayed() {
        return driver.findElement(totalLabel).isDisplayed();
    }
    public String getItemTotalValue() {
        return driver.findElement(itemTotalValueLabel).getText();
    }
    public String getTaxValue() {
        return driver.findElement(taxValueLabel).getText();
    }
    public String getTotalValue() {
        return driver.findElement(totalLabel).getText();
    }
}
