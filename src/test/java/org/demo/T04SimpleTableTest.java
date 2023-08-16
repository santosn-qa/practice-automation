package org.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class T04SimpleTableTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        // Set up WebDriver (ChromeDriver)
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Navigate to the home page
        driver.get("https://practice-automation.com/");

        // Navigate to the Tables page
        WebElement tablesLink = driver.findElement(By.linkText("Tables"));
        tablesLink.click();
    }

    @Test
    public void testSimpleTableValidation() {
        // Find the table element by CSS selector
        WebElement table = driver.findElement(By.cssSelector("figure.wp-block-table > table"));

        // Validate headers
        WebElement headerRow = table.findElement(By.tagName("tr"));
        assertEquals("Item", headerRow.findElements(By.tagName("td")).get(0).getText());
        assertEquals("Price", headerRow.findElements(By.tagName("td")).get(1).getText());

        // Validate data rows
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        assertEquals(4, tableBody.findElements(By.tagName("tr")).size());

        // Validate data in the rows
        validateRowData(tableBody.findElement(By.xpath("//tr[2]")), "Oranges", "$3.99");
        validateRowData(tableBody.findElement(By.xpath("//tr[3]")), "Laptop", "$1200.00");
        validateRowData(tableBody.findElement(By.xpath("//tr[4]")), "Marbles", "$1.25");
    }

    private void validateRowData(WebElement row, String expectedItem, String expectedPrice) {
        try {
            assertEquals(expectedItem, row.findElements(By.tagName("td")).get(0).getText());
            assertEquals(expectedPrice, row.findElements(By.tagName("td")).get(1).getText());
        } catch (Exception e) {
            fail("An exception occurred while validating row data: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        // End the session
        driver.quit();
    }
}
