package com.automation.ui.pages;

import com.automation.ui.utils.PageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for the Checkout Complete (confirmation) page.
 * URL: https://www.saucedemo.com/checkout-complete.html
 */
public class CheckoutCompletePage {
    private final WebDriver driver;
    private final PageHelper helper;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "complete-header")
    private WebElement confirmationHeader;

    @FindBy(className = "complete-text")
    private WebElement confirmationText;

    @FindBy(css = "[data-test='back-to-products']")
    private WebElement backToProductsButton;

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.helper = new PageHelper(driver);
        PageFactory.initElements(driver, this);
    }

    /** Returns the page title text (e.g. "Checkout: Complete!"). */
    public String getPageTitle() {
        return helper.getText(pageTitle);
    }

    /** Returns the confirmation header text (e.g. "Thank you for your order!"). */
    public String getConfirmationHeader() {
        return helper.getText(confirmationHeader);
    }

    /** Returns the confirmation body text. */
    public String getConfirmationText() {
        return helper.getText(confirmationText);
    }

    /** Returns true if the confirmation header is displayed. */
    public boolean isOrderConfirmed() {
        return helper.isDisplayed(confirmationHeader);
    }

    /** Navigates back to the products/inventory page. */
    public void backToProducts() {
        helper.click(backToProductsButton);
    }
}
