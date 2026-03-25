package com.automation.ui.tests;

import com.automation.ui.base.SetUp;
import com.automation.ui.data.LoginData;
import com.automation.ui.data.ProductData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Inventory Tests")
public class InventoryTest extends SetUp {

    @BeforeEach
    void login() {
        loginPage.login(LoginData.STANDARD_USER, LoginData.PASSWORD);
    }

    @Test
    @DisplayName("Inventory page displays correct title")
    void inventoryPageHasCorrectTitle() {
        assertEquals(ProductData.PAGE_TITLE, inventoryPage.getPageTitle());
    }

    @Test
    @DisplayName("Inventory page displays all products")
    void inventoryPageDisplaysAllProducts() {
        assertEquals(ProductData.TOTAL_PRODUCT_COUNT, inventoryPage.getProductCount());
    }

    @Test
    @DisplayName("Adding a product updates the cart badge")
    void addingProductUpdatesCartBadge() {
        inventoryPage.addProductToCart(ProductData.ADD_BACKPACK);
        assertTrue(inventoryPage.isCartBadgeDisplayed());
        assertEquals("1", inventoryPage.getCartBadgeCount());
    }

    @Test
    @DisplayName("Adding multiple products updates cart badge count")
    void addingMultipleProductsUpdatesBadgeCount() {
        inventoryPage.addProductToCart(ProductData.ADD_BACKPACK);
        inventoryPage.addProductToCart(ProductData.ADD_BIKE_LIGHT);
        assertEquals("2", inventoryPage.getCartBadgeCount());
    }

    @Test
    @DisplayName("User can log out from inventory page")
    void userCanLogout() {
        inventoryPage.logout();
        assertTrue(loginPage.isErrorDisplayed() == false);
        // After logout the login button should be present (page reloaded to login)
        assertDoesNotThrow(() -> loginPage.login(LoginData.STANDARD_USER, LoginData.PASSWORD));
    }
}
