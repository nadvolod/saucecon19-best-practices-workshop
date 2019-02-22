package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class LogInPage extends BasePage {
    private By userField = By.cssSelector("[placeholder = 'Username']");
    private By passwordField = By.cssSelector("[placeholder = 'Password']");
    private By loginButton = By.cssSelector("[value = 'LOGIN']");

    public static LogInPage visit(WebDriver driver) {
        LogInPage page = new LogInPage(driver);
        driver.get("https://www.saucedemo.com");
        return page;
    }

    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void signInSuccessfully() {
        logIn();
    }

    private void logIn() {
        String username = "standard_user";
        String password = "secret_sauce";
        sendKeys(userField, username);
        sendKeys(passwordField, password);
        click(loginButton);
    }
}
