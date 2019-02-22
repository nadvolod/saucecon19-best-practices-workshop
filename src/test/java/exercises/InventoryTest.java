package exercises;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;
import pages.InventoryPage;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;


public class InventoryTest extends BaseTest {

    @Tag(name = "oneItemInCart()")
    @Test(dataProvider = "hardCodedBrowsers")
    /** Tests placing one specific item in the cart **/
    public void oneItemInCart(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();
        InventoryPage inventoryPage = InventoryPage.visit(driver);
        inventoryPage.addOneItem();
        Assert.assertEquals(inventoryPage.itemCount(), "1");
        inventoryPage.checkout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");

    }

    @Tag(name = "twoItemsInCart()")
    @Test(dataProvider = "hardCodedBrowsers")
    /** Tests placing two specific items in the cart **/
    public void twoItemsInCart(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();
        InventoryPage inventoryPage = InventoryPage.visit(driver);
        inventoryPage.addTwoItems();
        Assert.assertEquals(inventoryPage.itemCount(), "2");
        inventoryPage.checkout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
    }
}