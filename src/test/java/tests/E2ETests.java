package tests;

import constants.*;
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
        assertEquals(ProductsConst.PRODUCTS_PAGE_TITLE, productsPage.getPageTitle());
        assertTrue(header.shoppingCartButtonIsDisplayed());
        assertTrue(header.menuButtonIsDisplayed());
        assertTrue(productsPage.sortingIsDisplayed());

        //Check expected product is present on the Products page with expected details
        assertTrue(productsPage.getAllProductsNames().contains(product.getName()));
        assertEquals(ProductsConst.PRODUCT_IMAGE, productsPage.getProductImage(product.getName()));
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
        assertEquals(ProductDetailsConst.PRODUCT_IMAGE, productDetailsPage.getProductImage());

        //Add product to Shopping Cart and check that expected elements have been updated accordingly
        productDetailsPage.clickAddToCart();
        assertEquals("1", header.getCartBadgeValue());
        assertEquals(HeaderConst.CART_BADGE_COLOR, header.getShoppingCartBadgeColor());
        assertEquals(ProductDetailsConst.REMOVE_BUTTON, productDetailsPage.getRemoveButtonText());
        assertEquals(ProductDetailsConst.REMOVE_BUTTON_TEXT_COLOR, productDetailsPage.getRemoveButtonTextColor());

        //Navigate to Shopping Cart and check user is on Shopping Cart page
        header.clickShoppingCart();
        assertEquals(CartConst.CART_PAGE_TITLE, cartPage.getPageTitle());
        assertTrue(cartPage.checkoutButtonIsEnabled());
        assertEquals(CartConst.CHECKOUT_BUTTON_TEXT, cartPage.getCheckoutButtonText());
        assertEquals(CartConst.CHECKOUT_SHOPPING_BUTTON_COLOR, cartPage.getCheckoutButtonColor());

        //Check expected product is present on Shopping Cart page with expected details
        assertTrue(cartPage.getAllProductsInCart().contains(product.getName()));
        assertEquals("1", cartPage.getProductQuantity(product.getName()));
        assertEquals(Currency.USD + product.getPrice(), cartPage.getProductPrice(product.getName()));

        //Continue to Checkout: Your Information page
        cartPage.clickCheckoutButton();

        //Check that user was redirected to Checkout: Your Information page
        assertEquals(CheckoutInfoConst.CHECKOUT_INFO_PAGE_TITLE, checkoutInfoPage.getPageTitle());
        assertTrue(checkoutInfoPage.firstNameInputIsDisplayed());
        assertTrue(checkoutInfoPage.lastNameInputIsDisplayed());
        assertTrue(checkoutInfoPage.zipCodeInputIsDisplayed());
        assertEquals(CheckoutInfoConst.FIRST_NAME_PLACEHOLDER, checkoutInfoPage.getFirstNamePlaceholder());
        assertEquals(CheckoutInfoConst.LAST_NAME_PLACEHOLDER, checkoutInfoPage.getLastNamePlaceholder());
        assertEquals(CheckoutInfoConst.ZIP_CODE_PLACEHOLDER, checkoutInfoPage.getZipCodePlaceholder());
        assertEquals(CheckoutInfoConst.CONTINUE_BUTTON_TEXT, checkoutInfoPage.getContinueButtonText());
        assertEquals(CheckoutInfoConst.CONTINUE_BUTTON_COLOR, checkoutInfoPage.getContinueButtonColor());
        assertTrue(checkoutInfoPage.cancelButtonIsEnabled());
        assertTrue(checkoutInfoPage.continueButtonIsEnabled());

        //Fill in the form using valid user data and continue to Checkout Overview page
        checkoutInfoPage.fillUpForm(userData);
        checkoutInfoPage.clickContinue();

        //Check that user was redirected to the Checkout Overview page
        assertEquals(CheckoutOverviewConst.CHECKOUT_OVERVIEW_PAGE_TITLE, checkoutOverviewPage.getPageTitle());
        assertTrue(checkoutOverviewPage.totalLabelIsDisplayed());
        assertTrue(checkoutOverviewPage.cancelButtonIsEnabled());
        assertTrue(checkoutOverviewPage.finishButtonIsEnabled());
        assertEquals(CheckoutOverviewConst.FINISH_BUTTON_TEXT, checkoutOverviewPage.getFinishButtonText());
        assertEquals(CheckoutOverviewConst.FINISH_BUTTON_COLOR, checkoutOverviewPage.getFinishButtonColor());

        //Check the product details on the Checkout Overview page
        assertTrue(checkoutOverviewPage.getAllProductsInCart().contains(product.getName()));
        assertEquals(product.getDescription(), checkoutOverviewPage.getProductDescription(product.getName()));
        assertEquals(Currency.USD + product.getPrice(), checkoutOverviewPage.getProductPrice(product.getName()));
        assertEquals("1", checkoutOverviewPage.getProductQuantity(product.getName()));

        //Check the Payment and Shipping details on the Checkout Overview page
        assertEquals(userData.getCreditCard(), checkoutOverviewPage.getCardDetails());
        assertEquals(CheckoutOverviewConst.SHIPPING_INFORMATION, checkoutOverviewPage.getShippingInformation());

        //Check the Price Total values and Total value on the Checkout Overview page
        assertEquals(CheckoutOverviewConst.ITEM_TOTAL_TEXT + Currency.USD + product.getPrice(),
                checkoutOverviewPage.getItemTotalValue());
        assertEquals(CheckoutOverviewConst.TAX_TEXT + Currency.USD +
                StringsUtils.twoDecimalsFormatter(product.getPrice() * CheckoutOverviewConst.TAX_PERCENTAGE),
                checkoutOverviewPage.getTaxValue());
        assertEquals(CheckoutOverviewConst.TOTAL_TEXT + Currency.USD +
                        StringsUtils.twoDecimalsFormatter((product.getPrice() * CheckoutOverviewConst.TAX_PERCENTAGE) + product.getPrice()),
                checkoutOverviewPage.getTotalValue());

        //Finish the order and check that user was redirected to the Checkout Complete! page
        checkoutOverviewPage.clickFinish();
        assertEquals(CheckoutCompleteConst.PAGE_TITLE, checkoutCompletePage.getPageTitle());
        assertTrue(checkoutCompletePage.checkmarkImageIsDisplayed());
        assertEquals(CheckoutCompleteConst.CHECKMARK_IMAGE, checkoutCompletePage.getCheckmarkImage());
        assertEquals(CheckoutCompleteConst.HEADER, checkoutCompletePage.getHeader());
        assertEquals(CheckoutCompleteConst.DESCRIPTION, checkoutCompletePage.getDescription());
        assertTrue(checkoutCompletePage.backHomeButtonIsEnabled());
        assertEquals(CheckoutCompleteConst.BACK_HOME_BUTTON_TEXT, checkoutCompletePage.getBackHomeButtonText());
        assertEquals(CheckoutCompleteConst.BACK_HOME_BUTTON_COLOR, checkoutCompletePage.getBackHomeButtonColor());

        //Navigate to Products page and check user was redirected to Products page
        checkoutCompletePage.clickBackHomeButton();
        assertEquals(ProductsConst.PRODUCTS_PAGE_TITLE, productsPage.getPageTitle());
        assertTrue(header.shoppingCartButtonIsDisplayed());
        assertTrue(productsPage.sortingIsDisplayed());
    }
}
