package org.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class T03SlidersTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        // Set up WebDriver (ChromeDriver)
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Navigate to the home page
        driver.get("https://practice-automation.com/");
    }

    // Method to slide the range input element using JavaScript executor
    private void slideUsingJS(WebElement element, int offset) {
        // Execute JavaScript to slide the element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                // Set the value of the slider element
                "arguments[0].value = arguments[1];" +
                // Dispatch an 'input' event on the slider element to simulate user interaction
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                element, offset);
        // Wait for the slider to slide
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeToBe(element, "value", String.valueOf(offset)));
    }

    @Test
    public void testMoveSliderRightUsingJS() {
        // Navigate to the Sliders page
        WebElement slidersLink = driver.findElement(By.linkText("Sliders"));
        slidersLink.click();

        // Locate the slider element
        WebElement slider = driver.findElement(By.id("slideMe"));

        // Retrieve the initial slider value
        String sliderValue = slider.getAttribute("value");
        System.out.println("Slider: " + sliderValue);

        // Locate the slider value text element
        WebElement sliderText= driver.findElement(By.id("value"));

        // Retrieve the current slider value
        String initialSliderText = sliderText.getText();
        System.out.println("Slider Text: " + initialSliderText);

        // Determine the desired value for sliding
        int offset = Integer.parseInt(sliderValue) + 10;

        // Slide the slider
        slideUsingJS(slider, offset);

        // Check the slider value after sliding
        String currentSliderValue = slider.getAttribute("value");
        System.out.println("Slider: " + currentSliderValue);

        // Retrieve the current slider value
        String currentSliderText = sliderText.getText();
        System.out.println("Slider Text: " + currentSliderText);

        assertEquals(currentSliderValue, currentSliderText);
    }

    @Test
    public void testMoveSliderLeftUsingJS() {
        // Navigate to the Sliders page
        WebElement slidersLink = driver.findElement(By.linkText("Sliders"));
        slidersLink.click();

        // Locate the slider element
        WebElement slider = driver.findElement(By.id("slideMe"));

        // Retrieve the initial slider value
        String sliderValue = slider.getAttribute("value");
        System.out.println("Slider: " + sliderValue);

        // Locate the slider value text element
        WebElement sliderText= driver.findElement(By.id("value"));

        // Retrieve the current slider value
        String initialSliderText = sliderText.getText();
        System.out.println("Slider Text: " + initialSliderText);

        // Determine the desired value for sliding
        int offset = Integer.parseInt(sliderValue) - 10;

        // Slide the slider
        slideUsingJS(slider, offset);

        // Check the slider value after sliding
        String currentSliderValue = slider.getAttribute("value");
        System.out.println("Slider: " + currentSliderValue);

        // Retrieve the current slider value
        String currentSliderText = sliderText.getText();
        System.out.println("Slider Text: " + currentSliderText);

        assertEquals(currentSliderValue, currentSliderText);
    }

    @AfterEach
    public void tearDown() {
        // End the session
        driver.quit();
    }
}
