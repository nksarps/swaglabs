package com.automation.ui.base;

import com.automation.ui.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Manages WebDriver lifecycle for test execution.
 */
@ExtendWith(SetUp.JulTestWatcher.class)
public class SetUp {

    protected WebDriver driver;

    static {
        /*
         * Configures Java Util Logging (JUL) to display only the log message without
         * additional metadata. Also suppresses Selenium DevTools CDP version warnings.
         */
        try (InputStream configFile = SetUp.class.getClassLoader().getResourceAsStream("logging.properties")) {
            if (configFile != null) {
                LogManager.getLogManager().readConfiguration(configFile);
            }
        } catch (Exception e) {
            System.err.println("Could not load logging.properties: " + e.getMessage());
        }

        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%n");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.OFF);
    }

    private static final Logger LOGGER = Logger.getLogger(SetUp.class.getName());
    private static final String APPLICATION_URL = "https://www.saucedemo.com/";

    /**
     * Custom JUnit 5 {@link TestWatcher} that logs test results.
     */
    static class JulTestWatcher implements TestWatcher {

        @Override
        public void testSuccessful(ExtensionContext context) {
            LOGGER.info("[PASS] " + context.getDisplayName());
        }

        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            LOGGER.info("[FAIL] " + context.getDisplayName() + " - " + cause.getMessage());
        }

        @Override
        public void testDisabled(ExtensionContext context, java.util.Optional<String> reason) {
            LOGGER.info("[SKIP] " + context.getDisplayName() + reason.map(r -> " - " + r).orElse(""));
        }
    }

    // Page objects available to all test classes
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected CartPage cartPage;
    protected CheckoutInfoPage checkoutInfoPage;
    protected CheckoutOverviewPage checkoutOverviewPage;
    protected CheckoutCompletePage checkoutCompletePage;

    /**
     * Initializes the WebDriver and navigates to the application URL.
     * Set APPLICATION_URL as an environment variable or system property.
     * Pass -Dheadless=true to run in headless mode.
     */
    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        String headless = System.getProperty("headless");
        if ("true".equalsIgnoreCase(headless)) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        driver = new ChromeDriver(options);
        driver.get(APPLICATION_URL);

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutInfoPage = new CheckoutInfoPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
    }
    /** Quits the WebDriver instance after each test. */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
