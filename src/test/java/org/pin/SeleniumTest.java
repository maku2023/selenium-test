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


public class SeleniumTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        // Set the path to the ChromeDriver executable
        // NOTE: This path must be correct on the Jenkins build agent
        //System.setProperty("
        //
        //
        // webdriver.chrome.driver", "/usr/local/bin/chromedriver"); // Example path for Linux
       ChromeOptions options = new ChromeOptions();
options.addArguments("--headless=new"); // Use the updated headless mode
options.addArguments("--window-size=1920,1080"); // Set a consistent resolution
options.addArguments("--no-sandbox"); // Fixes "DevToolsActivePort" errors
options.addArguments("--disable-gpu"); // Recommended for CI stability
driver = new ChromeDriver(options);
    }

    @Test
    public void verifyPageTitle() {
        driver.get("https://www.google.com");
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle, "The page title should be 'Google'");
    }


    @Test
    public void verifyPageSource() {
        driver.get("https://www.google.com");
        String expectedTitle = "Google";
        String actualTitle = driver.getPageSource();
        assertEquals(expectedTitle, actualTitle, "The page title should be 'Google'");
    }

    @Test
    public void verifySpPage2() {
        driver.get("https://www.yahoo.com");
        String expectedTitle = "yahoo";
        String actualTitle = driver.getPageSource();
        assertEquals(expectedTitle, actualTitle, "yahoo");
    }



    //    @Test
//    void runGmailLogin() {
//        GmailLogin.loginToGmail(driver, wait, "...", "...");
//    }
    @Test
    void gmailLoginTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        GmailLogin.loginToGmail(driver, wait, "processgmail.com", "2008");
    }

    @Test void displayGoogleChart() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            String path = Paths.get("src/test/resources/chart.html").toAbsolutePath().toUri().toString();
            driver.get(path);
            WebElement chart = wait.until( ExpectedConditions.visibilityOfElementLocated(By.id("chart_div")) );
        assert chart.isDisplayed();
        }
        finally {
            driver.quit();
        }
    }

    @Test void displaySppLogin1() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            String path = Paths.get("src/test/resources/chart.html").toAbsolutePath().toUri().toString();
            driver.get(path);
            WebElement chart = wait.until( ExpectedConditions.visibilityOfElementLocated(By.id("chart_div")) );
            assert chart.isDisplayed();
        }
        finally {
            driver.quit();
        }
    }

    @Test void displaySpHrLogin() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            String path = Paths.get("src/test/resources/chart.html").toAbsolutePath().toUri().toString();
            driver.get(path);
            WebElement chart = wait.until( ExpectedConditions.visibilityOfElementLocated(By.id("chart_div")) );
            assert chart.isDisplayed();
        }
        finally {
            driver.quit();
        }
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
