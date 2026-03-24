package com.automation.ui.pages;

import com.automation.ui.utils.PageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for the SauceDemo Login page.
 * URL: https://www.saucedemo.com/
 */
public class LoginPage {

    private final WebDriver driver;
    private final PageHelper helper;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.helper = new PageHelper(driver);
        PageFactory.initElements(driver, this);
    }

    /** Navigates to the SauceDemo login page. */
    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    /** Enters credentials and clicks the login button. */
    public void login(String username, String password) {
        helper.type(usernameField, username);
        helper.type(passwordField, password);
        helper.click(loginButton);
    }

    /** Attempts login expecting it to fail (e.g. for negative tests). */
    public void loginExpectingFailure(String username, String password) {
        helper.type(usernameField, username);
        helper.type(passwordField, password);
        helper.click(loginButton);
    }

    /** Returns the error message text shown after a failed login attempt. */
    public String getErrorMessage() {
        return helper.getText(errorMessage);
    }

    /** Returns true if the error message container is visible. */
    public boolean isErrorDisplayed() {
        return helper.isDisplayed(errorMessage);
    }
}
