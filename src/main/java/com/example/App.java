package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class App {
    public static void main(String[] args) {

        // Set ChromeDriver path (if not using WebDriverManager)
        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        WebDriver driver = new ChromeDriver();

        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Open website
            driver.get("https://automationexercise.com/login");
            driver.manage().window().maximize();

            // Wait for email field and enter email
            WebElement email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("email"))
            );
            email.sendKeys("testuser123@gmail.com");  // Use valid registered email

            // Enter password
            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("test123");  // Use correct password

            // Click login button
            WebElement loginBtn = driver.findElement(
                By.xpath("//button[@data-qa='login-button']")
            );
            loginBtn.click();

            // Validate login success (optional)
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Logged in as')]")
            ));

            System.out.println("Login successful!");

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            // Close browser
            driver.quit();
        }
    }
}
