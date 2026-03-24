package com.automation.ui.pages;

import com.automation.ui.utils.PageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Page Object for Checkout Step Two — order summary / overview.
 * URL: https://www.saucedemo.com/checkout-step-two.html
 */
public class CheckoutOverviewPage {
    private final WebDriver driver;
    private final PageHelper helper;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> orderItems;

    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    @FindBy(css = "[data-test='finish']")
    private WebElement finishButton;

    @FindBy(css = "[data-test='cancel']")
    private WebElement cancelButton;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.helper = new PageHelper(driver);
        PageFactory.initElements(driver, this);
    }

    /** Returns the page title text (e.g. "Checkout: Overview"). */
    public String getPageTitle() {
        return helper.getText(pageTitle);
    }

    /** Returns all order item elements in the summary. */
    public List<WebElement> getOrderItems() {
        return orderItems;
    }

    /** Returns the subtotal label text (e.g. "Item total: $29.99"). */
    public String getSubtotal() {
        return helper.getText(subtotalLabel);
    }

    /** Returns the tax label text (e.g. "Tax: $2.40"). */
    public String getTax() {
        return helper.getText(taxLabel);
    }

    /** Returns the total label text (e.g. "Total: $32.39"). */
    public String getTotal() {
        return helper.getText(totalLabel);
    }

    /** Confirms the order and navigates to the confirmation page. */
    public void finishOrder() {
        helper.click(finishButton);
    }

    /** Cancels and returns to the inventory page. */
    public void cancelOrder() {
        helper.click(cancelButton);
    }
}
