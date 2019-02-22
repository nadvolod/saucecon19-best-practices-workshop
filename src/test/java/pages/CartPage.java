package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{
    private By checkoutBtn = By.className("cart_checkout_link");
    private By cartBadge = By.className("shopping_cart_badge");

    public static CartPage visit(WebDriver driver) {
        CartPage page = new CartPage(driver);
        driver.get("https://www.saucedemo.com/cart.html");
        return page;
    }

    public CartPage(WebDriver driver) { this.driver = driver; }

    public void checkout() {
        click(checkoutBtn);
    }

    private String cartItemCounterText() {
        try {
            return getInnerText(cartBadge);
        } catch (NoSuchElementException e) {
            System.out.println("Something Went Wrong: " + e);
            return "0";
        }
    }
    public Boolean hasItems() {
        return Integer.parseInt(cartItemCounterText()) > 0;
    }
}
