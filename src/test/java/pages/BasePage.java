package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;

    void click(By locator) {
        waitForElement(locator);
        findBy(locator).click();
    }
    void sendKeys(By locator, String text) {
        waitForElement(locator);
        findBy(locator).sendKeys(text);
    }
    WebElement findBy(By locator) {
        return driver.findElement(locator);
    }
    public String getInnerText(By locator) { return findBy(locator).getText();}

    private void waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
