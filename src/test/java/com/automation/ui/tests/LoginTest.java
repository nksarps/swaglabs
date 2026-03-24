package com.automation.ui.tests;

import com.automation.ui.base.SetUp;
import com.automation.ui.data.LoginData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Login Tests")
public class LoginTest extends SetUp {

    @Test
    @DisplayName("Standard user can log in successfully")
    void standardUserCanLogin() {
        loginPage.login(LoginData.STANDARD_USER, LoginData.PASSWORD);
        assertEquals("Products", inventoryPage.getPageTitle());
    }

    @Test
    @DisplayName("Locked out user sees error message")
    void lockedOutUserSeesError() {
        loginPage.login(LoginData.LOCKED_OUT_USER, LoginData.PASSWORD);
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(LoginData.ERROR_LOCKED_OUT, loginPage.getErrorMessage());
    }

    @Test
    @DisplayName("Invalid credentials show error message")
    void invalidCredentialsShowError() {
        loginPage.login(LoginData.INVALID_USERNAME, LoginData.INVALID_PASSWORD);
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(LoginData.ERROR_INVALID_CREDENTIALS, loginPage.getErrorMessage());
    }

    @Test
    @DisplayName("Empty username shows required error")
    void emptyUsernameShowsError() {
        loginPage.login("", LoginData.PASSWORD);
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(LoginData.ERROR_USERNAME_REQUIRED, loginPage.getErrorMessage());
    }

    @Test
    @DisplayName("Empty password shows required error")
    void emptyPasswordShowsError() {
        loginPage.login(LoginData.STANDARD_USER, "");
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(LoginData.ERROR_PASSWORD_REQUIRED, loginPage.getErrorMessage());
    }
}
