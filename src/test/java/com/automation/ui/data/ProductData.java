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
    public static final String ADD_BOLT_TSHIRT       = "add-to-cart-sauce-labs-bolt-t-shirt";
    public static final String ADD_FLEECE_JACKET     = "add-to-cart-sauce-labs-fleece-jacket";
    public static final String ADD_ONESIE            = "add-to-cart-sauce-labs-onesie";
    public static final String ADD_RED_TSHIRT        = "add-to-cart-test.allthethings()-t-shirt-(red)";

    // data-test attribute values for "Remove" buttons (used in CartPage)
    public static final String REMOVE_BACKPACK       = "remove-sauce-labs-backpack";
    public static final String REMOVE_BIKE_LIGHT     = "remove-sauce-labs-bike-light";
    public static final String REMOVE_BOLT_TSHIRT    = "remove-sauce-labs-bolt-t-shirt";
    public static final String REMOVE_FLEECE_JACKET  = "remove-sauce-labs-fleece-jacket";
    public static final String REMOVE_ONESIE         = "remove-sauce-labs-onesie";
    public static final String REMOVE_RED_TSHIRT     = "remove-test.allthethings()-t-shirt-(red)";

    // Expected product count on the inventory page
    public static final int TOTAL_PRODUCT_COUNT = 6;

    // Expected page title
    public static final String PAGE_TITLE = "Products";
}
