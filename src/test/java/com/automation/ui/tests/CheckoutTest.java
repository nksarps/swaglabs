package com.automation.ui.tests;

import com.automation.ui.base.SetUp;
import com.automation.ui.data.CheckoutData;
import com.automation.ui.data.LoginData;
import com.automation.ui.data.ProductData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("Submitting empty form shows first name required error")
    void emptyFormShowsFirstNameError() {
        checkoutInfoPage.submitWithoutInfo();
        assertTrue(checkoutInfoPage.isErrorDisplayed());
        assertEquals(CheckoutData.ERROR_FIRST_NAME_REQUIRED, checkoutInfoPage.getErrorMessage());
    }

    @Test
    @DisplayName("Submitting without last name shows last name required error")
    void missingLastNameShowsError() {
        checkoutInfoPage.enterCustomerInfo(CheckoutData.FIRST_NAME, "", CheckoutData.POSTAL_CODE);
        assertTrue(checkoutInfoPage.isErrorDisplayed());
        assertEquals(CheckoutData.ERROR_LAST_NAME_REQUIRED, checkoutInfoPage.getErrorMessage());
    }

    @Test
    @DisplayName("Submitting without postal code shows postal code required error")
    void missingPostalCodeShowsError() {
        checkoutInfoPage.enterCustomerInfo(CheckoutData.FIRST_NAME, CheckoutData.LAST_NAME, "");
        assertTrue(checkoutInfoPage.isErrorDisplayed());
        assertEquals(CheckoutData.ERROR_POSTAL_CODE_REQUIRED, checkoutInfoPage.getErrorMessage());
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
