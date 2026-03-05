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
        //seArguments("--headless"); // Run in headless mode for Jenkins
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
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
    public void verifySspPage2() {
        driver.get("https://www.ssp.usps.gov");
        String expectedTitle = "LiteBlue | Self-Service Profile (SSP)";
        String actualTitle = driver.getPageSource();
        assertEquals(expectedTitle, actualTitle, "LiteBlue | Self-Service Profile (SSP)");
    }



    //    @Test
//    void runGmailLogin() {
//        GmailLogin.loginToGmail(driver, wait, "...", "...");
//    }
    @Test
    void gmailLoginTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        GmailLogin.loginToGmail(driver, wait, "processinfonow@gmail.com", "##GallaFesame@@2008");
    }

    @Test void displayGoogleChart() {
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

    @Test void displaySspHrLogin() {
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
