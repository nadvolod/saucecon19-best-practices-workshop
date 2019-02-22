package exercises;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import pages.CartPage;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

public class CartTest extends BaseTest{
    @Tag(name = "checkoutTest()")
    @Test(dataProvider = "hardCodedBrowsers")
    public void checkoutTest(String browser, String version, String os, Method method)
        throws MalformedURLException, InvalidElementStateException, UnexpectedException {
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();
        CartPage cartPage = CartPage.visit(driver);
        cartPage.setCartState();
        cartPage.checkout();
        Assert.assertTrue(cartPage.hasItems());
    }

    /*@Tag(name = "confirmCheckout()")
    @Test
    public void confirmCheckout() {
        LogInPage logInPage = LogInPage.visit(driver);
        logInPage.signInSuccessfully();
        InventoryPage inventoryPage = InventoryPage.visit(driver);
        inventoryPage.addTwoItems();
        CartPage cartPage = CartPage.visit(driver);
        cartPage.checkout();
        Assert.assertTrue(cartPage.hasItems());
    }*/
}
