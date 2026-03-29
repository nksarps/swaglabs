package com.automation.ui.data;

/**
 * Test data constants for inventory/product interactions.
 * Add-to-cart and remove button data-test attribute values
 * match the SauceDemo DOM selectors used in InventoryPage and CartPage.
 */
public class ProductData {

    private ProductData() {}

    // data-test attribute values for "Add to cart" buttons
    public static final String ADD_BACKPACK          = "add-to-cart-sauce-labs-backpack";
    public static final String ADD_BIKE_LIGHT        = "add-to-cart-sauce-labs-bike-light";

    // data-test attribute values for "Remove" buttons (used in CartPage)
    public static final String REMOVE_BACKPACK       = "remove-sauce-labs-backpack";

    // Expected product count on the inventory page
    public static final int TOTAL_PRODUCT_COUNT = 6;

    // Expected page title
    public static final String PAGE_TITLE = "Products";
}
