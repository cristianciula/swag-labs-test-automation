package tests;

import constants.LoginConst;
import constants.ProductsConst;
import org.junit.jupiter.api.Test;
import testdata.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends BaseTest {
    public static User wrongUser = new User("wrongUser");
    public static User lockedOutUser = new User("lockedOutUser");

    @Test
    public void validUserLogin() {
        loginPage.enterUsername(standardUser.getUsername());
        loginPage.enterPassword(standardUser.getPassword());
        loginPage.clickLogin();

        assertEquals(ProductsConst.PRODUCTS_PAGE_TITLE, productsPage.getPageTitle());
        assertTrue(header.shoppingCartButtonIsDisplayed());
        assertTrue(header.menuButtonIsDisplayed());
        assertTrue(productsPage.sortingIsDisplayed());
    }

    @Test
    public void invalidUserLogin() {
        loginPage.enterUsername(wrongUser.getRandomUsername());
        loginPage.enterPassword(wrongUser.getPassword());
        loginPage.clickLogin();

        assertEquals(LoginConst.INVALID_USER_LOGIN_ERROR, loginPage.getErrorMessage());
        assertEquals(LoginConst.LOGIN_ERROR_BACKGROUND_COLOR, loginPage.getErrorBackgroundColor());
    }

    @Test
    public void lockedOutUserLogin() {
        loginPage.enterUsername(lockedOutUser.getUsername());
        loginPage.enterPassword(lockedOutUser.getPassword());
        loginPage.clickLogin();

        assertEquals(LoginConst.LOCKED_USER_LOGIN_ERROR, loginPage.getErrorMessage());
        assertEquals(LoginConst.LOGIN_ERROR_BACKGROUND_COLOR, loginPage.getErrorBackgroundColor());
    }

    @Test
    public void emptyCredentialsLogin() {
        loginPage.clickLogin();

        assertEquals(LoginConst.MISSING_USERNAME_ERROR, loginPage.getErrorMessage());
        assertEquals(LoginConst.LOGIN_ERROR_BACKGROUND_COLOR, loginPage.getErrorBackgroundColor());
    }

    @Test
    public void emptyPasswordLogin() {
        loginPage.enterUsername(standardUser.getUsername());
        loginPage.clickLogin();

        assertEquals(LoginConst.MISSING_PASSWORD_ERROR, loginPage.getErrorMessage());
        assertEquals(LoginConst.LOGIN_ERROR_BACKGROUND_COLOR, loginPage.getErrorBackgroundColor());
    }

    @Test
    public void logout() {
        loginPage.authenticate(standardUser);
        assertEquals(ProductsConst.PRODUCTS_PAGE_TITLE, productsPage.getPageTitle());
        assertTrue(header.shoppingCartButtonIsDisplayed());
        assertTrue(header.menuButtonIsEnabled());

        header.clickMenu();
        menu.waitMenuToLoad();
        assertTrue(menu.logoutButtonIsEnabled());

        menu.clickLogout();
        assertTrue(loginPage.loginButtonIsDisplayed());
    }
}
