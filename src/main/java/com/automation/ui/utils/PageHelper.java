package com.automation.ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * PageHelper provides reusable wrapper methods for common Selenium interactions.
 * All methods include explicit waits and basic error handling.
 */
public class PageHelper {

    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    private final WebDriverWait wait;

    public PageHelper(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
    }

    /**
     * Waits for an element to be clickable, then clicks it.
     */
    public void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Waits for an element to be visible, clears it, then types the given text.
     */
    public void type(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Waits for an element to be visible, then returns its trimmed text content.
     */
    public String getText(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.getText().trim();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Returns true if the element is displayed on the page, false otherwise.
     */
    public boolean isDisplayed(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Waits for an element to be visible and returns it.
     */
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
