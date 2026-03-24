package com.automation.ui.tests;

import com.automation.ui.base.SetUp;
import com.automation.ui.data.CheckoutData;
import com.automation.ui.data.LoginData;
import com.automation.ui.data.ProductData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Checkout Tests")
public class CheckoutTest extends SetUp {

    @BeforeEach
    void loginAddProductAndProceedToCheckout() {
        loginPage.login(LoginData.STANDARD_USER, LoginData.PASSWORD);
        inventoryPage.addProductToCart(ProductData.ADD_BACKPACK);
        inventoryPage.goToCart();
        cartPage.proceedToCheckout();
    }

    @Test
    @DisplayName("Checkout info page displays correct title")
    void checkoutInfoPageHasCorrectTitle() {
        assertEquals(CheckoutData.INFO_PAGE_TITLE, checkoutInfoPage.getPageTitle());
    }

    static Stream<Arguments> checkoutFormValidationScenarios() {
        return Stream.of(
            Arguments.of("",                    CheckoutData.LAST_NAME,  CheckoutData.POSTAL_CODE, CheckoutData.ERROR_FIRST_NAME_REQUIRED),
            Arguments.of(CheckoutData.FIRST_NAME, "",                    CheckoutData.POSTAL_CODE, CheckoutData.ERROR_LAST_NAME_REQUIRED),
            Arguments.of(CheckoutData.FIRST_NAME, CheckoutData.LAST_NAME, "",                      CheckoutData.ERROR_POSTAL_CODE_REQUIRED)
        );
    }

    @ParameterizedTest(name = "{3}")
    @MethodSource("checkoutFormValidationScenarios")
    @DisplayName("Incomplete checkout form shows correct validation error")
    void incompleteFormShowsValidationError(String firstName, String lastName, String postalCode, String expectedError) {
        checkoutInfoPage.enterCustomerInfo(firstName, lastName, postalCode);
        assertTrue(checkoutInfoPage.isErrorDisplayed());
        assertEquals(expectedError, checkoutInfoPage.getErrorMessage());
    }

    @Test
    @DisplayName("Valid info navigates to checkout overview page")
    void validInfoNavigatesToOverview() {
        checkoutInfoPage.enterCustomerInfo(CheckoutData.FIRST_NAME, CheckoutData.LAST_NAME, CheckoutData.POSTAL_CODE);
        assertEquals(CheckoutData.OVERVIEW_PAGE_TITLE, checkoutOverviewPage.getPageTitle());
    }

    @Test
    @DisplayName("Overview page shows ordered item")
    void overviewPageShowsOrderedItem() {
        checkoutInfoPage.enterCustomerInfo(CheckoutData.FIRST_NAME, CheckoutData.LAST_NAME, CheckoutData.POSTAL_CODE);
        assertEquals(1, checkoutOverviewPage.getOrderItems().size());
    }

    @Test
    @DisplayName("Finishing order navigates to confirmation page")
    void finishingOrderNavigatesToConfirmation() {
        checkoutInfoPage.enterCustomerInfo(CheckoutData.FIRST_NAME, CheckoutData.LAST_NAME, CheckoutData.POSTAL_CODE);
        checkoutOverviewPage.finishOrder();
        assertEquals(CheckoutData.COMPLETE_PAGE_TITLE, checkoutCompletePage.getPageTitle());
        assertTrue(checkoutCompletePage.isOrderConfirmed());
        assertEquals(CheckoutData.CONFIRMATION_HEADER, checkoutCompletePage.getConfirmationHeader());
    }
}
