package test.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import test.base.Base;

public class ElementActionsClass extends Base {

    @Tag("clickAction()")
    @DisplayName("clickAction()")
    @Test
    public void clickAction() {
        driver.get("https://www.saucedemo.com");

        // Actions Class
        Actions action = new Actions(driver);

        WebElement element = driver.findElement(By.className("login-button"));

        action.click(element).build().perform();
    }

    @Tag("actionExamples()")
    @DisplayName("actionExamples()")
    @Test
    public void actionExamples() {
        driver.get("https://www.saucedemo.com");

        // Actions Examples
        Actions action = new Actions(driver);

        WebElement element = driver.findElement(By.className("login-button"));
        WebElement element2 = driver.findElement(By.cssSelector("[data-test='username']"));

        // Hover
        action.moveToElement(element).build().perform();

        // Drag & Drop
        action.dragAndDrop(element, element2);

        // click - pause - release
        action.clickAndHold(element).pause(100).release().build().perform();
    }
}