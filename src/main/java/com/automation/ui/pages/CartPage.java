package com.automation.ui.pages;

import com.automation.ui.utils.PageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Page Object for the SauceDemo Cart page.
 * URL: https://www.saucedemo.com/cart.html
 */
public class CartPage {

    private final WebDriver driver;
    private final PageHelper helper;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = "[data-test='checkout']")
    private WebElement checkoutButton;

    @FindBy(css = "[data-test='continue-shopping']")
    private WebElement continueShoppingButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.helper = new PageHelper(driver);
        PageFactory.initElements(driver, this);
    }

    /** Returns the page title text (e.g. "Your Cart"). */
    public String getPageTitle() {
        return helper.getText(pageTitle);
    }

    /** Returns all cart item elements. */
    public List<WebElement> getCartItems() {
        return cartItems;
    }

    /** Returns the number of items currently in the cart. */
    public int getCartItemCount() {
        return cartItems.size();
    }

    /**
     * Removes a cart item by its data-test remove button attribute value.
     * Example: removeProduct("remove-sauce-labs-backpack")
     */
    public void removeProduct(String removeButtonDataTest) {
        WebElement removeButton = driver.findElement(
                org.openqa.selenium.By.cssSelector("[data-test='" + removeButtonDataTest + "']"));
        helper.click(removeButton);
    }

    /** Proceeds to the checkout information step. */
    public void proceedToCheckout() {
        helper.click(checkoutButton);
    }

    /** Navigates back to the inventory page. */
    public void continueShopping() {
        helper.click(continueShoppingButton);
    }
}
