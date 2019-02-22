package exercises;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.lang.reflect.Method;
import java.rmi.UnexpectedException;

public class BaseTest {

    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    public String username = System.getenv("SAUCE_USERNAME");
    public String accessKey = System.getenv("SAUCE_ACCESS_KEY");

    @DataProvider(name = "hardCodedBrowsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
        return new Object[][]{
                new Object[]{"MicrosoftEdge", "14.14393", "Windows 10"},
                new Object[]{"firefox", "49.0", "Windows 10"},
                new Object[]{"internet explorer", "11.0", "Windows 7"},
                new Object[]{"safari", "10.0", "OS X 10.11"},
                new Object[]{"chrome", "54.0", "OS X 10.10"},
                new Object[]{"firefox", "latest-1", "Windows 7"},
        };
    }

    public WebDriver getWebDriver() {
        return webDriver.get();
    }
    public String getSessionId() {
        return sessionId.get();
    }

    protected void createDriver(String browser, String version, String os, String methodName)
            throws MalformedURLException, UnexpectedException {

        /*ChromeOptions chromeOpts = new ChromeOptions();
        chromeOpts.setExperimentalOption("w3c", true);*/

       /* MutableCapabilities sauceOpts = new MutableCapabilities();
        sauceOpts.setCapability("username", username);
        sauceOpts.setCapability("accessKey", accessKey);
        //sauceOpts.setCapability("seleniumVersion", "3.141.59");
        sauceOpts.setCapability("commandTimeout", 90);
        sauceOpts.setCapability("idleTimeout", 10);
        sauceOpts.setCapability("maxDuration", 100);
        sauceOpts.setCapability("name", methodName);
        sauceOpts.setCapability("build", "saucecon19-best-practices");
        sauceOpts.setCapability("tags", "['best-practices', 'saucecon19']");*/

        DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(ChromeOptions.CAPABILITY,  chromeOpts);
        //caps.setCapability("sauce:options", sauceOpts);
        caps.setCapability(CapabilityType.BROWSER_NAME, browser);
        caps.setCapability(CapabilityType.VERSION, version);
        caps.setCapability(CapabilityType.PLATFORM, os);
        caps.setCapability("name", methodName);
        caps.setCapability("build", "saucecon19-best-practices.02");
        caps.setCapability("tags", "['best-practices', 'saucecon19']");
        caps.setCapability("commandTimeout", 90);
        caps.setCapability("idleTimeout", 100);
        caps.setCapability("maxDuration", 300);
        caps.setCapability("avoidProxy", true);

        String sauceUrl = "https://" + username + ":" + accessKey + "@ondemand.saucelabs.com:443/wd/hub";
        URL url = new URL(sauceUrl);
        webDriver.set(new RemoteWebDriver(url, caps));
        // set current sessionId
        String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        sessionId.set(id);
    }

    @AfterMethod
    public void teardown(ITestResult result) throws Exception {
        ((JavascriptExecutor)webDriver.get()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        webDriver.get().quit();
    }

    protected void annotate(String text) {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:context=" + text);
    }
}

