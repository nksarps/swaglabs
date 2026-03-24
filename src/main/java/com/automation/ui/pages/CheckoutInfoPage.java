package com.automation.ui.pages;

import com.automation.ui.utils.PageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for Checkout Step One — customer information entry.
 * URL: https://www.saucedemo.com/checkout-step-one.html
 */
public class CheckoutInfoPage {

    private final WebDriver driver;
    private final PageHelper helper;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(css = "[data-test='continue']")
    private WebElement continueButton;

    @FindBy(css = "[data-test='cancel']")
    private WebElement cancelButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public CheckoutInfoPage(WebDriver driver) {
        this.driver = driver;
        this.helper = new PageHelper(driver);
        PageFactory.initElements(driver, this);
    }

    /** Returns the page title text (e.g. "Checkout: Your Information"). */
    public String getPageTitle() {
        return helper.getText(pageTitle);
    }

    /** Fills in the customer information form and continues to the overview step. */
    public void enterCustomerInfo(String firstName, String lastName, String postalCode) {
        helper.type(firstNameField, firstName);
        helper.type(lastNameField, lastName);
        helper.type(postalCodeField, postalCode);
        helper.click(continueButton);
    }

    /** Submits the form without filling it in (useful for validation testing). */
    public void submitWithoutInfo() {
        helper.click(continueButton);
    }

    /** Returns the validation error message text. */
    public String getErrorMessage() {
        return helper.getText(errorMessage);
    }

    /** Returns true if the error message is visible. */
    public boolean isErrorDisplayed() {
        return helper.isDisplayed(errorMessage);
    }

    /** Cancels checkout and returns to the cart. */
    public void cancelCheckout() {
        helper.click(cancelButton);
    }
}
