package com.automation.ui.pages;

import com.automation.ui.utils.PageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Page Object for Checkout Step Two — order summary / overview.
 */
public class CheckoutOverviewPage {
    private final WebDriver driver;
    private final PageHelper helper;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> orderItems;

    @FindBy(css = "[data-test='finish']")
    private WebElement finishButton;

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

    /** Confirms the order and navigates to the confirmation page. */
    public void finishOrder() {
        helper.click(finishButton);
    }
}
