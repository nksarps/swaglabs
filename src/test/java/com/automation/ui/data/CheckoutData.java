package com.automation.ui.data;

/**
 * Test data constants for the Checkout information form.
 */
public class CheckoutData {

    private CheckoutData() {}

    // Valid customer info
    public static final String FIRST_NAME   = "John";
    public static final String LAST_NAME    = "Doe";
    public static final String POSTAL_CODE  = "12345";

    // Expected page titles across the checkout flow
    public static final String CART_PAGE_TITLE      = "Your Cart";
    public static final String INFO_PAGE_TITLE      = "Checkout: Your Information";
    public static final String OVERVIEW_PAGE_TITLE  = "Checkout: Overview";
    public static final String COMPLETE_PAGE_TITLE  = "Checkout: Complete!";

    // Expected confirmation texts on the complete page
    public static final String CONFIRMATION_HEADER  = "Thank you for your order!";

    // Expected validation error messages on the info form
    public static final String ERROR_FIRST_NAME_REQUIRED  = "Error: First Name is required";
    public static final String ERROR_LAST_NAME_REQUIRED   = "Error: Last Name is required";
    public static final String ERROR_POSTAL_CODE_REQUIRED = "Error: Postal Code is required";
}
