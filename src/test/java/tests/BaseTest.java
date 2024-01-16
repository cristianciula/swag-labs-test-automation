package tests;

import components.Header;
import components.Menu;
import io.github.bonigarcia.wdm.WebDriverManager;
import constants.LoginConst;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;
import testdata.URL;
import testdata.User;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseTest {
    WebDriver driver;
    public static Menu menu;
    public static Header header;
    public static CartPage cartPage;
    public static LoginPage loginPage;
    public static ProductsPage productsPage;
    public static ProductDetailsPage productDetailsPage;
    public static CheckoutInfoPage checkoutInfoPage;
    public static CheckoutOverviewPage checkoutOverviewPage;
    public static CheckoutCompletePage checkoutCompletePage;

    public static User standardUser = new User("standardUser");

    @BeforeAll
    public static void setUp() {

    }
    @AfterAll
    public static void tearDown() {

    }
    @BeforeEach
    public void beforeEach() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        menu = new Menu(driver);
        header = new Header(driver);
        cartPage = new CartPage(driver);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        checkoutInfoPage = new CheckoutInfoPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);

        driver.get(URL.LOGIN_PAGE);
        assertTrue(loginPage.loginButtonIsEnabled());
        assertEquals(LoginConst.LOGIN_BUTTON_COLOR, loginPage.getLoginButtonColor());
        assertEquals(LoginConst.USERNAME_PLACEHOLDER, loginPage.getUsernamePlaceholder());
        assertEquals(LoginConst.PASSWORD_PLACEHOLDER, loginPage.getPasswordPlaceholder());
    }
    @AfterEach
    public void afterEach() {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }
}
