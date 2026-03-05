package org.pin;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

class GoogleChartTest {

    @Test
    void displayGoogleChart() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            String path = Paths.get("src/test/resources/chart.html")
                    .toAbsolutePath()
                    .toUri()
                    .toString();

            driver.get(path);

            WebElement chart = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("chart_div"))
            );

            assert chart.isDisplayed();

        } finally {
            driver.quit();
        }
    }
}
