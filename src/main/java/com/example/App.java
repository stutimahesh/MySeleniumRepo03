package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class App {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();

        // Required for Jenkins (headless Linux)
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://automationexercise.com");

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Wait until "Add to cart" is clickable
            WebElement addToCart = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("(//a[contains(text(),'Add to cart')])[1]")
                )
            );

            addToCart.click();

            // Wait for Continue Shopping button
            WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Continue Shopping')]")
                )
            );

            continueBtn.click();

            System.out.println("First item added to cart successfully");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
