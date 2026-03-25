package com.automation.ui.pages;

import com.automation.ui.utils.PageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Page Object for the SauceDemo Inventory (Products) page.
 * URL: https://www.saucedemo.com/inventory.html
 */
public class InventoryPage {
    private final WebDriver driver;
    private final PageHelper helper;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = "[data-test='product-sort-container']")
    private WebElement sortDropdown;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.helper = new PageHelper(driver);
        PageFactory.initElements(driver, this);
    }

    /** Returns the page title text (e.g. "Products"). */
    public String getPageTitle() {
        return helper.getText(pageTitle);
    }

    /** Returns all inventory item elements on the page. */
    public List<WebElement> getAllProducts() {
        return inventoryItems;
    }

    /** Returns the number of products displayed. */
    public int getProductCount() {
        return inventoryItems.size();
    }

    /**
     * Adds a product to the cart by its data-test add button attribute value.
     * Example: addProductToCart("add-to-cart-sauce-labs-backpack")
     */
    public void addProductToCart(String addButtonDataTest) {
        WebElement addButton = driver.findElement(
                org.openqa.selenium.By.cssSelector("[data-test='" + addButtonDataTest + "']"));
        helper.click(addButton);
    }

    /** Returns the cart badge count as a string (e.g. "1"). */
    public String getCartBadgeCount() {
        return helper.getText(cartBadge);
    }

    /** Returns true if the cart badge is visible. */
    public boolean isCartBadgeDisplayed() {
        return helper.isDisplayed(cartBadge);
    }

    /** Navigates to the cart page. */
    public void goToCart() {
        helper.click(cartIcon);
    }

    /** Opens the burger menu and logs out. */
    public void logout() {
        helper.click(burgerMenuButton);
        helper.click(logoutLink);
    }
}
