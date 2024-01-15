package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ColorCodesUtils;

public class CheckoutCompletePage {
    WebDriver driver;

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    //LOCATORS
    private By pageTitle = By.xpath("//span[@class=\"title\"]");
    private By checkmarkImage = By.xpath("//img[@class=\"pony_express\"]");
    private By header = By.xpath("//h2[@class=\"complete-header\"]");
    private By description = By.xpath("//div[@class=\"complete-text\"]");
    private By backHomeButton = By.id("back-to-products");

    //ACTIONS
    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }
    public String getCheckmarkImage() {
        return driver.findElement(checkmarkImage).getAttribute("src");
    }
    public boolean checkmarkImageIsDisplayed() {
        return driver.findElement(checkmarkImage).isDisplayed();
    }
    public String getHeader() {
        return driver.findElement(header).getText();
    }
    public String getDescription() {
        return driver.findElement(description).getText();
    }
    public String getBackHomeButtonText() {
        return driver.findElement(backHomeButton).getText();
    }
    public String getBackHomeButtonColor() {
        String rgba = driver.findElement(backHomeButton).getCssValue("background-color");
        return ColorCodesUtils.rgbaToHex(rgba);
    }
    public boolean backHomeButtonIsEnabled() {
        return driver.findElement(backHomeButton).isEnabled();
    }
    public void clickBackHomeButton() {
        driver.findElement(backHomeButton).click();
    }
}
