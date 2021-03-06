package test.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.base.Base;

public class ElementActions extends Base {

    @Tag("signin()")
    @DisplayName("signin()")
    @Test
    public void signIn() {
        driver.get("https://www.saucedemo.com");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Wait for page to render by locating the login element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login_button_container")));

        // Take actions on the login element
        driver.findElement(By.className("login-button")).click();

    }

}