package test.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.base.Base;

import java.io.File;
import java.io.IOException;

public class FileUpload extends Base {

    @Tag("uploadFile()")
    @DisplayName("uploadFile()")
    @Test
    public void uploadFile() throws IOException {
        // FileDetector Required when using Remote WebDriver
        // driver.setFileDetector(new LocalFileDetector());

        driver.get("http://the-internet.herokuapp.com/upload");

        WebElement inputElement = driver.findElement(By.id("file-upload"));
        File file = new File("lib/drivers/chromedriver");
        inputElement.sendKeys(file.getCanonicalPath());
    }

}

