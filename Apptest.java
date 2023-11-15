package testcase;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Integration UI test for PHP App.
 */
public class Apptest {
    WebDriver driver;
    WebDriverWait wait;
    String url = "http://localhost";
    String validPassword = "password1234";
    String passwordLength = "pass";
    String invalidPassword = "123456789";

    @Before
    public void setUp() {
        driver = new HtmlUnitDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testValidPassword()
            throws InterruptedException {

        // get web page
        driver.get(url);
        // wait until page is loaded or timeout error
        wait.until(ExpectedConditions.titleContains("Home Page"));

        // enter input
        driver.findElement(By.name("password")).sendKeys(validPassword);
        // click submit
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        // check result
        String expectedResult = "Welcome Page";
        boolean isResultCorrect = wait.until(ExpectedConditions.titleContains(expectedResult));
        assertTrue(isResultCorrect);
    }

    @Test
    public void testPasswordLength()
            throws InterruptedException {

        // get web page
        driver.get(url);
        // wait until page is loaded or timeout error
        wait.until(ExpectedConditions.titleContains("Home Page"));

        // enter input
        driver.findElement(By.name("password")).sendKeys(passwordLength);
        // click submit
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        // check result
        By errorMsgId = By.xpath("//div[@id='error-message']");
        String expectedResult = "Blocked: Password does not meet security requirements.";
        boolean isResultCorrect = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsgId)).getText().contains(expectedResult);
        assertTrue(isResultCorrect);
    }

    @Test
    public void testInvalidPassword()
            throws InterruptedException {

        // get web page
        driver.get(url);
        // wait until page is loaded or timeout error
        wait.until(ExpectedConditions.titleContains("Home Page"));

        // enter input
        driver.findElement(By.name("password")).sendKeys(invalidPassword);
        // click submit
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        // check result
        By errorMsgId = By.xpath("//div[@id='error-message']");
        String expectedResult = "Blocked: Password does not meet security requirements.";
        boolean isResultCorrect = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsgId)).getText().contains(expectedResult);
        assertTrue(isResultCorrect);
    }
}
