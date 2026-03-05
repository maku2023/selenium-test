package org.pin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GmailLogin {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            loginToGmail(driver, wait, "velocities2009@gmail.com", "yourPasswordHere");
            System.out.println("Login attempt completed.");

        } finally {
            // driver.quit(); // keep open for debugging
        }
    }

    public static void loginToGmail(WebDriver driver, WebDriverWait wait,
                                    String emailAddress, String passwordValue) {

        driver.get("https://accounts.google.com/signin");

        // Email field
        WebElement email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("identifierId"))
        );
        email.sendKeys(emailAddress);

        driver.findElement(By.id("identifierNext")).click();

        // Password field
        WebElement password = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("password"))
        );
        password.sendKeys(passwordValue);

        driver.findElement(By.id("passwordNext")).click();

        // Optional: wait for inbox load
        wait.until(ExpectedConditions.urlContains("mail.google.com"));
    }
}
