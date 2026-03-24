package com.automation.ui.tests;

import com.automation.ui.base.SetUp;
import com.automation.ui.data.LoginData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Login Tests")
public class LoginTest extends SetUp {

    @Test
    @DisplayName("Standard user can log in successfully")
    void standardUserCanLogin() {
        loginPage.login(LoginData.STANDARD_USER, LoginData.PASSWORD);
        assertEquals(LoginData.INVENTORY_PAGE_TITLE, inventoryPage.getPageTitle());
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("invalidLoginScenarios")
    @DisplayName("Invalid login attempts show correct error message")
    void invalidLoginShowsError(String username, String password, String expectedError) {
        loginPage.login(username, password);
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(expectedError, loginPage.getErrorMessage());
    }

    static Stream<Arguments> invalidLoginScenarios() {
        return Stream.of(
            Arguments.of(LoginData.LOCKED_OUT_USER, LoginData.PASSWORD, LoginData.ERROR_LOCKED_OUT),
            Arguments.of(LoginData.INVALID_USERNAME, LoginData.INVALID_PASSWORD, LoginData.ERROR_INVALID_CREDENTIALS),
            Arguments.of("", LoginData.PASSWORD, LoginData.ERROR_USERNAME_REQUIRED),
            Arguments.of(LoginData.STANDARD_USER, "", LoginData.ERROR_PASSWORD_REQUIRED)
        );
    }
}
