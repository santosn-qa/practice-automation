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

public class T04SortableTableTest {

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
    public void testTableSortByRank() {
        // Click on the Rank header to sort by rank ascending
        WebElement rankHeader = driver.findElement(By.xpath("//th[@aria-label='Rank: activate to sort column ascending']"));
        rankHeader.click();

        // Validate the first row after sorting
        WebElement firstRow = driver.findElement(By.xpath("//table[@id='tablepress-1']/tbody/tr[1]"));
        validateRowData(firstRow, "1", "China", "1,439.3");
    }

    @Test
    public void testTableSortByCountry() {
        // Click on the Country header to sort by country ascending
        WebElement countryHeader = driver.findElement(By.xpath("//th[@aria-label='Country: activate to sort column ascending']"));
        countryHeader.click();

        // Validate the first row after sorting
        WebElement firstRow = driver.findElement(By.xpath("//table[@id='tablepress-1']/tbody/tr[1]"));
        validateRowData(firstRow, "8", "Bangladesh", "164.7");
    }

    @Test
    public void testTableSortByPopulation() {
        // Click on the Poipulation header to sort by country ascending
        WebElement populationHeader = driver.findElement(By.xpath("//th[@aria-label='Population (million): activate to sort column ascending']"));
        populationHeader.click();

        // Validate the first row after sorting
        WebElement firstRow = driver.findElement(By.xpath("//table[@id='tablepress-1']/tbody/tr[1]"));
        validateRowData(firstRow, "25", "South Africa", "59.3");
    }

    @Test
    public void testTableSearch() {
        // Enter "India" in the search input
        WebElement searchInput = driver.findElement(By.xpath("//input[@type='search']"));
        searchInput.sendKeys("India");

        // Validate the first row after searching
        WebElement firstRow = driver.findElement(By.xpath("//table[@id='tablepress-1']/tbody/tr[1]"));
        validateRowData(firstRow, "2", "India", "1,380");
    }

    @Test
    public void testTableEntriesSelection() {
        // Select "25" entries
        WebElement entriesSelect = driver.findElement(By.name("tablepress-1_length"));
        entriesSelect.sendKeys("25");

        // Validate the number of rows after changing entries
        WebElement tbody = driver.findElement(By.xpath("//tbody[@class='row-hover']"));
        assertEquals(25, tbody.findElements(By.tagName("tr")).size());
    }

    private void validateRowData(WebElement row, String expectedRank, String expectedCountry, String expectedPopulation) {
        try {
            assertEquals(expectedRank, row.findElements(By.tagName("td")).get(0).getText());
            assertEquals(expectedCountry, row.findElements(By.tagName("td")).get(1).getText());
            assertEquals(expectedPopulation, row.findElements(By.tagName("td")).get(2).getText());
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
