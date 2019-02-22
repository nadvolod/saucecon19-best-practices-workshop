package exercises;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;
import pages.LogInPage;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

public class LogInTest extends BaseTest  {

    @Tag(name = "logInSuccessfully()")
    @Test(dataProvider = "hardCodedBrowsers")
    /** Tests for a successful login **/
    public void logInSuccessfully(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();
        LogInPage logInPage = LogInPage.visit(driver);
        logInPage.signInSuccessfully();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
}
