package org.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class T02DelaysTest {

        private WebDriver driver;

        @BeforeEach
        public void setup() {
            // Set up WebDriver (ChromeDriver)
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

            // Navigate to the home page
            driver.get("https://practice-automation.com/");
        }

        @Test
        public void testCountdownTimer() {
            // Navigate to the JavaScript Delays page
            WebElement javaScriptDelaysLink = driver.findElement(By.linkText("JavaScript Delays"));
            javaScriptDelaysLink.click();

            // Locate the countdown timer element
            WebElement countdownTimerElement = driver.findElement(By.id("delay"));

            // Retrieve the initial countdown value
            String initialCountdownValue = countdownTimerElement.getText();

            // Start the countdown (click the "Start" button)
            WebElement startButton = driver.findElement(By.id("start"));
            startButton.click();

            // Check the countdown value every second until completion
            long startTime = System.currentTimeMillis();
            long elapsedTime;
            String currentCountdownValue;
            do {
                currentCountdownValue = countdownTimerElement.getAttribute("value");
                System.out.println("Countdown: " + currentCountdownValue);
                elapsedTime = System.currentTimeMillis() - startTime;
                try {
                    Thread.sleep(1000); // Wait for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (elapsedTime < 10000); // 10 seconds

            // Assert the expected countdown behavior
            assertNotEquals(initialCountdownValue, currentCountdownValue);
            assertEquals("Liftoff!", currentCountdownValue);
        }

        @AfterEach
        public void tearDown() {
            // End the session
            driver.quit();
        }
    }