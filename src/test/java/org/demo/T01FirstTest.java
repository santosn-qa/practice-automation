package org.demo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class T01FirstTest {

    @Test
    public void testLandingPage() {
        // WebDriverManager is an open-source Java library that carries out the management of the drivers required by Selenium WebDriver
        WebDriverManager.chromedriver().setup();

        // Start driver session using ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to landing page
        driver.get("https://practice-automation.com/");

        // Request browser information - page title
        String title = driver.getTitle();

        // Validate if title is as expected
        assert title.contains("Home");

        // End the session
        driver.quit();
    }
}
