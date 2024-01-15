package tests;

import messages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testdata.Currency;
import testdata.Product;
import testdata.UserData;
import utils.StringsUtils;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class E2ETests extends BaseTest {

    public static Product product = new Product("product");
    public static UserData userData = new UserData("userData");

    @BeforeEach
    public void beforeEach() {
        super.beforeEach();

        //Login and check user is logged in
        loginPage.authenticate(standardUser);
        assertEquals(ProductsMessages.PRODUCTS_PAGE_TITLE, productsPage.getPageTitle());
        assertTrue(header.shoppingCartButtonIsDisplayed());
        assertTrue(header.menuButtonIsDisplayed());
        assertTrue(productsPage.sortingIsDisplayed());

        //Check expected product is present on the Products page with expected details
        assertTrue(productsPage.getAllProductsNames().contains(product.getName()));
        assertEquals(ProductsMessages.PRODUCT_IMAGE, productsPage.getProductImage(product.getName()));
        assertEquals(product.getDescription(), productsPage.getProductDescription(product.getName()));
        assertEquals(Currency.USD + product.getPrice(), productsPage.getProductPrice(product.getName()));
    }

    @Test
    public void buyProduct() {
        //Open Product Details page and check product details are the expected ones
        productsPage.clickProductName(product.getName());
        assertTrue(productDetailsPage.addToCartButtonIsEnabled());
        assertTrue(productDetailsPage.backToProductsButtonIsDisplayed());
        assertEquals(product.getName(), productDetailsPage.getProductName());
        assertEquals(product.getDescription(), productDetailsPage.getProductDescription());
        assertEquals(Currency.USD + product.getPrice(), productDetailsPage.getProductPrice());
        assertTrue(productDetailsPage.productImageIsDisplayed());
        assertEquals(ProductDetailsMessages.PRODUCT_IMAGE, productDetailsPage.getProductImage());

        //Add product to Shopping Cart and check that expected elements have been updated accordingly
        productDetailsPage.clickAddToCart();
        assertEquals("1", header.getCartBadgeValue());
        assertEquals(HeaderMessages.CART_BADGE_COLOR, header.getShoppingCartBadgeColor());
        assertEquals(ProductDetailsMessages.REMOVE_BUTTON, productDetailsPage.getRemoveButtonText());
        assertEquals(ProductDetailsMessages.REMOVE_BUTTON_TEXT_COLOR, productDetailsPage.getRemoveButtonTextColor());

        //Navigate to Shopping Cart and check user is on Shopping Cart page
        header.clickShoppingCart();
        assertEquals(CartMessages.CART_PAGE_TITLE, cartPage.getPageTitle());
        assertTrue(cartPage.checkoutButtonIsEnabled());
        assertEquals(CartMessages.CHECKOUT_BUTTON_TEXT, cartPage.getCheckoutButtonText());
        assertEquals(CartMessages.CHECKOUT_SHOPPING_BUTTON_COLOR, cartPage.getCheckoutButtonColor());

        //Check expected product is present on Shopping Cart page with expected details
        assertTrue(cartPage.getAllProductsInCart().contains(product.getName()));
        assertEquals("1", cartPage.getProductQuantity(product.getName()));
        assertEquals(Currency.USD + product.getPrice(), cartPage.getProductPrice(product.getName()));

        //Continue to Checkout: Your Information page
        cartPage.clickCheckoutButton();

        //Check that user was redirected to Checkout: Your Information page
        assertEquals(CheckoutInfoMessages.CHECKOUT_INFO_PAGE_TITLE, checkoutInfoPage.getPageTitle());
        assertTrue(checkoutInfoPage.firstNameInputIsDisplayed());
        assertTrue(checkoutInfoPage.lastNameInputIsDisplayed());
        assertTrue(checkoutInfoPage.zipCodeInputIsDisplayed());
        assertEquals(CheckoutInfoMessages.FIRST_NAME_PLACEHOLDER, checkoutInfoPage.getFirstNamePlaceholder());
        assertEquals(CheckoutInfoMessages.LAST_NAME_PLACEHOLDER, checkoutInfoPage.getLastNamePlaceholder());
        assertEquals(CheckoutInfoMessages.ZIP_CODE_PLACEHOLDER, checkoutInfoPage.getZipCodePlaceholder());
        assertEquals(CheckoutInfoMessages.CONTINUE_BUTTON_TEXT, checkoutInfoPage.getContinueButtonText());
        assertEquals(CheckoutInfoMessages.CONTINUE_BUTTON_COLOR, checkoutInfoPage.getContinueButtonColor());
        assertTrue(checkoutInfoPage.cancelButtonIsEnabled());
        assertTrue(checkoutInfoPage.continueButtonIsEnabled());

        //Fill in the form using valid user data and continue to Checkout Overview page
        checkoutInfoPage.fillUpForm(userData);
        checkoutInfoPage.clickContinue();

        //Check that user was redirected to the Checkout Overview page
        assertEquals(CheckoutOverviewMessages.CHECKOUT_OVERVIEW_PAGE_TITLE, checkoutOverviewPage.getPageTitle());
        assertTrue(checkoutOverviewPage.totalLabelIsDisplayed());
        assertTrue(checkoutOverviewPage.cancelButtonIsEnabled());
        assertTrue(checkoutOverviewPage.finishButtonIsEnabled());
        assertEquals(CheckoutOverviewMessages.FINISH_BUTTON_TEXT, checkoutOverviewPage.getFinishButtonText());
        assertEquals(CheckoutOverviewMessages.FINISH_BUTTON_COLOR, checkoutOverviewPage.getFinishButtonColor());

        //Check the product details on the Checkout Overview page
        assertTrue(checkoutOverviewPage.getAllProductsInCart().contains(product.getName()));
        assertEquals(product.getDescription(), checkoutOverviewPage.getProductDescription(product.getName()));
        assertEquals(Currency.USD + product.getPrice(), checkoutOverviewPage.getProductPrice(product.getName()));
        assertEquals("1", checkoutOverviewPage.getProductQuantity(product.getName()));

        //Check the Payment and Shipping details on the Checkout Overview page
        assertEquals(userData.getCreditCard(), checkoutOverviewPage.getCardDetails());
        assertEquals(CheckoutOverviewMessages.SHIPPING_INFORMATION, checkoutOverviewPage.getShippingInformation());

        //Check the Price Total values and Total value on the Checkout Overview page
        assertEquals(CheckoutOverviewMessages.ITEM_TOTAL_TEXT + Currency.USD + product.getPrice(),
                checkoutOverviewPage.getItemTotalValue());
        assertEquals(CheckoutOverviewMessages.TAX_TEXT + Currency.USD +
                StringsUtils.twoDecimalsFormatter(product.getPrice() * CheckoutOverviewMessages.TAX_PERCENTAGE),
                checkoutOverviewPage.getTaxValue());
        assertEquals(CheckoutOverviewMessages.TOTAL_TEXT + Currency.USD +
                        StringsUtils.twoDecimalsFormatter((product.getPrice() * CheckoutOverviewMessages.TAX_PERCENTAGE) + product.getPrice()),
                checkoutOverviewPage.getTotalValue());

        //Finish the order and check that user was redirected to the Checkout Complete! page
        checkoutOverviewPage.clickFinish();
        assertEquals(CheckoutCompleteMessages.PAGE_TITLE, checkoutCompletePage.getPageTitle());
        assertTrue(checkoutCompletePage.checkmarkImageIsDisplayed());
        assertEquals(CheckoutCompleteMessages.CHECKMARK_IMAGE, checkoutCompletePage.getCheckmarkImage());
        assertEquals(CheckoutCompleteMessages.HEADER, checkoutCompletePage.getHeader());
        assertEquals(CheckoutCompleteMessages.DESCRIPTION, checkoutCompletePage.getDescription());
        assertTrue(checkoutCompletePage.backHomeButtonIsEnabled());
        assertEquals(CheckoutCompleteMessages.BACK_HOME_BUTTON_TEXT, checkoutCompletePage.getBackHomeButtonText());
        assertEquals(CheckoutCompleteMessages.BACK_HOME_BUTTON_COLOR, checkoutCompletePage.getBackHomeButtonColor());

        //Navigate to Products page and check user was redirected to Products page
        checkoutCompletePage.clickBackHomeButton();
        assertEquals(ProductsMessages.PRODUCTS_PAGE_TITLE, productsPage.getPageTitle());
        assertTrue(header.shoppingCartButtonIsDisplayed());
        assertTrue(productsPage.sortingIsDisplayed());
    }
}
