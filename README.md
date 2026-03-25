# SauceDemo UI Automation Suite

A Selenium-based end-to-end UI test suite for [SauceDemo](https://www.saucedemo.com/), a demo e-commerce app by Sauce Labs. Built with Java 17, JUnit 5, and the Page Object Model pattern. Includes Docker support for zero-setup execution and Allure for rich HTML test reporting.

---

## Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 17 | Language |
| Maven | 3.9+ | Build and dependency management |
| Selenium WebDriver | 4.40.0 | Browser automation |
| JUnit 5 | 5.10.2 | Test framework |
| Allure | 2.32.0 | Test reporting |
| Docker | - | Containerized test execution |
| Google Chrome | Latest stable | Browser under test |

---

## Project Structure

    src/
    main/java/com/automation/ui/
        pages/                   Page Object classes
            LoginPage.java
            InventoryPage.java
            CartPage.java
            CheckoutInfoPage.java
            CheckoutOverviewPage.java
            CheckoutCompletePage.java
        utils/
            PageHelper.java      Reusable Selenium wrapper methods

    test/java/com/automation/ui/
        base/
            SetUp.java           WebDriver lifecycle + JUnit TestWatcher
        data/                    Test data constants
            LoginData.java
            ProductData.java
            CheckoutData.java
        tests/                   Test classes
            LoginTest.java
            InventoryTest.java
            CartTest.java
            CheckoutTest.java

---

## Test Coverage

### LoginTest
- Standard user can log in successfully
- Locked-out user sees the correct error message
- Invalid credentials show the correct error message
- Empty username field shows a validation error
- Empty password field shows a validation error

### InventoryTest
- Inventory page displays the correct title
- All 6 products are displayed on the page
- Adding a product updates the cart badge
- Adding multiple products updates the badge count correctly
- User can log out from the inventory page

### CartTest
- Cart page displays the correct title
- Cart contains the product that was added
- Removing a product empties the cart
- Proceeding to checkout navigates to the checkout info page

### CheckoutTest
- Checkout info page displays the correct title
- Missing first name shows a validation error
- Missing last name shows a validation error
- Missing postal code shows a validation error
- Valid customer info navigates to the overview page
- Overview page shows the ordered item
- Finishing the order navigates to the confirmation page with the correct header

---

## Running the Tests

### Prerequisites (local)
- Java 17+
- Maven 3.9+
- Google Chrome installed

### Run all tests (headed)

    mvn test

### Run all tests (headless)

    mvn test -Dheadless=true

### Generate the Allure report

    mvn allure:report

The report is generated at 	arget/site/allure-maven-plugin/index.html. Open it in any browser.

---

## Running with Docker

Docker handles Chrome installation and headless execution automatically. No local Java or Chrome setup needed.

### Build and run

    docker-compose up --build

### View the Allure report

Once the tests finish, the container serves the report at:

    http://localhost:8080

Open that URL in your browser to view the full interactive Allure dashboard. To stop the server, stop the container in Docker Desktop or press Ctrl+C in the terminal.

### What happens inside the container
1. Maven runs all tests in headless Chrome
2. Allure generates the HTML report from the test results
3. A Python HTTP server serves the report on port 8080

---

## Design Notes

**Page Object Model** - Each page of the application has a dedicated class that encapsulates its locators and interactions. Tests stay clean and readable, and UI changes only require updates in one place.

**PageHelper utility** - All Selenium interactions (click, type, getText, isDisplayed) go through PageHelper, which wraps each action with an explicit WebDriverWait. This prevents flaky NoSuchElementException and StaleElementReferenceException errors without scattering wait logic across the codebase.

**SetUp base class** - SetUp manages the full WebDriver lifecycle via JUnit 5 @BeforeEach / @AfterEach, ensuring a fresh browser instance per test. It also registers a TestWatcher that logs [PASS], [FAIL], and [SKIP] results to the console.

**Parameterized tests** - Negative and validation scenarios (invalid logins, incomplete checkout forms) use @ParameterizedTest with @MethodSource to keep test logic DRY while covering multiple input combinations in a single test method.

**Allure integration** - The allure-junit5 listener captures results automatically during the Maven Surefire run. No manual annotations are required for basic pass/fail reporting.
