package org.pin;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        
        // Detect if running in Jenkins or if headless flag is passed
        boolean isHeadless = System.getenv("JENKINS_HOME") != null || 
                             "true".equals(System.getProperty("headless"));

        if (isHeadless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");
        }

        // Selenium 4.41.0 automatically manages the driver executable
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void verifyPageTitle() {
        driver.get("https://www.google.com");
        assertEquals("Google", driver.getTitle(), "Title should be Google");
    }

    @Test
    public void verifyPageSource() {
        driver.get("https://www.google.com");
        // Fixed: contains check is safer than exact match for page source
        assertTrue(driver.getPageSource().contains("Google"), "Source should contain Google");
    }

    @Test
    public void verifySpPage2() {
        driver.get("https://www.yahoo.com");
        // Fixed: Use getTitle() for title checks, not getPageSource()
        assertTrue(driver.getTitle().toLowerCase().contains("yahoo"), "Title should contain yahoo");
    }

    @Test
    void gmailLoginTest() {
        // Removed 'new ChromeDriver()' to use the global headless instance
        GmailLogin.loginToGmail(driver, wait, "processgmail.com", "2008");
    }

    @Test 
    void displayGoogleChart() {
        String path = Paths.get("src/test/resources/chart.html").toAbsolutePath().toUri().toString();
        driver.get(path);
        WebElement chart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("chart_div")));
        assertTrue(chart.isDisplayed(), "Chart should be visible");
    }

    @Test 
    void displaySppLogin1() {
        String path = Paths.get("src/test/resources/chart.html").toAbsolutePath().toUri().toString();
        driver.get(path);
        WebElement chart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("chart_div")));
        assertTrue(chart.isDisplayed());
    }

    @Test 
    void displaySpHrLogin() {
        String path = Paths.get("src/test/resources/chart.html").toAbsolutePath().toUri().toString();
        driver.get(path);
        WebElement chart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("chart_div")));
        assertTrue(chart.isDisplayed());
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
