package com.automation.ui.tests;

import com.automation.ui.base.SetUp;
import com.automation.ui.data.CheckoutData;
import com.automation.ui.data.LoginData;
import com.automation.ui.data.ProductData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cart Tests")
public class CartTest extends SetUp {

    @BeforeEach
    void loginAndAddProduct() {
        loginPage.login(LoginData.STANDARD_USER, LoginData.PASSWORD);
        inventoryPage.addProductToCart(ProductData.ADD_BACKPACK);
        inventoryPage.goToCart();
    }

    @Test
    @DisplayName("Cart page displays correct title")
    void cartPageHasCorrectTitle() {
        assertEquals(CheckoutData.CART_PAGE_TITLE, cartPage.getPageTitle());
    }

    @Test
    @DisplayName("Cart contains the added product")
    void cartContainsAddedProduct() {
        assertEquals(1, cartPage.getCartItemCount());
    }

    @Test
    @DisplayName("Removing a product empties the cart")
    void removingProductEmptiesCart() {
        cartPage.removeProduct(ProductData.REMOVE_BACKPACK);
        assertEquals(0, cartPage.getCartItemCount());
    }

    @Test
    @DisplayName("Proceeding to checkout navigates to checkout info page")
    void proceedToCheckoutNavigatesToInfoPage() {
        cartPage.proceedToCheckout();
        assertEquals(CheckoutData.INFO_PAGE_TITLE, checkoutInfoPage.getPageTitle());
    }
}
