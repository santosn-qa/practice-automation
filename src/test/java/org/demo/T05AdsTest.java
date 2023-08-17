package org.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class T05AdsTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement adElement;

    @BeforeEach
    public void setup() {
        // Set up WebDriver (ChromeDriver)
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Navigate to the home page
        driver.get("https://practice-automation.com/");

        // Navigate to the Ads page
        WebElement adsLink = driver.findElement(By.linkText("Ads"));
        adsLink.click();

        // Wait for the ad to appear
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        handlingAds();
    }

    private void handlingAds() {
        // Explicitly wait for the ad element to appear
        try {
            Instant startTime = Instant.now();
            adElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pum-active")));

            Instant endTime = Instant.now();
            Duration duration = Duration.between(startTime, endTime);

            long secondsWaited = duration.getSeconds();

            System.out.println("Element appeared after " + secondsWaited + " seconds.");
        } catch (Exception e) {
            fail("An exception occurred while validating ads: " + e.getMessage());
        }
    }

    private void closingAds() {
        try {
            // Close the ad by clicking on a close button
            WebElement closeButton = adElement.findElement(By.cssSelector(".pum-close"));
            closeButton.click();

            // Explicitly wait for the ad element to disappear
            Instant startTime = Instant.now();
            wait.until(ExpectedConditions.invisibilityOf(adElement));

            Instant endTime = Instant.now();
            Duration duration = Duration.between(startTime, endTime);

            long secondsWaited = duration.getSeconds();

            System.out.println("Element disappeared after " + secondsWaited + " seconds.");
        } catch (Exception e) {
            fail("An exception occurred while validating ads: " + e.getMessage());
        }
    }

    @Test
    public void testAdVisibility() {
        // Assert that the ad is visible after 5 seconds
        assertTrue(adElement.isDisplayed());
    }

    @Test
    public void testCloseAd() {
        // Close the ad
        closingAds();

        // Assert that the ad is no longer visible after close
        assertFalse(adElement.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        // End the session
        driver.quit();
    }
}
