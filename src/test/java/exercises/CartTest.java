package exercises;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import pages.LogInPage;
import pages.InventoryPage;
import pages.CartPage;

public class CartTest extends BaseTest{
    @Tag(name = "confirmCheckout()")
    @Test
    public void confirmCheckout() {
        LogInPage logInPage = LogInPage.visit(driver);
        logInPage.signInSuccessfully();
        InventoryPage inventoryPage = InventoryPage.visit(driver);
        inventoryPage.addTwoItems();
        CartPage cartPage = CartPage.visit(driver);
        cartPage.checkout();
        Assert.assertTrue(cartPage.hasItems());
    }
}
