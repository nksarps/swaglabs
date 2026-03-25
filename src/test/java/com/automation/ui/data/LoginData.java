package com.automation.ui.data;

/**
 * Test data constants for the Login page.
 * Usernames sourced from the SauceDemo accepted credentials.
 */
public class LoginData {

    private LoginData() {}

    public static final String PASSWORD = "secret_sauce";

    // Valid users
    public static final String STANDARD_USER = "standard_user";
    public static final String PROBLEM_USER = "problem_user";
    public static final String PERFORMANCE_USER = "performance_glitch_user";
    public static final String ERROR_USER = "error_user";
    public static final String VISUAL_USER = "visual_user";

    // Locked-out user (triggers error on login)
    public static final String LOCKED_OUT_USER = "locked_out_user";

    // Invalid credentials (for negative tests)
    public static final String INVALID_USERNAME = "invalid_user";
    public static final String INVALID_PASSWORD = "wrong_password";

    // Expected page title after successful login
    public static final String INVENTORY_PAGE_TITLE = "Products";

    // Expected error messages
    public static final String ERROR_LOCKED_OUT = "Epic sadface: Sorry, this user has been locked out.";
    public static final String ERROR_INVALID_CREDENTIALS = "Epic sadface: Username and password do not match any user in this service";
    public static final String ERROR_USERNAME_REQUIRED = "Epic sadface: Username is required";
    public static final String ERROR_PASSWORD_REQUIRED = "Epic sadface: Password is required";
}
